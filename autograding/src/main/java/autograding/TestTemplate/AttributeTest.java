package autograding.TestTemplate;

import autograding.AssignFactory.Attribute;


public class AttributeTest extends Test {
    private String result;
    private Attribute tested;      // Submission Attribute
    private Attribute tester;      // Scheme Attribute

    public AttributeTest(Attribute scheme, Attribute sub) {
        this.tester = scheme;
        this.tested = sub;
    }

    protected void execute() {
        String a=tester.getModifier()+" "+tester.getType()+" "+tester.getName();
        String b=tested.getModifier()+" "+tested.getType()+" "+tested.getName();
        if(a.equals(b)){
            result=tester.getName()+":Passed";
        }
        else{
            result=tester.getName()+":Failed";
        }
    }

    protected String result() {
        return result;
    }

}
