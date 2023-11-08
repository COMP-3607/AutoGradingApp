package autograding;

public class AutoGrader {
    private SpecReader specReader;
    private SubmissionReader javaReader;
    private double score;
    private String specText;
    private TestSpecCreator specMaker;

    public AutoGrader() {
        specReader = new SpecReader();
        specMaker = new TestSpecCreator();
        javaReader = new SubmissionReader();
        score = 0;
    }

    public double grader(String specFilePath, String specFolder, String specExtractionDirectory, String javaZipFilePath,
            String javaExtractionDirectory) {
        specText = specReader.readFile(specFilePath, specFolder, specExtractionDirectory);

        specMaker.createTestObject(specText);
        return score;
    }
}
