/**
 * 
 */
package com.rnd.scrum.dto;

import java.util.List;

/**
 * @author Sambaiah.Jillellamud
 *
 */
public class Issue {
	private String jira;
	private String epic;
	private String epicJira;
	private String story;
	private String status;
	private String assignee;
	private String sprint;
	private String sprintStartDate;
	private String sptintEndDate;
	private String sprintCompleteDate;
	private String sprintStatus;
	private String estimatedEfforts;
	private String loggedEfforts;
	private String remainingEfforts;
	private String prioirty;
	private String project;
	private List<WorkLog> workLogs;

	/**
	 * @return the jira
	 */
	public String getJira() {
		return jira;
	}

	/**
	 * @param jira
	 *            the jira to set
	 */
	public void setJira(String jira) {
		this.jira = jira;
	}

	/**
	 * @return the epicJira
	 */
	public String getEpicJira() {
		return epicJira;
	}

	/**
	 * @param epicJira
	 *            the epicJira to set
	 */
	public void setEpicJira(String epicJira) {
		this.epicJira = epicJira;
	}

	/**
	 * @return the epic
	 */
	public String getEpic() {
		return epic;
	}

	/**
	 * @param epic
	 *            the epic to set
	 */
	public void setEpic(String epic) {
		this.epic = epic;
	}

	/**
	 * @return the story
	 */
	public String getStory() {
		return story;
	}

	/**
	 * @param story
	 *            the story to set
	 */
	public void setStory(String story) {
		this.story = story;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the assignee
	 */
	public String getAssignee() {
		return assignee;
	}

	/**
	 * @param assignee
	 *            the assignee to set
	 */
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	/**
	 * @return the sprint
	 */
	public String getSprint() {
		return sprint;
	}

	/**
	 * @param sprint
	 *            the sprint to set
	 */
	public void setSprint(String sprint) {
		this.sprint = sprint;
	}

	/**
	 * @return the sprintStartDate
	 */
	public String getSprintStartDate() {
		return sprintStartDate;
	}

	/**
	 * @param sprintStartDate
	 *            the sprintStartDate to set
	 */
	public void setSprintStartDate(String sprintStartDate) {
		this.sprintStartDate = sprintStartDate;
	}

	/**
	 * @return the sptintEndDate
	 */
	public String getSptintEndDate() {
		return sptintEndDate;
	}

	/**
	 * @param sptintEndDate
	 *            the sptintEndDate to set
	 */
	public void setSptintEndDate(String sptintEndDate) {
		this.sptintEndDate = sptintEndDate;
	}

	/**
	 * @return the sprintCompleteDate
	 */
	public String getSprintCompleteDate() {
		return sprintCompleteDate;
	}

	/**
	 * @param sprintCompleteDate
	 *            the sprintCompleteDate to set
	 */
	public void setSprintCompleteDate(String sprintCompleteDate) {
		this.sprintCompleteDate = sprintCompleteDate;
	}

	/**
	 * @return the sprintStatus
	 */
	public String getSprintStatus() {
		return sprintStatus;
	}

	/**
	 * @param sprintStatus
	 *            the sprintStatus to set
	 */
	public void setSprintStatus(String sprintStatus) {
		this.sprintStatus = sprintStatus;
	}

	/**
	 * @return the estimatedEfforts
	 */
	public String getEstimatedEfforts() {
		return estimatedEfforts;
	}

	/**
	 * @param estimatedEfforts
	 *            the estimatedEfforts to set
	 */
	public void setEstimatedEfforts(String estimatedEfforts) {
		this.estimatedEfforts = estimatedEfforts;
	}

	/**
	 * @return the loggedEfforts
	 */
	public String getLoggedEfforts() {
		return loggedEfforts;
	}

	/**
	 * @param loggedEfforts
	 *            the loggedEfforts to set
	 */
	public void setLoggedEfforts(String loggedEfforts) {
		this.loggedEfforts = loggedEfforts;
	}

	/**
	 * @return the remainingEfforts
	 */
	public String getRemainingEfforts() {
		return remainingEfforts;
	}

	/**
	 * @param remainingEfforts
	 *            the remainingEfforts to set
	 */
	public void setRemainingEfforts(String remainingEfforts) {
		this.remainingEfforts = remainingEfforts;
	}

	/**
	 * @return the prioirty
	 */
	public String getPrioirty() {
		return prioirty;
	}

	/**
	 * @param prioirty
	 *            the prioirty to set
	 */
	public void setPrioirty(String prioirty) {
		this.prioirty = prioirty;
	}

	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @param project
	 *            the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return the workLogs
	 */
	public List<WorkLog> getWorkLogs() {
		return workLogs;
	}

	/**
	 * @param workLogs
	 *            the workLogs to set
	 */
	public void setWorkLogs(List<WorkLog> workLogs) {
		this.workLogs = workLogs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Issue [jira=" + jira + ", epic=" + epic + ", epicJira=" + epicJira + ", story=" + story + ", status="
				+ status + ", assignee=" + assignee + ", sprint=" + sprint + ", sprintStartDate=" + sprintStartDate
				+ ", sptintEndDate=" + sptintEndDate + ", sprintCompleteDate=" + sprintCompleteDate + ", sprintStatus="
				+ sprintStatus + ", estimatedEfforts=" + estimatedEfforts + ", loggedEfforts=" + loggedEfforts
				+ ", remainingEfforts=" + remainingEfforts + ", prioirty=" + prioirty + ", project=" + project
				+ ", workLogs=" + workLogs + "]";
	}
}
