package com.rnd.scrum;

import com.rnd.scrum.excel.IssuesExcelExport;
import com.rnd.scrum.rest.JiraService;

//@SpringBootApplication
public class ScrumreportsApplication {

	public static void main(String[] args) throws Exception {
		// SpringApplication.run(ScrumreportsApplication.class, args);
		JiraService jiraService = new JiraService();
		IssuesExcelExport iee = new IssuesExcelExport(jiraService.fetchData());
		iee.export("C:\\Work\\RD\\Jira\\ST.xlsx");
	}

}
