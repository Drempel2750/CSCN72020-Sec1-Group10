package ScadaBackend.FileHandling;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandling {
    private String fileName;
    private int lineCount;

    // Constructor
    public FileHandling(String fileName) {
        this.fileName = fileName;
        this.lineCount = 0;
}
    public void openFile() {
        try {
            Scanner scanner = openReadStream(fileName);
            if (scanner != null) {
                // Read the file and count lines
                while (scanner.hasNextLine()) {
                    scanner.nextLine();
                    lineCount++;
                }
                scanner.close();
                System.out.println("File opened successfully. Total lines: " + lineCount);
            } else {
                reportFileOpeningError("File does not exist or cannot be read.");
            }
        } catch (FileNotFoundException e) {
            reportFileOpeningError("File not found: " + e.getMessage());
        }
    }

    // Function to read the contents of the file
    public String readFile() {
        try {
            Scanner scanner = openReadStream(fileName);
            if (scanner != null) {
                StringBuilder content = new StringBuilder();
                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine()).append("\n");
                }
                scanner.close();
                return content.toString();
            } else {
                reportFileOpeningError("File does not exist or cannot be read.");
            }
        } catch (FileNotFoundException e) {
            reportFileOpeningError("File not found: " + e.getMessage());
        }
        return null;
    }

    // Function to close the file
    public void closeFile() {
        System.out.println("Closing the file.");
        // Additional cleanup if needed
    }

    // Function to check for file updates
    public void checkForFileUpdates() {
        // Implement logic to monitor and reload file if updates are detected
        System.out.println("Checking for file updates.");
    }

    // Function to report file opening error
    private void reportFileOpeningError(String error) {
        System.out.println("Error opening the file: " + error);
    }

    // Helper function to open a read stream
    private Scanner openReadStream(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }
        if (!file.canRead()) {
            return null;
        }
        return new Scanner(file);
    }
}
