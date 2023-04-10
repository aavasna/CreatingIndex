package proj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads input file texts word by word,
 * stripping away all punctuation and numeric characters.
 * nextToken() returns the next word in file as a String.
 */
public class FileReader {

    private Scanner myReader;

    /**
     * Initializes the constructor with the given file.
     * @param filename path to input file
     */
    public FileReader (String filename) {
        try {
            myReader = new Scanner(new File(filename));
            myReader.useDelimiter("[^a-zA-Z#]+");
        } catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
    }

    /**
     * Reads and returns the text in the file word by word.
     */
    public String nextToken () {
        while (myReader.hasNext()) {
            return (myReader.next());
        }
        return null;
    }
}
