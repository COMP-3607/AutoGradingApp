package autograding.TestObjectFactory;

import java.util.ArrayList;

public class Method {
    private String name;
    private ArrayList<Attribute> parameters;
    private int marks;
    private String description;
    private String returnType;
    private String signature = "";

    public Method(String name, int marks, String description, String returnType) {
        this.name = name;
        this.marks = marks;
        this.description = description;
        this.returnType = returnType;
        this.parameters = new ArrayList<>();
    }

    public Method(String signature) {
        this.signature = signature;
    }

    // Add a parameter to the method
    public void addParameter(String name, String type) {
        Attribute parameter = new Attribute(name, type);
        parameters.add(parameter);
    }

    public String getName() {
        return name;
    }

    public String getReturnType() {
        return returnType;
    }

    public int getMarks() {
        return marks;
    }

    public ArrayList<Attribute> getParameters() {
        return parameters;
    }

    public String getDescription() {
        return description;
    }

    public String getSignature() {
        return signature;
    }
}
