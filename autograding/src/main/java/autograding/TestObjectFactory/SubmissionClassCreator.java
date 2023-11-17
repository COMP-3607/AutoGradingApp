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
            // System.out.println("HEREEEE");
            openFound = false;
            return method;
        }
        if (openFound == true) {
            tempMethod = line;
            if (tempMethod.indexOf(';') != -1) {
                // System.out.println("HEREEEE");
                tempMethod = tempMethod.replace(';', ' ');
            }
            tempMethod = tempMethod.trim();
            method = method.concat(" " + tempMethod);
            // System.out.println("method is : " + method);
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
        writeToFile(document = createTxtFile(documentName), javaText);
        try {
            scanner = new Scanner(jFile);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();

                if (data.indexOf('{') != -1) {
                    openCount++;
                    // System.out.println(" { " + openCount);
                }
                if (data.indexOf('}') != -1) {
                    closeCount++;
                    // System.out.println(" } " + closeCount);
                }
                if ((data.indexOf("class") != -1 && data.indexOf('{') != -1)) {
                    // || (data.indexOf("extends") != -1 || data.indexOf("implements") != 1)) {
                    // System.out.println("HEREE");
                    className = findClassName(data, 0);
                    System.out.println(className);
                }
                temp = findAccessModifier(data, 0);
                if (temp != null) {
                    access = temp;
                    // System.out.println(access);
                    temp = findAttributeType(data, 0);

                    if (temp != null) {
                        type = temp;
                        // System.out.println(type);
                    }
                    temp = findAttributeName(data, 0);
                    if (temp != null) {
                        varName = temp;
                        // System.out.println(varName);
                    }
                    if (varName != null && type != null && access != null) {
                        // System.out.println(varName + " " + type + " " + access);
                        attribute = new Attribute(varName, type, access, -1);
                        attributes.add(attribute);
                    }
                }

                temp2 = findMethod(data);
                // methodString = temp2;
                if (temp2 != null) {
                    // tempMethodString = temp2;
                    // temp2.concat(temp2);
                    // methodString.concat(temp2);
                    // System.out.println(methodString);

                    methodList.add(temp2);

                    method = "";
                    // System.out.println("HEREE");
                    // temp2 = "";
                    // javaObject = new Class(className, attributes, methodObj);

                    // System.out.println(javaObjects.toString() + " ");
                    // methodString = "";

                    // System.out.println("HERE");
                    // System.out.println(methodObj.getSignature());

                }

                if (openCount == closeCount && openCount != 0 && closeCount != 0) {
                    openCount = 0;
                    closeCount = 0;
                    methodString = "";
                    for (String x : methodList) {
                        tempMethodString = x;
                        // methodString.concat(tempMethodString);
                        methodString = methodString + tempMethodString;
                    }
                    // System.out.println("methods are: " + methodString);
                    methodObj = new Method(methodString);

                    if (methodObj.getSignature() != "") {
                        javaObject = createTestObject(className, attributes, methodObj);
                        System.out.println(javaObject.toString());
                    }
                    methodList.clear();
                    if (javaObject != null) {
                        // System.out.println(javaObject.toString());
                        javaObjects.add(javaObject);
                    }
                }

                // if (temp != null && methodString != "") {

                // }
                // tempLine=data.trim();

                // if (data.trim().startsWith("}") && tempLine.indexOf('}') != -1) {

                // }

                // if (methodString != null && varName != null && type != null && access != null
                // && className != null) {

                // }
                if (data.trim().startsWith("}")) {
                    tempLine = data.trim();
                }

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // System.out.println("Size is :" + attributes.size());
        System.out.println("Size is :" + javaObjects.size());
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
                    // System.out.println(value);
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
