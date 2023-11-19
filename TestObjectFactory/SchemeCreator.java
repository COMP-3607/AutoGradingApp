package autograding.TestObjectFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SchemeCreator extends TextAnalyzer {
    private Attribute attribute = null;
    private ArrayList<Attribute> attributes = new ArrayList<Attribute>();
    private Method method;
    private CreateClass specObject = null;
    private ArrayList<CreateClass> specObjects = new ArrayList<CreateClass>();
    private int count = 0;
    private int num;
    private int len;
    private String className = "";
    private String tempClassName = "";
    private String varName = "";
    private String temp = "";
    private String temp2 = "";
    private String line;
    private String access = "";
    private int mark;
    private String tempSigLine = "";
    private String SigLine = "";
    private int attributeLen;
    private int typelen;
    private String type = "";
    private FileWriter writer;
    private File spec;
    private Scanner scanner;
    private boolean signal = false;
    private String signatures = "";

    public SchemeCreator() {
    }

    @Override
    public CreateClass createTestObject(String className, ArrayList<Attribute> attributes, Method methods) {
        return new Scheme(className, attributes, methods);
    }

    public File createTxtFile(String documentName) {
        spec = new File(documentName);
        return spec;
    }

    public void writeToFile(File document, String specText) {
        try {
            writer = new FileWriter(document.getName());
            writer.write(specText);
            writer.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String findClassName(String line, int count) {
        int x = count + 1;
        if (line.charAt(count) == 'C' && line.charAt(x) == 'l') {
            num = count - 1;
            if (num != -1) {
                if (line.charAt(num) == ' ') {
                    num = num - 1;
                    temp = "";
                    while (num != -1) {
                        int y = num + 1;
                        if (line.charAt(num) != ' ') {
                            temp = temp.concat(Character.toString(line.charAt(num)));
                        } else {
                            num = 0;
                        }
                        num--;
                    }
                    className = "";
                    for (int i = 0; i < temp.length(); i++) {
                        className = temp.charAt(i) + className;
                    }
                    return className;
                }
            }
        }
        return null;
    }

    public String findAccessModifier(String line, int index) {
        index = index + 2;
        if (line.charAt(index) != ' ') {
            int x = index + 1;
            int xx = index + 2;
            if (line.charAt(index) == 'P' && line.charAt(x) == 'r' && line.charAt(xx) == 'i') {
                return "Private";
            }
            if (line.charAt(count) == 'P' && line.charAt(x) == 'u' && line.charAt(xx) == 'b') {
                return "Public";
            }
        }
        return null;
    }

    public String findAttributeName(String line, int count) {
        int x = count + 1;
        int xx = x + 1;
        if (xx < len || x < len) {
            if ((line.charAt(count) == 'P' && line.charAt(x) == 'r' && line.charAt(xx) == 'i')
                    || (line.charAt(count) == 'P' && line.charAt(x) == 'u' && line.charAt(xx) == 'b')) {
                int start = 0;
                temp2 = "";
                while (line.charAt(start) != ' ') {
                    temp2 = temp2.concat(Character.toString(line.charAt(start)));
                    start++;
                }
                return temp2;
            }
        }
        return null;
    }

    public String findAttributeType(String line, int index) {
        if (line.charAt(index) == ' ') {
            index++;
            type = "";
            while (line.charAt(index) != ' ') {
                type = type.concat(Character.toString(line.charAt(index)));
                index++;
            }
            return type;
        }
        return null;
    }

    public int findAttributeMark(String line, int index) {
        if (line.charAt(index) != ' ') {
            char tempNum = line.charAt(index);
            return Character.getNumericValue(tempNum);
        }
        return -1;
    }

    public String findMethodSignatures(String line) {
        int result = 0;
        result = line.indexOf(" Class");
        if (result != -1 && signal == true) {
            signal = false;
            return SigLine;
        }
        result = line.indexOf(" Signature");
        if (result != -1) {
            signal = true;
        }
        if (signal == true && line != "") {
            tempSigLine = line;
            SigLine = SigLine.concat(" " + tempSigLine);
        }
        if (signal == false) {
            return "";
        }
        return "";
    }

    @Override
    public ArrayList<CreateClass> createTestObject(String documentName, File document, String specText) {
        writeToFile(document = createTxtFile(documentName), specText);
        try {
            scanner = new Scanner(document);
            while (scanner.hasNextLine()) {
                count = 0;
                line = scanner.nextLine();
                len = line.length();
                signatures = findMethodSignatures(line);
                if (signatures != "" && signal == false) {
                    method = new Method(signatures);
                    SigLine = "";
                    signatures = "";
                    if (className != null) {
                        specObject = createTestObject(className, attributes, method);
                        specObjects.add(specObject);
                    }
                }
                while (count != len) {
                    tempClassName = findClassName(line, count);
                    if (tempClassName != null) {
                        className = tempClassName;
                    }
                    varName = findAttributeName(line, count);
                    if (varName != null) {
                        attributeLen = varName.length();
                        type = findAttributeType(line, attributeLen);
                        if (type != null) {
                            typelen = type.length();
                            typelen = typelen + 2 + attributeLen;
                            mark = findAttributeMark(line, typelen);
                            if (mark != -1) {
                                access = findAccessModifier(line, typelen);
                                attribute = new Attribute(varName, type, access, mark);
                                attributes.add(attribute);
                            }
                        }
                    }
                    count++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return specObjects;
    }
}
