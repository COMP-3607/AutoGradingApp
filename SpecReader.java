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

                } /*else {
                    System.out.println("Other file found: " + entry.getName());
                }*/
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
                } else {
                    //System.out.println("Other file found: " + subEntry.getName());
                }
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



/*
New Code 
package autograding.DataReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class SpecReader implements FileReader {
    private String pdfText = "";

    public String readFile(String specFilePath, String specFolder, String specExtractionDirectory) {
        try (ZipFile zipFile = new ZipFile(specFilePath)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();

                if (entry.isDirectory()) {
                    // Skip processing directories
                    // System.out.println("Folder found: " + entry.getName());
                    continue;
                }

                if (entry.getName().toLowerCase().endsWith(".zip")) {
                    // System.out.println("Zipped folder found: " + entry.getName());
                    processZippedFolder(zipFile.getInputStream(entry));

                } else if (entry.getName().toLowerCase().endsWith(".pdf")) {
                    // System.out.println("PDF file found: " + entry.getName());
                    try (InputStream entryStream = zipFile.getInputStream(entry);
                         PDDocument document = PDDocument.load(entryStream)) {
                        PDFTextStripper textStripper = new PDFTextStripper();
                        pdfText += textStripper.getText(document);
                    }
                } else {
                    // System.out.println("Other file found: " + entry.getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Combine PDF content into a single string
        StringBuilder result = new StringBuilder();
        result.append("PDF Contents:\n").append(pdfText).append("\n");

        // System.out.println("TESTING....................................................\n" + result.toString());

        return result.toString();
    }

    private void processZippedFolder(InputStream zipInputStream) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(zipInputStream)) {
            ZipEntry subEntry;
            while ((subEntry = zis.getNextEntry()) != null) {
                if (subEntry.isDirectory()) {
                     System.out.println("Folder found inside zipped folder: " + subEntry.getName());
                } else if (subEntry.getName().toLowerCase().endsWith(".zip")) {
                    // System.out.println("Nested zipped folder found inside zipped folder: " + subEntry.getName());
                    // Recursively process nested ZIP file
                    processZippedFolder(zis);
                } else if (subEntry.getName().toLowerCase().endsWith(".pdf")) {
                    // System.out.println("PDF file found inside zipped folder: " + subEntry.getName());
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
                } else {
                    System.out.println("Other file found inside zipped folder: " + subEntry.getName());
                }
            }
        }
    }
}*/



/*

Working Code

package autograding.DataReader;
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

 
public class SpecReader implements FileReader {
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
                            System.out.println("Text in the PDF file:\n" + pdfText);
                        }
                    } else if (entry.getName().toLowerCase().endsWith(".java")) {
                        System.out.println("Java file found: " + entry.getName());
                        // Open and read the contents of the Java file
                        try (InputStream entryStream = zipFile.getInputStream(entry);
                             BufferedReader reader = new BufferedReader(new InputStreamReader(entryStream))) {
                            String code;
                            while ((code = reader.readLine()) != null) {
                                System.out.println("Java file contents: " + code);
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
                            System.out.println("Text in the PDF file:\n" + pdfText);
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
                        System.out.println("Java file contents:" + baos.toString());
                    }
                } else {
                    // Handle other file types or extensions if needed
                    System.out.println("Other file found inside zipped folder: " + subEntry.getName());
                }
            }
        }
    }
}*/
