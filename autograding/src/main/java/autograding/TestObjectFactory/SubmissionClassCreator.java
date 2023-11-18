package autograding.TestObjectFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class SubmissionClassCreator extends TextAnalyzer {
    private Scanner scanner;
    private FileWriter writer;
    private String className = "";
    private int openCount = 0;
    private int closeCount = 0;
    private String access = "";
    private String type = "";
    private String varName = "";
    private String tempMethod = "";
    private String method = "";
    private boolean openFound = false;
    private Attribute attribute;
    private ArrayList<Attribute> attributes = new ArrayList<Attribute>();
    private Method methodObj;
    private CreateClass javaObject = null;
    private ArrayList<CreateClass> javaObjects = new ArrayList<CreateClass>();
    private File jFile;

    public CreateClass createTestObject(String className, ArrayList<Attribute> attributes, Method methods) {
        return new Class(className, attributes, methods);
    }

    public String findMethod(String line) {
        if (line.indexOf("private") != -1 || line.indexOf("public") != -1 || line.indexOf("protected") != -1) {
            if (line.indexOf('(') != -1 && line.indexOf('{') != -1) {
                openFound = true;
            }
        }
        if (line.indexOf('}') != -1 && line.indexOf(';') == -1) {
            openFound = false;
            return method;
        }
        if (openFound == true) {
            tempMethod = line;
            if (tempMethod.indexOf(';') != -1) {
                tempMethod = tempMethod.replace(';', ' ');
            }
            tempMethod = tempMethod.trim();
            method = method.concat(" " + tempMethod);
        }
        return null;
    }

    @Override
    public ArrayList<CreateClass> createTestObject(String documentName, File document, String javaText) {
        String temp = "";
        String tempLine = "";
        String temp2;
        String tempMethodString = "";
        ArrayList<String> methodList = new ArrayList<String>();
        String methodString = "";

        // Method call to create and write submitted java code to a text document
        writeToFile(document = createTxtFile(documentName), javaText);
        try {
            scanner = new Scanner(jFile);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                // Condition to increment a counter
                if (data.indexOf('{') != -1) {
                    openCount++;
                }
                // Condition to increment a counter
                if (data.indexOf('}') != -1) {
                    closeCount++;
                }
                // Condition for method call to find a class name on a line if present
                if ((data.indexOf("class") != -1 && data.indexOf('{') != -1)) {
                    className = findClassName(data, 0);
                }
                // Method call to find an attribute's access modifier on a line if present
                temp = findAccessModifier(data, 0);
                if (temp != null) {
                    access = temp;
                    // Method call to find an attribute's type on a line if present
                    temp = findAttributeType(data, 0);
                    if (temp != null) {
                        type = temp;
                    }
                    // Method call to find an attribute's name on a line if present
                    temp = findAttributeName(data, 0);
                    if (temp != null) {
                        varName = temp;
                    }
                    // Condition for creating Attribute objects for each class object
                    if (varName != null && type != null && access != null) {
                        attribute = new Attribute(varName, type, access);
                        attributes.add(attribute);
                    }
                }
                // Method call to find a method of a class and add it to an arrayList
                temp2 = findMethod(data);
                if (temp2 != null) {
                    methodList.add(temp2);
                    method = "";
                }

                // Condition to find the end of a class
                if (openCount == closeCount && openCount != 0 && closeCount != 0) {
                    openCount = 0;
                    closeCount = 0;
                    methodString = "";
                    // merge all methods in arraylist into one string
                    for (String x : methodList) {
                        tempMethodString = x;
                        methodString = methodString + tempMethodString;
                    }
                    // create method object for a class
                    methodObj = new Method(methodString);
                    if (methodObj.getSignature() != "") {
                        // Method call to crete a java submission object for testing
                        javaObject = createTestObject(className, attributes, methodObj);
                    }
                    methodList.clear();
                    // condition for adding java objects to an arrayList
                    if (javaObject != null) {
                        javaObjects.add(javaObject);
                    }
                }
                if (data.trim().startsWith("}")) {
                    tempLine = data.trim();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return javaObjects;
    }

    @Override
    public File createTxtFile(String documentName) {
        jFile = new File(documentName);
        return jFile;
    }

    @Override
    public void writeToFile(File document, String javaText) {
        try {
            writer = new FileWriter(document.getName());
            writer.write(javaText);
            writer.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String findClassName(String line, int count) {
        int index = 0;
        String value = "";
        index = line.indexOf("class");
        if (index == -1) {
            return null;
        }
        while (index != line.length()) {
            if (line.charAt(index) == ' ') {
                index++;
                if (index >= line.length()) {
                    index = line.length();
                }
                while (line.charAt(index) != ' ' && line.charAt(index) != '{') {
                    value = value.concat(Character.toString(line.charAt(index)));
                    index++;
                }
                return value;
            }
            index++;
        }
        return null;
    }

    @Override
    public String findAccessModifier(String line, int index) {
        int loc = 0;
        String value = "";
        if (line.indexOf("private") != -1 || line.indexOf("public") != -1 || line.indexOf("protected") != -1) {
            if (line.indexOf('{') != -1) {
                return null;
            }
            line = line.trim();
            while (line.charAt(loc) != ' ') {
                value = value.concat(Character.toString(line.charAt(loc)));
                loc++;
            }
            return value;
        }
        return null;
    }

    @Override
    public String findAttributeName(String line, int count) {
        String value = "";
        String str = "";
        line = line.trim();
        int loc = line.length();
        loc = loc - 1;
        if (line.indexOf("private") != -1 || line.indexOf("public") != -1 || line.indexOf("protected") != -1) {
            if (line.indexOf(';') != -1) {
                while (line.charAt(loc) != ' ') {
                    value = value.concat(Character.toString(line.charAt(loc)));
                    loc--;
                }
                for (int i = 0; i < value.length(); i++) {
                    str = value.charAt(i) + str;
                }
                str = str.replace(';', ' ');
                str = str.trim();
                return str;
            }
        }
        return null;
    }

    @Override
    public String findAttributeType(String line, int index) {
        String value = "";
        int loc = 0;
        line = line.trim();
        if (line.indexOf("private") != -1 || line.indexOf("public") != -1 || line.indexOf("protected") != -1) {
            if (line.indexOf(';') != -1) {
                while (loc < line.length()) {
                    if (line.charAt(loc) == ' ') {
                        loc++;
                        while (line.charAt(loc) != ' ') {
                            value = value.concat(Character.toString(line.charAt(loc)));
                            loc++;
                        }
                        return value;
                    }
                    loc++;
                }
            }
        }
        return null;
    }

}
