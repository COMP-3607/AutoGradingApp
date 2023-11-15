package autograding.TestObjectFactory;

public class TestAttribute implements TestObject {
    private String name;
    private String type;
    private String description;
    private int marks;

    // Constructor for Class attributes
    public TestAttribute(String name, String type, String description, int marks) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.marks = marks;
    }

    // Constructor for method parameters with no description and marks
    public TestAttribute(String name, String type) {
        this.name = name;
        this.type = type;
        this.description = ""; // Set a default description as an empty string
        this.marks = 0; // Set a default of 0 marks
    }

    // Getters for all attributes
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getMarks() {
        return marks;
    }
}
