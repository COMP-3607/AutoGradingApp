package autograding.TestObjectFactory;

import java.io.File;
import java.util.ArrayList;

public abstract class TestObjectCreator {
    private String specText;
    private TestObject object;
    private TestJavaCreator javaCreator;
    private TestSpecCreator specCreator;
    private ArrayList<Attribute> attributes;
    private ArrayList<Method> methods;

    public TestObjectCreator() {

    }

    public TestObjectCreator(String specText) {
        this.specText = specText;
    }

    public ArrayList<TestObject> createTestObject(String documentName, File document, String specText) {
        return null;
    }

    public abstract File createTxtFile(String documentName);

    public abstract void writeToFile(File document, String specText);

    public abstract String findClassName(String line, int count);

    public abstract String findAccessModifier(String line, int index);

    public abstract String findAttributeName(String line, int count);

    public abstract String findAttributeType(String line, int index);
}
