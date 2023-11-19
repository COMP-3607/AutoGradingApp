package autograding;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import autograding.TestObjectFactory.SchemeCreator;
import autograding.TestObjectFactory.SubmissionClassCreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SubmissionClassCreatorTest {
    SubmissionClassCreator submissionClassCreator = new SubmissionClassCreator();

    @Test
    public void testFindClassName() {
        File schemeFile = new File("testJavaData.txt");
        try {
            Scanner scanner = new Scanner(schemeFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // int len = line.length();
                int count = 0;

                assertEquals("Fish", submissionClassCreator.findClassName(line, count));
                count++;
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testFindAccessModifier() {
        File schemeFile = new File("testJavaData.txt");
        try {
            Scanner scanner = new Scanner(schemeFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int count = 0;
                assertEquals("private", submissionClassCreator.findAccessModifier(line, count));
                // count++;
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testfindAttributeName() {
        File schemeFile = new File("testJavaData.txt");
        try {
            Scanner scanner = new Scanner(schemeFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int count = 0;
                assertEquals("colour", submissionClassCreator.findAttributeName(line, count));
                // count++;
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testFindAttributeType() {
        File schemeFile = new File("testJavaData.txt");
        try {
            Scanner scanner = new Scanner(schemeFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int count = 0;
                assertEquals("String", submissionClassCreator.findAttributeType(line, count));
                // count++;
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
