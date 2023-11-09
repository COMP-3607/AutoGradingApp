package autograding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TestSpecCreator extends TestObjectCreator {
    private Character ch;
    private int count = 0;
    private int num;
    private int len;
    private String className = "";
    private String temp = "";
    private char[] x;
    private boolean found = false;
    private ArrayList<String> list;
    String line;
    FileWriter writer;
    File spec;
    Scanner scanner;

    public TestSpecCreator() {
    }

    @Override
    public TestObject createTestObject(String specText) {
        // ch=specText.charAt(0);
        // list = new ArrayList<String>();
        spec = new File("specDoc.txt");
        try {
            writer = new FileWriter(spec.getName());
            writer.write(specText);
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            scanner = new Scanner(spec);
            while (scanner.hasNextLine()) {
                count = 0;
                line = scanner.nextLine();
                len = line.length();
                while (count != len) {
                    // && line.charAt(count++) == 'l'
                    int x = count + 1;
                    if (line.charAt(count) == 'C' && line.charAt(x) == 'l') {
                        System.out.println("HEREEEEEEE");
                        num = count - 1;
                        if (num != -1) {
                            if (line.charAt(num) == ' ') {

                                num = num - 1;
                                while (num != -1) {
                                    int y = num + 1;
                                    // if (line.charAt(y) == ' ') {
                                    if (line.charAt(num) != ' ') {
                                        temp = temp.concat(Character.toString(line.charAt(num)));
                                    } else {
                                        num = 0;
                                    }
                                    num--;
                                    // }
                                }
                                for (int i = 0; i < temp.length(); i++) {
                                    className = temp.charAt(i) + className;
                                }
                                System.out.println("Class Name: " + className);
                                temp = "";
                                className = "";
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
        /*
         * while (count != len) {
         * // System.out.print(specText.charAt(count));
         * // if (specText.charAt(count) == 'C' && specText.charAt(count = count + 1) ==
         * // 'l') {
         * if (specText.charAt(count) == 'C') {
         * 
         * System.out.println("HEREE");
         * num = count;
         * while (num != 0 || found == true) {
         * if (specText.charAt(num) != ' ') {
         * while (specText.charAt(num) != ' ') {
         * temp = temp.concat(Character.toString(specText.charAt(num)));
         * num--;
         * }
         * found = true;
         * for (int i = 0; i < temp.length(); i++) {
         * className = temp.charAt(i) + className;
         * }
         * System.out.println("Class Name: " + className);
         * }
         * num--;
         * }
         * }
         * found = false;
         * count = count + 1;
         * }
         */

        return null;
    }

}
