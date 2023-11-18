package autograding.ObjectEvaluation;

import java.util.ArrayList;

import autograding.TestObjectFactory.CreateClass;

public abstract class Test {
    /*
     * 
     * public void runTest(ArrayList<CreateClass> specObjects,ArrayList<CreateClass>
     * javaObjects) {
     * 
     * execute(ArrayList<CreateClass> specObjects,ArrayList<CreateClass>
     * javaObjects);
     * result();
     * }
     */

    public abstract void execute(ArrayList<CreateClass> specObjects, ArrayList<CreateClass> javaObjects);

    public abstract String result();
}
