package autograding.DataReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class SpecReader implements FileReader {
    private StringBuilder pdfText = new StringBuilder();

    public String readFile(String specFilePath, String specFolder, String specExtractionDirectory) {
        try (ZipFile zipFile = new ZipFile(specFilePath)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();

                if (entry.isDirectory()) {
                    // Skip processing directories
                    continue;
                }

                if (entry.getName().toLowerCase().endsWith(".zip")) {
                    processZippedFolder(zipFile.getInputStream(entry));

                } else if (entry.getName().toLowerCase().endsWith(".pdf")) {
                    processPdfEntry(zipFile.getInputStream(entry));

                } /*
                   * else {
                   * System.out.println("Other file found: " + entry.getName());
                   * }
                   */
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return PDF contents
        return pdfText.toString();
    }

    private void processZippedFolder(InputStream zipInputStream) {
        try (ZipInputStream zis = new ZipInputStream(zipInputStream)) {
            ZipEntry subEntry;
            while ((subEntry = zis.getNextEntry()) != null) {
                if (subEntry.isDirectory()) {
                    // Skip processing directories inside zipped folder
                } else if (subEntry.getName().toLowerCase().endsWith(".zip")) {
                    processZippedFolder(zis);
                } else if (subEntry.getName().toLowerCase().endsWith(".pdf")) {
                    processPdfEntry(zis);
                } /*
                   * else {
                   * //System.out.println("Other file found: " + subEntry.getName());
                   * }
                   */
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processPdfEntry(InputStream entryStream) {
        try (PDDocument document = PDDocument.load(entryStream)) {
            PDFTextStripper textStripper = new PDFTextStripper();
            pdfText.append(textStripper.getText(document)).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
