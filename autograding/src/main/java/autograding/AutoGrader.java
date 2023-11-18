package autograding;

import java.io.File;
import java.util.ArrayList;

import autograding.TestObjectFactory.CreateClass;
import autograding.DataReader.SpecReader;
import autograding.DataReader.SubmissionReader;
import autograding.ObjectEvaluation.AttributeTest;
import autograding.TestObjectFactory.SubmissionClassCreator;
import autograding.TestObjectFactory.SchemeCreator;

public class AutoGrader {
    private SpecReader specReader;
    private SubmissionReader javaReader;
    private double score;
    private String specText;
    private String subContent;
    private SchemeCreator specMaker;
    private SubmissionClassCreator javaMaker;
    private ArrayList<CreateClass> specObjs = null;
    private ArrayList<CreateClass> javaObjs = null;
    private String specDocumentName = "specDoc.txt";
    private String javaTextDocument = "javaDoc.txt";
    private File document;
    private AttributeTest attributeEvaluator;

    public AutoGrader() {
        specReader = new SpecReader();
        javaMaker = new SubmissionClassCreator();
        specMaker = new SchemeCreator();
        javaReader = new SubmissionReader();
        attributeEvaluator = new AttributeTest();
        score = 0;
    }

    public double grader(String specFilePath, String specFolder, String specExtractionDirectory, String javaZipFilePath,
            String javaExtractionDirectory) {

        specText = specReader.readFile(specFilePath, specFolder, specExtractionDirectory);
        // System.out.println("PDF Contents::\n"+ specText);

        subContent = javaReader.readFile(specFilePath, specFolder, specExtractionDirectory);
        // System.out.println("Java Contents:\n"+ subContent);

        specObjs = specMaker.createTestObject(specDocumentName, document, specText);
        // System.out.println(specObjs.get(0).toString());
        javaObjs = javaMaker.createTestObject(javaTextDocument, document, subContent);
        attributeEvaluator.execute(specObjs, javaObjs);
        return score;
    }
}
