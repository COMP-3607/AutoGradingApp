package autograding.TestObjectFactory;

public interface ReportSubject {
    void registerObserver(ReportObserver observer);
    void removeObserver(ReportObserver observer);
    void notifyObservers();
}