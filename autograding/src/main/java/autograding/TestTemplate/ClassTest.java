package autograding.TestTemplate;

import java.util.ArrayList;

import autograding.AssignFactory.Attribute;
import autograding.AssignFactory.Scheme;
import autograding.AssignFactory.Method;
import autograding.AssignFactory.Submission;
import autograding.AssignFactory.SubmissionCreator;

public class ClassTest extends Test{
    private Class tester;       //MarkScheme    Class
    private Class tested;       //Submission    Class
    private String result;
    private ArrayList<String> attResults;
    private ArrayList<String> methResults;
    private int attMarks;
    private int methMarks;

    public ClassTest(Scheme scheme,Submission sub){
        this.tester = scheme;
        this.tested = sub;
        this.attResults = new ArrayList<>();
        this.methResults = new ArrayList<>();
    }

    protected void execute() {
        if(tester.getName().equals(tested.getName())){
            attributeResults();
            methodResults();
                
             if(getMarks()>=(tester.getMarks()/2)){
                result=tested.getName()+":Passed";
            }
            else{
                result=tested.getName()+":Failed";
            }
        }
    }
    
    protected String result() {
       return result;
    }

    public int getMarks(){
        return attMarks+methMarks;
    }

    public void attributeResults(){
        AttributeTest test;
        Attribute  sub;
        Attribute  scheme;

        for(int i=0;i<tester.getAttributes().size();i++){
            scheme= tester.getAttributes().get(i);
            sub= tested.getAttributes().get(i);

            test=new AttributeTest(scheme, sub);
            test.execute();
            attResults.add(test.result());
            
            if(test.result().contains("Passed")){
                attMarks=attMarks+scheme.getMarks();
            }
        
        }
    }

    public void methodResults(){
        MethodTest test;
        Method  sub;
        Method  scheme;

        for(int i=0;i<tester.Method().size();i++){
            scheme= tester.getMethods().get(i);
            sub= tested.getMethods().get(i);

            test=new MethodTest(scheme, sub,tested);
            test.execute();

            methResults.add(test.result());
            if(test.result().contains("Passed")){
                methMarks=methMarks+scheme.getMarks();
            }
        }
    }

}
