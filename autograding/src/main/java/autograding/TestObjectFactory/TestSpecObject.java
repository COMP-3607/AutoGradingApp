package autograding.TestObjectFactory;

import java.util.ArrayList;

public class TestSpecObject implements TestObject {
    private String className;
    private ArrayList<Attribute> attributes;
    private Method methods;

    public TestSpecObject(String className, ArrayList<Attribute> attributes, Method methods) {
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

}
