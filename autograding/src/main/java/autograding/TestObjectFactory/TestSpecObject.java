package autograding.TestObjectFactory;

import java.util.ArrayList;

public class TestSpecObject implements TestObject {
    String className;
    ArrayList<TestAttribute> attributes;
    ArrayList<TestMethod> methods;

    public TestSpecObject(String className, ArrayList<TestAttribute> attributes, ArrayList<TestMethod> methods) {
        this.className = className;
        this.attributes = attributes;
        this.methods = methods;
    }

}
