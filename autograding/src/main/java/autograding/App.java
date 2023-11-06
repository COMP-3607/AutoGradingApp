package autograding;

/**
 * Hello world!
 *
 */
public class App {
    public static SubmissionReader javaReader = new SubmissionReader();
    public static SpecReader specReader = new SpecReader();

    public static void main(String[] args) {
        // Specify the path to the uploaded ZIP file.
        String javaZipFilePath = "path_to_uploaded_zip_file.zip";

        // Specify the directory where you want to extract the submissions.
        String javaExtractionDirectory = "extraction_directory";

        String specFilePath = "autograding\\src\\main\\java\\autograding\\TestData.zip";
        String specExtractionDirectory = "extraction_directory";
        // Call the method to process the submissions.
        specReader.readFile(specFilePath, specExtractionDirectory);
        // javaReader.readFile(javaZipFilePath, javaExtractionDirectory);
    }
}
