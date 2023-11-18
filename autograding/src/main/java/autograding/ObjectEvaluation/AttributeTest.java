package autograding.ObjectEvaluation;
/* 
import autograding.AssignFactory.Attribute;

//import org.junit.Assert.assertEquals;

import  org.junit.jupiter.api.assertEquals

public class AttributeTest extends Test {
    private String result;
    private Attribute tested;      // Submission Attribute
    private Attribute tester;      // Scheme Attribute

    public AttributeTest(Attribute scheme, Attribute sub) {
        this.tester = scheme;
        this.tested = sub;
    }

    @Override
    protected void execute() {
        if(assertEquals(tester,tested)==true){
            result=tester.getName()+":Passed";
        }
        else{
            result=tester.getName()+":Failed";
        }
    }

    @Test


    @Override
    protected String result() {
        return result;
    }

}*/

//package autograding;
/*
Rayhan Code
package autograding.ObjectEvaluation;

import java.util.ArrayList;

// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;
// import autograding.AssignFactory;
import autograding.ObjectEvaluation.AttributeTest;
import autograding.TestObjectFactory.CreateClass;

public class AttributeTest extends Test {
    // private CreateClass obj;
    private int jListSize = 0;
    private int sListSize = 0;

    @Override
    public void execute(ArrayList<CreateClass> specObjects, ArrayList<CreateClass> javaObjects) {
        jListSize = javaObjects.size();
        sListSize = specObjects.size();
        System.out.println(jListSize);
        System.out.println(sListSize);
        // compareAttributeName();
        // compareAttributeType();

        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public String result() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'result'");
    }

    public boolean compareAttributeName() {
        return false;
    }

    public boolean compareAttributeType() {
        return false;
    }
}*/

//Testing new code:

import java.util.ArrayList;

import autograding.TestObjectFactory.CreateClass;

public class AttributeTest extends Test {

    private int jListSize = 0;
    private int sListSize = 0;

    @Override
    public void execute(ArrayList<CreateClass> specObjects, ArrayList<CreateClass> javaObjects) {
        jListSize = javaObjects.size();
        sListSize = specObjects.size();
        System.out.println(jListSize);
        System.out.println(sListSize);

        // Compare spec object names and Java object names
        for (int i = 0; i < sListSize; i++) {
            // String specObjectName = specObjects.get(i).getName();
            // String javaObjectName = javaObjects.get(i).getName();
            /*
             * 
             * if (!specObjectName.equals(javaObjectName)) {
             * System.err.println("Spec object name and Java object name mismatch at index "
             * + i);
             * //return false;
             * }
             */
        }

        // return null;
    }

    @Override
    public String result() {
        return "AttributeTest execution completed";
    }

    public boolean compareAttributeName() {
        return false;
    }

    public boolean compareAttributeType() {
        return false;
    }
    /*
     * 
     * @Override
     * public void execute(ArrayList<CreateClass> specObjects,
     * ArrayList<CreateClass> javaObjects) {
     * // TODO Auto-generated method stub
     * throw new UnsupportedOperationException("Unimplemented method 'execute'");
     * }
     */
}