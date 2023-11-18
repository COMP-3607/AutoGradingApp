package autograding.TestObjectFactory;

public class Attribute {
    private String name;
    private String type;
    private String accessModifier;
    private int marks;

    // Constructor for Class attributes
    public Attribute(String name, String type, String accessModifier, int marks) {
        this.name = name;
        this.type = type;
        this.accessModifier = accessModifier;
        this.marks = marks;
    }

    public Attribute(String name, String type, String accessModifier) {
        this.name = name;
        this.type = type;
        this.accessModifier = accessModifier;

    }

    // Constructor for method parameters with no description and marks
    public Attribute(String name, String type) {
        this.name = name;
        this.type = type;
        this.accessModifier = ""; // Set a default description as an empty string
        this.marks = 0; // Set a default of 0 marks
    }

    // Getters for all attributes
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public int getMarks() {
        return marks;
    }
}
