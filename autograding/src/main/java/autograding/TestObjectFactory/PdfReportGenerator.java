package autograding.TestObjectFactory;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;

public class PdfReportGenerator implements ReportObserver {
    @Override
    public void update(StudentSubmission submission) {
        generateReport(submission);
    }

    public static void generateReport(StudentSubmission submission) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);

            float margin = 50;
            float lineSpacing = 20;

            // Write student information to the PDF
            writeText(contentStream, margin, page.getMediaBox().getHeight() - 50, "First Name: " + submission.getFirstName(), 12, lineSpacing);
            writeText(contentStream, margin, page.getMediaBox().getHeight() - 50, "Last Name: " + submission.getLastName(), 12, lineSpacing);
            writeText(contentStream, margin, page.getMediaBox().getHeight() - 70, "Student ID: " + submission.getStudentId(), 12, lineSpacing);

            // ... existing code

            // Assuming SubmissionTests has a getMarks method
            double totalMarks = submission.getSubmissionTests().getMarks();

            // Include the total marks in the report
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, page.getMediaBox().getHeight() - 170);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.showText("Total Marks: " + totalMarks);
            contentStream.endText();

            contentStream.close();

            // Save the PDF document to a file
            document.save("StudentReport.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeText(PDPageContentStream contentStream, float x, float y, String text, float fontSize, float lineSpacing) throws IOException {
        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.setFont(PDType1Font.HELVETICA, fontSize);
        contentStream.showText(text);
        contentStream.endText();
    }
}