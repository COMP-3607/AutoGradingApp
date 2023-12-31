package autograding;

import autograding.DataReader.SpecReader;
import autograding.DataReader.SubmissionReader;

public class App {
    public static void main(String[] args) {

        double score;
        AutoGrader autoGrader = new AutoGrader();
        // Specify the path to the uploaded ZIP file.
        String javaZipFilePath = "path_to_uploaded_zip_file.zip";

        // Specify the directory where you want to extract the submissions.
        String javaExtractionDirectory = "extraction_directory";

        String specFilePath = "autograding\\src\\main\\java\\autograding\\TestData.zip";
        String specExtractionDirectory = "extraction_directory";
        String specFolder = "autograding\\src\\main\\java\\autograding\\TestData.zip";
        score = autoGrader.grader(specFilePath, specFolder, specExtractionDirectory, javaZipFilePath,
                javaExtractionDirectory);
    }
}
