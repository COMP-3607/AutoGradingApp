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
    private String javaText;
    private TestJavaCreator javaMaker;
    private TestSpecCreator specMaker;
    String specDocumentName = "specDoc.txt";
    File document;

    public AutoGrader() {
        specReader = new SpecReader();
        specMaker = new TestSpecCreator();
        javaReader = new SubmissionReader();
        score = 0;
    }

    public double grader(String specFilePath, String specFolder, String specExtractionDirectory, String javaZipFilePath,
            String javaExtractionDirectory) {
        specText = specReader.readFile(specFilePath, specFolder, specExtractionDirectory);
        javaText = javaReader.readFile(specFilePath, specFolder, javaExtractionDirectory);
        System.out.println(javaText);
        specMaker.createTestObject(specDocumentName, document, specText);

        return score;
    }
}
