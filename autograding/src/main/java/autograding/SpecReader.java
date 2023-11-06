package autograding;

import java.util.Scanner;
//import java.util.zip;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.io.File;
import java.io.FileInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.parser.ParseContext;

public class SpecReader implements FileReader {
    FileInputStream zippedSpecStream = null;
    File specFile;
    Scanner fileScanner;
    ZipInputStream zipDataStream;
    ZipFile zipFile;
    ZipEntry entry;
    BodyContentHandler handler = new BodyContentHandler();
    Metadata meta = new Metadata();
    ParseContext contextParser = new ParseContext();
    PDFParser parser = new PDFParser();
    String error = "Error reading file";

    @Override
    public String readFile(String specFilePath, String specExtractionDirectory) {
        try {
            zippedSpecStream = new FileInputStream(specFilePath);
            zipFile = new ZipFile(specFilePath);
            // specFile = new File("ProjectTestData\\COMP2603_Assignment_1_2023.pdf");
            zipDataStream = new ZipInputStream(zippedSpecStream);
            fileScanner = new Scanner(zipDataStream);
            while ((entry = zipDataStream.getNextEntry()) != null) {
                parser.parse(zipDataStream, handler, meta, contextParser);
                // while (fileScanner.hasNextLine()) {
                // String data = fileScanner.nextLine();
                // System.out.println(data);
                // }
            }
            String str = handler.toString();
            System.out.println(str);
            zipDataStream.close();

            return str;

            /*
             * 
             * fileScanner = new Scanner(specFile);
             * System.out.println(specFile.getName());
             * System.out.println("here");
             * while (fileScanner.hasNextLine()) {
             * String data = fileScanner.nextLine();
             * System.out.println(data);
             * }
             * fileScanner.close();
             */
        } catch (Exception e) {
            // TODO: handle exception
            return error;
        }

    }

}
