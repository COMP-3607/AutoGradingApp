package autograding.TestObjectFactory;

import java.util.ArrayList;

public interface TestObject {
    public TestObject createTestObject(String className, ArrayList<Attribute> attributes, Method methods);

    public String toString();
}
