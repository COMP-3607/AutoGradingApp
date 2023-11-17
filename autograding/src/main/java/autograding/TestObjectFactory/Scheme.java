package autograding.TestObjectFactory;

import java.util.ArrayList;

public class Scheme implements CreateClass {
    private String className;
    private ArrayList<Attribute> attributes;
    private Method methods;

    public Scheme() {

    }

    public Scheme(String className, ArrayList<Attribute> attributes, Method methods) {
        this.className = className;
        this.attributes = attributes;
        this.methods = methods;
    }

    public String toString() {
        String line = "";
        for (Attribute x : attributes) {
            line += x.getName() + " ";
        }
        return "Classname is: " + className + " " + line + " " + methods.getSignature();
    }
    /*
     * 
     * @Override
     * public CreateClass createTestObject(String className, ArrayList<Attribute>
     * attributes, Method methods) {
     * return new Class(className, attributes, methods);
     * }
     */

}
