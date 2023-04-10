package proj5;


/**
 * Driver for the index maker project
 * 
 * @author Aavasna Rupakheti
 * @version March 12, 2023
 */
public class Client
{
    public static void main(String[] args)
    {
    	makeIndex("proj5/input.txt"); //replace with correct path
    }
    
    /**
     * Makes an index out of fileName. Gradescope needs this function.
     * 
     * @param fileName path to text file that you want to index
     */
    public static void makeIndex(String fileName) {
        Index index = new Index();
        index.makeIndexForFile(fileName);
    }
}
