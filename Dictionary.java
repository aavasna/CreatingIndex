package proj5;

/**
 * This class represent a Dictionary Object, which contains Strings.
 * It includes methods to manipulate a dictionary:
 * insert new string, search for a string, etc.
 *
 * @author Aavasna Rupakheti
 * @version March 12, 2023
 */
public class Dictionary{
    private BinarySearchTree<String> contents;

    /**
     * Default constructor
     */
    public Dictionary(){
        contents = new BinarySearchTree<String>();
    }

    /**
     * Insert a string to this dictionary
     * @param newValue given String to insert
     */
    public void insert(String newValue){
        contents.insert(newValue);
    }

    /**
     * Search whether a given string is in dictionary or not
     * @param word given String as target to search
     * @return true if in dictionary; else false
     */
    public boolean search(String word){
        return contents.search(word);
    }

    /**
     * Search whether a given string is in dictionary or not,
     * case-insensitive for the first character of searched word.
     * For example: "House" is in dictionary, search for "house" returns true
     * @param word given String as target to search
     * @return true if in dictionary; else false
     */
    public boolean searchCaseInsensitive(String word){
        if (!search(word)){     // if original word is not in dictionary, search for another case version
            String firstChar = word.substring(0,1);
            if (firstChar.equals(firstChar.toLowerCase())){
                return search(firstChar.toUpperCase() + word.substring(1));
            }
            else {
                return search(firstChar.toLowerCase() + word.substring(1));
            }
        }
        else {
            return true;
        }
    }


    /**
     * Constructs a printable string to represent this dictionary.
     * All words should be in ASCII alphabetical order. One word per line.
     *
     * For example:
     * Buy
     * The
     * house
     *
     * @return the string as representation
     */
    public String toString(){
        String[] msg = contents.toString().split(" ");
        String toReturn = "";
        for (String word: msg){
            if (!word.contains("(") && !word.contains(")") && !word.isBlank()){
                toReturn = toReturn + word + "\n";
            }
        }
        return toReturn;
    }
}
