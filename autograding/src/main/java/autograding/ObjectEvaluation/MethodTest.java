package autograding.ObjectEvaluation;
// import org.junit.Test;
// import java.util.ArrayList;

// import autograding.AssignFactory.*;
// import autograding.AssignFactory.Class;

// public class MethodTest extends Test {
// private String result;
// private Class beingTested;
// private Method tested; //Submission Method
// private Method tester; //Scheme Method

// public MethodTest(Method scheme,Method sub,Class beingTested){
// this.tester=scheme;
// this.beingTested = beingTested;
// this.tested=sub;

// }

// protected void execute() {
// if(isConstructor()==false && assertEquals(tester,tested)==true){
// result=tested.getName()+":Passed";
// }
// else{

// }

// }

// @Override
// protected String result() {
// return result;
// }

// public boolean compareSignature(){
// if(tester.getSignature().equals(tested.getSignature())){
// return true;
// }
// else{
// return false;
// }
// }

// public boolean isConstructor(){

// if(tested.getName().equals(beingTested.getName())){
// return true;
// }
// else{
// return false;
// }
// }
// }

/*
 * Geo Testing
 * 
 * package autograding;
 * 
 * import org.junit.jupiter.api.Test;
 * import static org.junit.jupiter.api.Assertions.*;
 * import autograding.AssignFactory.*;
 * import autograding.AssignFactory.Class;
 * 
 * public class MethodTestTest {
 * 
 * @Test
 * public void testExecutePass() {
 * Class beingTested = new Class("Test");
 * Method scheme = new Method("scheme", beingTested);
 * Method sub = new Method("scheme", beingTested);
 * 
 * MethodTest methodTest = new MethodTest(scheme, sub, beingTested);
 * methodTest.execute();
 * 
 * assertEquals("scheme:Passed", methodTest.result());
 * }
 * 
 * @Test
 * public void testExecuteFail() {
 * Class beingTested = new Class("Test");
 * Method scheme = new Method("scheme", beingTested);
 * Method sub = new Method("differentMethod", beingTested);
 * 
 * MethodTest methodTest = new MethodTest(scheme, sub, beingTested);
 * methodTest.execute();
 * 
 * assertEquals("scheme:Failed", methodTest.result());
 * }
 * 
 * @Test
 * public void testCompareSignaturePass() {
 * Class beingTested = new Class("Test");
 * Method scheme = new Method("scheme", beingTested, new ArrayList<>(), "void");
 * Method sub = new Method("scheme", beingTested, new ArrayList<>(), "void");
 * 
 * MethodTest methodTest = new MethodTest(scheme, sub, beingTested);
 * 
 * assertTrue(methodTest.compareSignature());
 * }
 * 
 * @Test
 * public void testCompareSignatureFail() {
 * Class beingTested = new Class("Test");
 * Method scheme = new Method("scheme", beingTested, new ArrayList<>(), "void");
 * Method sub = new Method("scheme", beingTested, new ArrayList<>(), "int");
 * 
 * MethodTest methodTest = new MethodTest(scheme, sub, beingTested);
 * 
 * assertFalse(methodTest.compareSignature());
 * }
 * 
 * @Test
 * public void testIsConstructorPass() {
 * Class beingTested = new Class("Test");
 * Method scheme = new Method("Test", beingTested, new ArrayList<>(), "void");
 * Method sub = new Method("Test", beingTested, new ArrayList<>(), "void");
 * 
 * MethodTest methodTest = new MethodTest(scheme, sub, beingTested);
 * 
 * assertTrue(methodTest.isConstructor());
 * }
 * 
 * @Test
 * public void testIsConstructorFail() {
 * Class beingTested = new Class("Test");
 * Method scheme = new Method("scheme", beingTested, new ArrayList<>(), "void");
 * Method sub = new Method("differentMethod", beingTested, new ArrayList<>(),
 * "void");
 * 
 * MethodTest methodTest = new MethodTest(scheme, sub, beingTested);
 * 
 * assertFalse(methodTest.isConstructor());
 * }
 * }
 */