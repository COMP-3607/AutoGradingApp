package autograding;

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

/*
 * Open zip folder 
 * open regular folder
 * Access pdf
 * Read pdf
 * return text in folder
 */

public class SpecReader implements FileReader {
    private FileInputStream zippedSpecStream = null;
    private File specFile;
    private Scanner fileScanner;
    private ZipInputStream zipDataStream;
    private ZipFile zipFile;
    private ZipEntry entry;
    private Entry inside;
    private BodyContentHandler handler = new BodyContentHandler();
    private Metadata meta = new Metadata();
    private ParseContext contextParser = new ParseContext();
    private PDFParser parser = new PDFParser();
    private String error = "Error reading file";
    private FileInputStream folderSpecStream;
    private File file;
    private File folder;
    // private FileInputStream stream;
    int ch;
    byte bytes[];
    FileInputStream stream;

    @Override
    public void readFile(String specFilePath, String specFolder, String specExtractionDirectory) {

        /*try (ZipFile zipFile = new ZipFile(specFilePath)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();

                // Check if the entry is a directory (folder)
                if (entry.isDirectory()) {
                    System.out.println("Folder found: " + entry.getName());
                } else {
                    // Check if the entry is a zipped folder
                    if (entry.getName().endsWith(".zip")) {
                        System.out.println("Zipped folder found: " + entry.getName());
                        // Open and print the contents of the zipped folder
                        try (InputStream entryStream = zipFile.getInputStream(entry);
                             ZipInputStream zis = new ZipInputStream(entryStream)) {
                            ZipEntry subEntry;
                            while ((subEntry = zis.getNextEntry()) != null) {
                                System.out.println("File in zipped folder: " + subEntry.getName());
                                // Print the contents of the file (you can modify this part as needed)
                               /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                byte[] buffer = new byte[1024];
                                int len;
                                while ((len = zis.read(buffer)) > 0) {
                                    baos.write(buffer, 0, len);
                                }
                                System.out.println("File contents: " + new String(baos.toByteArray()));*/
                           /*  }
                        }
                    } else {
                        System.out.println("File found: " + entry.getName());
                        // Print the contents of the file (you can modify this part as needed)
                        try (InputStream entryStream = zipFile.getInputStream(entry);
                             BufferedReader reader = new BufferedReader(new InputStreamReader(entryStream))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                System.out.println("File contents: " + line);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

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
                            String pdfText = textStripper.getText(document);
                            System.out.println("Text in the PDF file:\n" + pdfText);
                        }
                    } else {
                        // Handle other file types or extensions if needed
                        System.out.println("Non-PDF file found: " + entry.getName());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                } else {
                    // Handle other file types or extensions if needed
                    System.out.println("Non-PDF file found inside zipped folder: " + subEntry.getName());
                }
            }
        }   
    }
}