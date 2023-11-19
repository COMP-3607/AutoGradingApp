package autograding.TestObjectFactory;

public class ReportGenerator implements ReportObserver {

    @Override
    public void update(StudentSubmission submission) {
        // Generate both PDF and Excel reports
        PdfReportGenerator.generateReport(submission);
        ExcelReportGenerator.generateExcelReport(submission);
    }
}
