package autograding.TestObjectFactory;

import java.io.File;
import java.util.ArrayList;

public abstract class TextAnalyzer {
    private String specText;
    private CreateClass object;
    private SubmissionClassCreator javaCreator;
    private SchemeCreator specCreator;
    private ArrayList<Attribute> attributes;
    private ArrayList<Method> methods;

    public TextAnalyzer() {

    }

    public TextAnalyzer(String specText) {
        this.specText = specText;
    }

    // Method for creating and returning an array list of Class or Scheme objects
    // for testing
    public abstract ArrayList<CreateClass> createTestObject(String documentName, File document, String specText);

    public abstract File createTxtFile(String documentName);

    public abstract void writeToFile(File document, String specText);

    public abstract String findClassName(String line, int count);

    public abstract String findAccessModifier(String line, int index);

    public abstract String findAttributeName(String line, int count);

    public abstract String findAttributeType(String line, int index);

    public abstract CreateClass createTestObject(String className, ArrayList<Attribute> attributes, Method methods);

}
