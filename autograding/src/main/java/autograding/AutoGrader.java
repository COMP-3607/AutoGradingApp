package autograding;

public class AutoGrader {
    private SpecReader specReader;
    private SubmissionReader javaReader;
    private double score;
    private String specText;

    public AutoGrader() {
        specReader = new SpecReader();
        javaReader = new SubmissionReader();
        score = 0;
    }

    public double grader(String specFilePath, String specFolder, String specExtractionDirectory, String javaZipFilePath,
            String javaExtractionDirectory) {
        specText = specReader.readFile(specFilePath, specFolder, specExtractionDirectory);
        return score;
    }
}
