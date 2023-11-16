package autograding.DataReader;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.zip.ZipInputStream;

public class SubmissionReader implements FileReader {
    private StringBuilder javaCode = new StringBuilder();

    public String readFile(String specFilePath, String specFolder, String specExtractionDirectory) {
        try (ZipFile zipFile = new ZipFile(specFilePath)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();

                if (entry.isDirectory()) {
                    // Skip processing directories
                    System.out.println("Folder found: " + entry.getName());
                    continue;
                }

                if (entry.getName().toLowerCase().endsWith(".zip")) {
                    System.out.println("Zipped folder found: " + entry.getName());
                    processZippedFolder(zipFile.getInputStream(entry));

                } else if (entry.getName().toLowerCase().endsWith(".java")) {
                    System.out.println("Java file found: " + entry.getName());
                    try (InputStream entryStream = zipFile.getInputStream(entry);
                         BufferedReader reader = new BufferedReader(new InputStreamReader(entryStream))) {
                        String code;
                        while ((code = reader.readLine()) != null) {
                            javaCode.append(code).append("\n");
                        }
                    }
                } /*else {
                    System.out.println("Other file found: " + entry.getName());
                }*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return javaCode.toString();
    }

    private void processZippedFolder(InputStream zipInputStream) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(zipInputStream)) {
            ZipEntry subEntry;
            while ((subEntry = zis.getNextEntry()) != null) {
                if (subEntry.isDirectory()) {
                    System.out.println("Folder found inside zipped folder: " + subEntry.getName());
                } else if (subEntry.getName().toLowerCase().endsWith(".java")) {
                    System.out.println("Java file found inside zipped folder: " + subEntry.getName());
                    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            baos.write(buffer, 0, len);
                        }
                        // Append Java contents to the StringBuilder
                        javaCode.append(baos.toString()).append("\n");
                    }
                } else {
                    System.out.println("Other file found inside zipped folder: " + subEntry.getName());
                }
            }
        }
    }
}
