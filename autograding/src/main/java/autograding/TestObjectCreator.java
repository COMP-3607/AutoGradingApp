package autograding;

import java.io.File;
import java.util.ArrayList;

public class TestObjectCreator {
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

    public TestObject createTestObject(String documentName, File document, String specText) {

        return null;
    }
}
