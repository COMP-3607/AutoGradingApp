package autograding.TestObjectFactory;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelReportGenerator implements ReportObserver {

    @Override
    public void update(StudentSubmission submission) {
        generateExcelReport(submission);
    }

    public static void generateExcelReport(StudentSubmission submission) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("StudentReports");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Student ID");
            headerRow.createCell(1).setCellValue("Student Name");
            headerRow.createCell(2).setCellValue("Submission Date");
            headerRow.createCell(3).setCellValue("Score");

            // Populate data
            Row row = sheet.createRow(1);
            row.createCell(0).setCellValue(submission.getStudentId());
            row.createCell(1).setCellValue(submission.getFullName());
            row.createCell(2).setCellValue(submission.getSubmissionDate());
            row.createCell(3).setCellValue(submission.getMarksFromSubmissionTest());

            // Save the Excel file
            try (FileOutputStream fileOut = new FileOutputStream("StudentReport.xlsx")) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
