package autograding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class SubmissionReader implements FileReader {
    String str;
    /*
     * 
     * public static void main(String[] args) {
     * // Specify the path to the uploaded ZIP file.
     * String zipFilePath = "path_to_uploaded_zip_file.zip";
     * 
     * // Specify the directory where you want to extract the submissions.
     * String extractionDirectory = "extraction_directory";
     * 
     * // Call the method to process the submissions.
     * processSubmissions(zipFilePath, extractionDirectory);
     * }
     */

    public String readFile(String zipFilePath, String specFolder, String extractionDirectory) {
        try {
            // Create a directory for extracting submissions.
            File extractionDir = new File(extractionDirectory);
            extractionDir.mkdirs();

            // Open the ZIP file.
            try (ZipFile zipFile = new ZipFile(zipFilePath)) {
                Enumeration<? extends ZipEntry> entries = zipFile.entries();

                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();

                    // Ensure the entry is a file, not a directory.
                    if (!entry.isDirectory()) {
                        // Extract the file to the extraction directory.
                        File extractedFile = new File(extractionDir, entry.getName());

                        // Ensure the parent directories exist.
                        extractedFile.getParentFile().mkdirs();

                        // Create an output stream to write the extracted file.
                        try (FileOutputStream outputStream = new FileOutputStream(extractedFile)) {
                            int bytesRead;
                            byte[] buffer = new byte[1024];
                            while ((bytesRead = zipFile.getInputStream(entry).read(buffer)) != -1) {
                                outputStream.write(buffer, 0, bytesRead);
                            }
                        }

                        // Process the extracted file here.
                        // You can add code to analyze Java files, evaluate them, and generate reports.
                        // For simplicity, this example only extracts the files.

                        System.out.println("Extracted: " + extractedFile.getAbsolutePath());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
