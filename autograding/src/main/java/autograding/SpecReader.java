package autograding;

import java.util.Enumeration;
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

import com.coremedia.iso.boxes.CompositionTimeToSample.Entry;

import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.parser.ParseContext;

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
    private String fileName = "COMP2603_Assignment_1_2023.pdf";
    String str;
    // private FileInputStream stream;
    int ch;
    byte bytes[];
    InputStream stream;

    @Override
    public String readFile(String specFilePath, String specFolder, String specExtractionDirectory) {
        try {
            zippedSpecStream = new FileInputStream(specFilePath);

            // folder = new File(specFolder);
            // System.out.println("HEREE33333");
            zipFile = new ZipFile(specFilePath);
            // specFile = new File("ProjectTestData\\COMP2603_Assignment_1_2023.pdf");
            zipDataStream = new ZipInputStream(zippedSpecStream);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                entry = entries.nextElement();
                stream = zipFile.getInputStream(entry);
                if (entry.getName().startsWith("COMP")) {
                    parser.parse(stream, handler, meta, contextParser);
                }
            }
            str = handler.toString();
            stream.close();
            return str;
            // zipDataStream.close();
            // stream= new FileInputStream(folderSpecStream);
            // fileScanner = new Scanner(zipDataStream);
            /*
             * 
             * entry = zipDataStream.getNextEntry();
             * while (entry != null) {
             * // System.out.println(entry.getName());
             * if (!entry.isDirectory()) {
             * // folderSpecStream = new FileInputStream(entry.getName());
             * // stream = new FileInputStream(entry.getName());
             * 
             * // System.out.println(entry.getName());
             * if (entry.getName().startsWith("COMP")) {
             * String x = "\\" + entry.getName();
             * System.out.println("\\TestData.zip" + x);
             * file = new File(specFolder+x);
             * // file.getParentFile().mkdirs();
             * System.out.println(file.canRead());
             * System.out.println(file.exists());
             * stream = new FileInputStream(file);
             * 
             * System.out.println(stream.toString());
             * 
             * parser.parse(stream, handler, meta, contextParser);
             * }
             * 
             * }
             * 
             * // while (fileScanner.hasNextLine()) {
             * // String data = fileScanner.nextLine();
             * // System.out.println(data);
             * // }
             * entry = zipDataStream.getNextEntry();
             * }
             * String str = handler.toString();
             * System.out.println("DATA IS " + str);
             * zipDataStream.close();
             * 
             * // return str;
             */

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

        return str;
    }

}
