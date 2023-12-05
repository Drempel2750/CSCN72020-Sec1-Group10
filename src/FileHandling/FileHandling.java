package FileHandling;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandling {
    public static Scanner FileScanner(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) { //file with specified name does not exist
            System.out.println("File does not exist");
            return null;
        }
        if (!file.canRead()) { //cannot read from the specified file
            System.out.println("File cannot be read");
            return null;
        }
        return new Scanner(file);
    }
}
