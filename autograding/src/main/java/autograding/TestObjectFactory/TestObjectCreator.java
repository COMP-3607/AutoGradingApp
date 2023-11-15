package autograding.TestObjectFactory;

import java.io.File;
import java.util.ArrayList;

public abstract class TestObjectCreator {
    private String specText;
    private TestJavaCreator javaCreator;
    private TestSpecCreator specCreator;
    private ArrayList<TestAttribute> attributes;
    private ArrayList<TestMethod> methods;

    public TestObjectCreator() {

    }

    public TestObjectCreator(String specText) {
        this.specText = specText;
    }

    public abstract ArrayList<TestObject> createTestObject(String documentName, File document, String specText);

    public abstract File createTxtFile(String documentName);

    public abstract void writeToFile(File document, String specText);

    public abstract String findClassName(String line, int count);

    public abstract String findAccessModifier(String line, int index);

    public abstract String findAttributeName(String line, int count);

    public abstract String findAttributeType(String line, int index);
}
