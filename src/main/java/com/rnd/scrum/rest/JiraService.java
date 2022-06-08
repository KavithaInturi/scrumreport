/**
 * 
 */
package com.rnd.scrum.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.IssueField;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;
import com.rnd.scrum.dto.Issue;
import com.rnd.scrum.dto.WorkLog;

/**
 * @author Sambaiah.Jillellamud
 *
 */
public class JiraService {
	Logger logger = LoggerFactory.getLogger(JiraService.class);
	private static final String JIRA_URL = "your jira url";
	private static final String userName = "username";
	private static final String password = "password";
	private JiraRestClient jiraRestClient = null;
	private static final int SPRINTHOURS = 7 * 3600;

	public JiraService() {
		jiraRestClient = getJiraRestClient();
	}

	private JiraRestClient getJiraRestClient() {
		return new AsynchronousJiraRestClientFactory().createWithBasicHttpAuthentication(getJiraUri(), userName,
				password);
	}

	private URI getJiraUri() {
		return URI.create(JIRA_URL);
	}

	public List<Issue> fetchData() throws Exception {
		List<Issue> issues = new ArrayList<Issue>();
		Promise<SearchResult> results = this.jiraRestClient.getSearchClient()
				.searchJql("project = ST AND issuetype in (Bug, Story, Task) ORDER BY cf[10007] ASC, assignee ASC");
		results.claim();
		results.get().getIssues().forEach(i -> {
			Issue issueData = new Issue();
			issueData.setJira(i.getKey());
			issueData.setAssignee(i.getAssignee().getName());
			issueData.setStory(i.getSummary());
			issueData.setStatus(i.getStatus().getName());
			issueData.setPrioirty(i.getPriority().getName());
			issueData.setProject(i.getProject().getName());

			// Populating times
			IssueField issf = i.getFieldByName("Original Estimate");
			if (issf != null && issf.getValue() != null)
				issueData.setEstimatedEfforts(String.valueOf((Integer) issf.getValue() / SPRINTHOURS));
			issf = i.getFieldByName("Remaining Estimate");
			if (issf != null && issf.getValue() != null)
				issueData.setRemainingEfforts(String.valueOf((Integer) issf.getValue() / SPRINTHOURS));
			issf = i.getFieldByName("Time Spent");
			if (issf != null && issf.getValue() != null)
				issueData.setLoggedEfforts(String.valueOf((Integer) issf.getValue() / SPRINTHOURS));

			// Setting Sprint Details
			populateSprintDetails(i, issueData);
			populateWorkLogs(i, issueData);
			IssueField issueF = i.getFieldByName("Epic Link");
			if (issueF != null && issueF.getValue() != null) {
				issueData.setEpicJira((String) issueF.getValue());
			}
			issues.add(issueData);
		});
		populateEpicDetails(issues);
		return issues;
	}

	private void populateSprintDetails(com.atlassian.jira.rest.client.api.domain.Issue i, Issue response) {
		IssueField issueF = i.getFieldByName("Sprint");
		if (issueF != null && issueF.getValue() != null) {
			String str = issueF.getValue().toString();
			String[] fields = str.split(",");
			for (int j = 0; j < fields.length; j++) {
				if (fields[j].split("=")[1].equals("<null>"))
					continue;
				if (fields[j].contains("name") && !fields[j].isEmpty()) {
					response.setSprint(fields[j].split("=")[1]);
				} else if (fields[j].contains("state")) {
					response.setSprintStatus(fields[j].split("=")[1]);
				} else if (fields[j].contains("startDate") && fields[j] != null && fields[j].split("=").length == 2) {
					response.setSprintStartDate(fields[j].split("=")[1]);
				} else if (fields[j].contains("endDate") && fields[j] != null && fields[j].split("=").length == 2) {
					response.setSptintEndDate(fields[j].split("=")[1]);
				} else if (fields[j].contains("completeDate") && fields[j] != null
						&& fields[j].split("=").length == 2) {
					response.setSprintCompleteDate(fields[j].split("=")[1]);
				}
			}
		}
	}

	private void populateWorkLogs(com.atlassian.jira.rest.client.api.domain.Issue i, Issue response) {
		com.atlassian.jira.rest.client.api.domain.Issue is = this.jiraRestClient.getIssueClient()
				.getIssue(response.getJira()).claim();
		List<WorkLog> logs = new ArrayList<WorkLog>();
		response.setWorkLogs(logs);
		is.getWorklogs().forEach(wl -> {
			WorkLog workLog = new WorkLog();
			workLog.setUser(wl.getUpdateAuthor().getName());
			workLog.setUpdateDateTime(wl.getUpdateDate().toString());
			workLog.setSpentInDays(String.format("%.2f", wl.getMinutesSpent() / 420.0));
			response.getWorkLogs().add(workLog);
		});
	}

	private void populateEpicDetails(List<Issue> issues) {
		String epicJIRAs = issues.stream().map(i -> i.getEpicJira()).collect(Collectors.joining(","));
		try {
			Promise<SearchResult> results = this.jiraRestClient.getSearchClient()
					.searchJql("key in (" + epicJIRAs + ")");
			results.get().getIssues().forEach(i -> {
				issues.forEach(ai -> {
					if (ai.getEpicJira().equals(i.getKey())) {
						ai.setEpic(i.getSummary());
					}
				});
			});
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
