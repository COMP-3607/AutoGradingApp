package autograding.TestObjectFactory;

import java.util.ArrayList;

public class Class implements CreateClass {
    private String className;
    private ArrayList<Attribute> attributes;
    private Method methods;

    public Class(String className, ArrayList<Attribute> attributes, Method methods) {
        this.className = className;
        this.attributes = attributes;
        this.methods = methods;
    }

    public String getClassName() {
        return this.className;
    }

    public ArrayList<Attribute> getAttributes() {
        return this.attributes;
    }

    public Method getMethod() {
        return this.methods;
    }

    public String toString() {
        String line = "";
        for (Attribute x : attributes) {
            line += x.getName() + " ";
        }
        return "Classname is: " + className + " " + line + " " + methods.getSignature();
    }

}
