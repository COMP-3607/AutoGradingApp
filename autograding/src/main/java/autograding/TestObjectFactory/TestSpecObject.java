package autograding.TestObjectFactory;

import java.util.ArrayList;

public class TestSpecObject implements TestObject {
    private String className;
    private ArrayList<TestAttribute> attributes;
    private TestMethod methods;

    public TestSpecObject(String className, ArrayList<TestAttribute> attributes, TestMethod methods) {
        this.className = className;
        this.attributes = attributes;
        this.methods = methods;
    }

    public String toString() {
        // TestAttribute x;
        String line = "";
        for (TestAttribute x : attributes) {
            line += x.getName();
        }
        return className + " " + line + " " + methods.getSignature();
    }

}
