package autograding.TestObjectFactory;

import java.util.ArrayList;

public class TestJavaObject implements TestObject {
    private String className;
    private ArrayList<Attribute> attributes;
    private Method methods;

    public TestJavaObject(String className, ArrayList<Attribute> attributes, Method methods) {
        this.className = className;
        this.attributes = attributes;
        this.methods = methods;
    }

    public String toString() {
        // TestAttribute x;
        String line = "";
        for (Attribute x : attributes) {
            line += x.getName();
        }
        return className + " " + line + " " + methods.getSignature();
    }

    @Override
    public TestObject createTestObject(String className, ArrayList<Attribute> attributes, Method methods) {
        return new TestJavaObject(className, attributes, methods);
    }
}
