package autograding;

import java.io.File;

import autograding.DataReader.SpecReader;
import autograding.DataReader.SubmissionReader;
import autograding.TestObjectFactory.TestJavaCreator;
import autograding.TestObjectFactory.TestSpecCreator;

public class AutoGrader {
    private SpecReader specReader;
    private SubmissionReader javaReader;
    private double score;
    private String specText;
    private String subContent;
    private TestSpecCreator specMaker;
    private TestJavaCreator javaMaker;
    String specDocumentName = "specDoc.txt";
    String javaTextDocument = "javaDoc.txt";
    File document;

    public AutoGrader() {
        specReader = new SpecReader();
        javaMaker = new TestJavaCreator();
        specMaker = new TestSpecCreator();
        javaReader = new SubmissionReader();
        score = 0;
    }

    public double grader(String specFilePath, String specFolder, String specExtractionDirectory, String javaZipFilePath,
            String javaExtractionDirectory) {

        specText = specReader.readFile(specFilePath, specFolder, specExtractionDirectory);
        // System.out.println("PDF Contents::\n"+ specText);

        subContent = javaReader.readFile(specFilePath, specFolder, specExtractionDirectory);
        // System.out.println("Java Contents:\n"+ subContent);

        specMaker.createTestObject(specDocumentName, document, specText);
        javaMaker.createTestObject(javaTextDocument, document, subContent);
        return score;
    }
}
