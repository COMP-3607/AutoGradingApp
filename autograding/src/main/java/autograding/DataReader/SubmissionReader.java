package autograding.DataReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

//public class SubmissionReader implements FileReader {
//  String str;
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
/*
* 
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
                       
                       // System.out.println("Extracted: " + extractedFile.getAbsolutePath());
                   }
               }
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       return str;
   }
   */

import java.util.Enumeration;
import java.util.Scanner;
//import java.util.zip;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.sax.BodyContentHandler;

import com.coremedia.iso.boxes.CompositionTimeToSample.Entry;

import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.parser.ParseContext;

public class SubmissionReader implements FileReader {
    private String pdfText = "";

    public String readFile(String specFilePath, String specFolder, String specExtractionDirectory) {
        try (ZipFile zipFile = new ZipFile(specFilePath)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();

                // Check if the entry is a directory (folder)
                if (entry.isDirectory()) {
                    System.out.println("Folder found: " + entry.getName());
                } else {
                    // Check if the entry is a zipped folder
                    if (entry.getName().toLowerCase().endsWith(".zip")) {
                        System.out.print("======================================================================\n");
                        System.out.println("Zipped folder found: " + entry.getName());
                        // Open and process the contents of the zipped folder
                        processZippedFolder(zipFile.getInputStream(entry));
                    } else if (entry.getName().toLowerCase().endsWith(".pdf")) {
                        System.out.println("PDF file found: " + entry.getName());
                        // Open and read the contents of the PDF file
                        try (InputStream entryStream = zipFile.getInputStream(entry);
                                PDDocument document = PDDocument.load(entryStream)) {
                            PDFTextStripper textStripper = new PDFTextStripper();
                            pdfText = textStripper.getText(document);
                            // System.out.println("Text in the PDF file:\n" + pdfText);
                        }
                    } else if (entry.getName().toLowerCase().endsWith(".java")) {
                        System.out.println("Java file found: " + entry.getName());
                        // Open and read the contents of the Java file
                        try (InputStream entryStream = zipFile.getInputStream(entry);
                                BufferedReader reader = new BufferedReader(new InputStreamReader(entryStream))) {
                            String code;
                            while ((code = reader.readLine()) != null) {
                                // System.out.println("Java file contents: " + code);
                            }
                        }
                    } else {
                        // Handle other file types or extensions if needed
                        System.out.println("Other file found: " + entry.getName());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pdfText;
    }

    private static void processZippedFolder(InputStream zipInputStream) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(zipInputStream)) {
            ZipEntry subEntry;
            while ((subEntry = zis.getNextEntry()) != null) {
                // Check if the entry is a directory (folder)
                if (subEntry.isDirectory()) {
                    System.out.println("Folder found inside zipped folder: " + subEntry.getName());
                } else if (subEntry.getName().toLowerCase().endsWith(".pdf")) {
                    System.out.println("PDF file found inside zipped folder: " + subEntry.getName());
                    // Open and read the contents of the PDF file
                    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            baos.write(buffer, 0, len);
                        }

                        try (PDDocument document = PDDocument.load(baos.toByteArray())) {
                            PDFTextStripper textStripper = new PDFTextStripper();
                            String pdfText = textStripper.getText(document);
                            // System.out.println("Text in the PDF file:\n" + pdfText);
                        }
                    }
                } else if (subEntry.getName().toLowerCase().endsWith(".java")) {
                    System.out.println("Java file found inside zipped folder: " + subEntry.getName());
                    // Open and read the contents of the Java file
                    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            baos.write(buffer, 0, len);
                        }
                        // System.out.println("Java file contents:" + baos.toString());
                    }
                } else {
                    // Handle other file types or extensions if needed
                    System.out.println("Other file found inside zipped folder: " + subEntry.getName());
                }
            }
        }
    }
}
