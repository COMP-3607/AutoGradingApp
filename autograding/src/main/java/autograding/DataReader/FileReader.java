package autograding.DataReader;

public interface FileReader {
    public String readFile(String zipFilePath, String specFolder, String extractionDirectory);
}
