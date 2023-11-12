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
            zipFile = new ZipFile(specFilePath);
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

        } catch (Exception e) {
            // TODO: handle exception
            // return error;
        }

        return str;
    }

}
