package autograding.TestObjectFactory;

import java.util.ArrayList;
import java.util.List;

public class StudentSubmission implements ReportSubject {
    private List<ReportObserver> observers;
    private String firstName;
    private String lastName;
    private String studentId;
    private SubmissionTests submissionTests;

    public StudentSubmission(String firstName, String lastName, String studentId, SubmissionTests submissionTests) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.submissionTests = submissionTests;
        this.observers = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStudentId() {
        return studentId;
    }

    @Override
    public void registerObserver(ReportObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(ReportObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        submissionTests.notifyObservers(this);
    }
}
