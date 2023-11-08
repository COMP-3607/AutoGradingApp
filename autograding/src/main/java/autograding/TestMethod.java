package autograding;

import java.util.ArrayList;

import ucar.units.Test;

public class TestMethod {
    private String name;
    private ArrayList<TestAttribute> parameters;
    private int marks;
    private String description;
    private String returnType;

    public TestMethod(String name, int marks, String description, String returnType) {
        this.name = name;
        this.marks = marks;
        this.description = description;
        this.returnType = returnType;
        this.parameters = new ArrayList<>();
    }

    // Add a parameter to the method
    public void addParameter(String name, String type) {
        TestAttribute parameter = new TestAttribute(name, type);
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

    public ArrayList<TestAttribute> getParameters() {
        return parameters;
    }

    public String getDescription() {
        return description;
    }
}
