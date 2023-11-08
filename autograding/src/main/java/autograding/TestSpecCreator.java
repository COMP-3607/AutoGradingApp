package autograding;

public class TestSpecCreator extends TestObjectCreator {
    private Character ch;
    private int count = 0;
    private int num;
    private int len;
    private String className;
    private String temp = "";
    private char[] x;
    private boolean found = false;

    public TestSpecCreator() {
    }

    @Override
    public TestObject createTestObject(String specText) {
        // ch=specText.charAt(0);
        len = specText.length();
        while (count != len) {
            // System.out.print(specText.charAt(count));
            // if (specText.charAt(count) == 'C' && specText.charAt(count = count + 1) ==
            // 'l') {
            if (specText.charAt(count) == 'C') {
                /*
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
                 */
            }
            found = false;
            count = count + 1;
        }

        return null;
    }

}
