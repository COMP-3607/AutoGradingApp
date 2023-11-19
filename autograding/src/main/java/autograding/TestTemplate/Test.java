package autograding.TestTemplate;

public abstract class Test{
    public final void runTest() {
        
        execute();
        result();
    }

    protected abstract void execute();

    protected abstract String result();
}
