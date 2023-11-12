package autograding;

import java.io.File;

public class TestJavaCreator extends TestObjectCreator {

    @Override
    public TestObject createTestObject(String documentName, File document, String specText) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTestObject'");
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
