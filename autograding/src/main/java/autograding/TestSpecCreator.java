package autograding;

public class TestSpecCreator extends TestObjectCreator {
    private Character ch;
    private int count = 0;
    private int len;

    public TestSpecCreator() {
    }

    @Override
    public TestObject createTestObject(String specText) {
        // ch=specText.charAt(0);
        len = specText.length();
        System.out.println("HEREE");
        while (count != len) {
            System.out.print(specText.charAt(count));
            count = count + 1;
        }

        return null;
    }

}
