package autograding;

import java.util.Scanner;
//import java.util.zip;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.parser.ParseContext;

public class SpecReader implements FileReader {
    private FileInputStream zippedSpecStream = null;
    private File specFile;
    private Scanner fileScanner;
    private ZipInputStream zipDataStream;
    private ZipFile zipFile;
    private ZipEntry entry;
    private BodyContentHandler handler = new BodyContentHandler();
    private Metadata meta = new Metadata();
    private ParseContext contextParser = new ParseContext();
    private PDFParser parser = new PDFParser();
    private String error = "Error reading file";
    private FileInputStream folderSpecStream;
    private File file;
    // private FileInputStream stream;
    int ch;
    byte bytes[];

    @Override
    public void readFile(String specFilePath, String specFolder, String specExtractionDirectory) {
        try {
            zippedSpecStream = new FileInputStream(specFilePath);
            // folderSpecStream = new FileInputStream(specFolder);

            zipFile = new ZipFile(specFilePath);
            // specFile = new File("ProjectTestData\\COMP2603_Assignment_1_2023.pdf");
            zipDataStream = new ZipInputStream(zippedSpecStream);
            // fileScanner = new Scanner(zipDataStream);
            while ((entry = zipDataStream.getNextEntry()) != null) {
                System.out.println("HEREE" + entry.getName());
                // stream = zipFile.getInputStream(entry);
                file = new File(entry.getName());

                System.out.println(file.canRead());
                FileInputStream stream = new FileInputStream(file);

                System.out.println("HEREE");

                parser.parse(stream, handler, meta, contextParser);

                // while (fileScanner.hasNextLine()) {
                // String data = fileScanner.nextLine();
                // System.out.println(data);
                // }
            }
            String str = handler.toString();
            System.out.println(str);
            zipDataStream.close();

            // return str;

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
            // return error;
        }

    }

}
