package autograding.TestTemplate;

import java.util.ArrayList;

import autograding.AssignFactory.Scheme;
import autograding.AssignFactory.Submission;

import autograding.AssignFactory.SubmissionCreator;

public class SubmissionTest extends Test{
    private Scheme tester;       //MarkScheme Object
    private Submission tested;   //Submission Object
    private String result;
    private int mark;
    private ArrayList<String> results;

    public SubmissionTest(Scheme scheme, Submission sub){
        this.tester=scheme;
        this.tested =sub;
        this.results = new ArrayList<>();
    }

    @Override
    protected void execute() {
       classResults();
    }

    @Override
    protected String result() {
        return result;
    }

    public void classResults(){
        ClassTest test;
        Class scheme;
        Class sub;
        for(int i=0; i<tester.getClasses().size();i++){
            scheme=new Class (tester.getClasses().get(i));
            sub = new Class (tested.getClasses().get(i));
            test= new ClassTest(tester, tested);

            results.add(test.result());
            
            if(test.result().contains("Passed")){
                mark=mark+scheme.getMarks();
            }
        }
    }
}
