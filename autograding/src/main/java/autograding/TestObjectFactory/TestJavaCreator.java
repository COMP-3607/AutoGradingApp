package autograding.TestObjectFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class TestJavaCreator extends TestObjectCreator {
    File javaFile;
    Scanner scanner;
    FileWriter writer;

    String javaCode = "import java.util.Random;\r\n" + //
            "public class Passenger{\r\n" + //
            "    //Declarations\r\n" + //
            "    private String passportNumber;\r\n" + //
            "    private String flightNo;\r\n" + //
            "    private String firstName;\r\n" + //
            "    private String lastName;\r\n" + //
            "    private int numLuggage;\r\n" + //
            "    private char cabinClass;\r\n" + //
            "    private Random rand;\r\n" + //
            "    \r\n" + //
            "    //constructor\r\n" + //
            "        public Passenger(String passportNumber,String firstName, String lastName,String flightNo){\r\n" + //
            "        this.passportNumber=passportNumber;\r\n" + //
            "        this.firstName=firstName;\r\n" + //
            "        this.lastName=lastName;\r\n" + //
            "        this.flightNo=flightNo;\r\n" + //
            "        assignRandomCabinClass();\r\n" + //
            "        }\r\n" + //
            "    \r\n" + //
            "    //accessors\r\n" + //
            "        public String getPassportNumber(){\r\n" + //
            "        return this.passportNumber;\r\n" + //
            "        }\r\n" + //
            "    \r\n" + //
            "        public String getFirstName(){\r\n" + //
            "        return this.firstName;\r\n" + //
            "        }\r\n" + //
            "        \r\n" + //
            "        public String getLastName(){\r\n" + //
            "        return this.lastName;\r\n" + //
            "        }\r\n" + //
            "        \r\n" + //
            "        public String getFlightNo(){\r\n" + //
            "        return this.flightNo;\r\n" + //
            "        }\r\n" + //
            "        \r\n" + //
            "        public int getNumLuggage(){\r\n" + //
            "        return this.numLuggage;\r\n" + //
            "        }\r\n" + //
            "        \r\n" + //
            "        public char getCabinClass(){\r\n" + //
            "        return this.cabinClass;\r\n" + //
            "        }\r\n" + //
            "        \r\n" + //
            "    //methods\r\n" + //
            "        public void assignRandomCabinClass(){\r\n" + //
            "            char cabin[]={'F','B','P','E'};\r\n" + //
            "            int luggage[]={3,2,1,0};\r\n" + //
            "            int rc=new Random().nextInt(cabin.length);\r\n" + //
            "            int rl=new Random().nextInt(luggage.length);\r\n" + //
            "            this.cabinClass=cabin[rc];\r\n" + //
            "            this.numLuggage=luggage[rl];\r\n" + //
            "        }\r\n" + //
            "        \r\n" + //
            "        public String toString(){\r\n" + //
            "            return \"PP NO. \"+this.getPassportNumber()+\" NAME: \"+this.getFirstName().charAt(0) + \".\" + this.getLastName().toUpperCase() \r\n"
            + //
            "                        + \" NUMLUGGAGE: \" +this.getNumLuggage()+\" \"+ \" CLASS: \" +this.getCabinClass();\r\n"
            + //
            "        }\r\n" + //
            "        \r\n" + //
            "}\r\n" + //
            "";
    private int openCount = 0;
    private int closeCount = 0;
    private String className = "";
    private String access = "";
    private String type = "";
    private boolean signal = false;
    String tempMethod = "";
    String method = "";
    boolean openFound = false;
    private Attribute attribute;
    private ArrayList<Attribute> attributes = new ArrayList<Attribute>();

    public String findClassName(String line) {
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

    public String findAccessModifier(String line, boolean signal) {
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

        // loc++;
        // }
        // return value;
        // }
        return null;
    }

    public String findAttributeName(String line) {

        return null;
    }

    public String findAttributeType(String line) {
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

        // return value;

        return null;
    }

    public String findMethod(String line) {
        // if (line.indexOf('}') != -1) {
        // System.out.println("HELLO");

        // }
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
    public ArrayList<TestObject> createTestObject(String documentName, File document, String specText) {
        String methodString = "";
        try {
            javaFile = new File("JavaData.txt");
            writer = new FileWriter(javaFile);
            writer.write(javaCode);
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            scanner = new Scanner(javaFile);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                if ((data.indexOf("class") != -1 && data.indexOf('{') != -1)) {
                    // || (data.indexOf("extends") != -1 || data.indexOf("implements") != 1)) {
                    // System.out.println("HEREE");
                    className = findClassName(data);
                    System.out.println(className);
                }

                access = findAccessModifier(data, signal);
                if (access != null) {
                    System.out.println(access);
                }

                type = findAttributeType(data);
                if (type != null) {
                    System.out.println("type is " + type);
                }

                methodString = findMethod(data);
                if (methodString != null) {
                    method = "";
                    // System.out.println(methodString);

                }

            }
            scanner.close();
        } catch (

        FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public File createTxtFile(String documentName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTxtFile'");
    }

    @Override
    public void writeToFile(File document, String specText) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeToFile'");
    }

    @Override
    public String findClassName(String line, int count) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findClassName'");
    }

    @Override
    public String findAccessModifier(String line, int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAccessModifier'");
    }

    @Override
    public String findAttributeName(String line, int count) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAttributeName'");
    }

    @Override
    public String findAttributeType(String line, int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAttributeType'");
    }

}
