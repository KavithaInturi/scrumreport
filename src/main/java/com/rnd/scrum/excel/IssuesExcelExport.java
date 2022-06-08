/**
 * 
 */
package com.rnd.scrum.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rnd.scrum.dto.Issue;
import com.rnd.scrum.dto.WorkLog;

/**
 * @author Sambaiah.Jillellamud
 *
 */
public class IssuesExcelExport {
	private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
	private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:MM:SS");
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFSheet iPlanSheet;
	private List<Issue> listIssues;

	public IssuesExcelExport(List<Issue> issues) {
		this.listIssues = issues;
		workbook = new XSSFWorkbook();
	}

	private void writeSprignPlanningHeaderLine() {
		sheet = workbook.createSheet("Sprint Planning");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);

		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(sheet, row, 0, "Project", style);
		createCell(sheet, row, 1, "Jira", style);
		createCell(sheet, row, 2, "Epic", style);
		createCell(sheet, row, 3, "Story", style);
		createCell(sheet, row, 4, "Sprint", style);
		createCell(sheet, row, 5, "Assignee", style);
		createCell(sheet, row, 6, "Status", style);
		createCell(sheet, row, 7, "Sprint Start Date", style);
		createCell(sheet, row, 8, "Sprint End Date", style);
		createCell(sheet, row, 9, "Sprint Actual End Date", style);
		createCell(sheet, row, 10, "Sprint Status", style);
		createCell(sheet, row, 11, "Esstimated Efforts in Days", style);
		createCell(sheet, row, 12, "Time Spent in Days", style);
		createCell(sheet, row, 13, "Remaining Efforts in Days", style);
	}

	private void writeIPlanHeaderLine() {
		iPlanSheet = workbook.createSheet("iPlan");

		Row row = iPlanSheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);

		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(iPlanSheet, row, 0, "Project", style);
		createCell(iPlanSheet, row, 1, "Jira", style);
		createCell(iPlanSheet, row, 2, "Epic", style);
		createCell(iPlanSheet, row, 3, "Story", style);
		createCell(iPlanSheet, row, 4, "Sprint", style);
		createCell(iPlanSheet, row, 5, "Resource", style);
		createCell(iPlanSheet, row, 6, "Time Spent in Days", style);
		createCell(iPlanSheet, row, 7, "Logged Date", style);
		createCell(iPlanSheet, row, 8, "Logged Time", style);
	}

	private void createCell(XSSFSheet sheet, Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeIPlanDataLines() {
		int rowCount = 1;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);

		for (Issue issue : listIssues) {
			if (issue.getWorkLogs() != null && !issue.getWorkLogs().isEmpty()) {
				for (WorkLog wl : issue.getWorkLogs()) {
					Row row = iPlanSheet.createRow(rowCount++);
					int columnCount = 0;
					createCell(iPlanSheet, row, columnCount++, issue.getProject(), style);
					createCell(iPlanSheet, row, columnCount++, issue.getJira(), style);
					createCell(iPlanSheet, row, columnCount++, issue.getEpic(), style);
					createCell(iPlanSheet, row, columnCount++, issue.getStory(), style);
					createCell(iPlanSheet, row, columnCount++, issue.getSprint(), style);
					createCell(iPlanSheet, row, columnCount++, wl.getUser(), style);
					createCell(iPlanSheet, row, columnCount++, wl.getSpentInDays(), style);
					if (wl.getUpdateDateTime() != null && !wl.getUpdateDateTime().isEmpty()) {
						createCell(iPlanSheet, row, columnCount++, LocalDateTime
								.parse((CharSequence) wl.getUpdateDateTime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME)
								.format(DTF), style);
						createCell(iPlanSheet, row, columnCount++, LocalDateTime
								.parse((CharSequence) wl.getUpdateDateTime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME)
								.format(TIME), style);
					} else
						createCell(iPlanSheet, row, columnCount++, null, style);
				}
			}
		}
	}

	private void writeSprignPlanningDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);

		for (Issue issue : listIssues) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(sheet, row, columnCount++, issue.getProject(), style);
			createCell(sheet, row, columnCount++, issue.getJira(), style);
			createCell(sheet, row, columnCount++, issue.getEpic(), style);
			createCell(sheet, row, columnCount++, issue.getStory(), style);
			createCell(sheet, row, columnCount++, issue.getSprint(), style);
			createCell(sheet, row, columnCount++, issue.getAssignee(), style);
			createCell(sheet, row, columnCount++, issue.getStatus(), style);
			if (issue.getSprintStartDate() != null && !issue.getSprintStartDate().isEmpty())
				createCell(sheet, row, columnCount++, LocalDateTime
						.parse((CharSequence) issue.getSprintStartDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME)
						.format(DTF), style);
			else
				createCell(sheet, row, columnCount++, null, style);
			if (issue.getSptintEndDate() != null && !issue.getSptintEndDate().isEmpty())
				createCell(sheet, row, columnCount++,
						LocalDateTime
								.parse((CharSequence) issue.getSptintEndDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME)
								.format(DTF),
						style);
			else
				createCell(sheet, row, columnCount++, null, style);
			if (issue.getSprintCompleteDate() != null && !issue.getSprintCompleteDate().isEmpty())
				createCell(sheet, row, columnCount++, LocalDateTime
						.parse((CharSequence) issue.getSprintCompleteDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME)
						.format(DTF), style);
			else
				createCell(sheet, row, columnCount++, null, style);
			createCell(sheet, row, columnCount++, issue.getSprintStatus(), style);
			createCell(sheet, row, columnCount++, issue.getEstimatedEfforts(), style);
			createCell(sheet, row, columnCount++, issue.getLoggedEfforts(), style);
			createCell(sheet, row, columnCount++, issue.getRemainingEfforts(), style);
		}
	}

	public void export(String fileLocation) throws IOException {
		writeSprignPlanningHeaderLine();
		writeSprignPlanningDataLines();
		writeIPlanHeaderLine();
		writeIPlanDataLines();

		FileOutputStream fos = new FileOutputStream(fileLocation);
		workbook.write(fos);
		fos.close();
	}
}
