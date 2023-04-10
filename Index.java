package proj5;

/**
 * Constructs an Index object - which hold index for words and the pages where they occur.
 * This class contains methods to construct and manipulate the Index:
 * Insertion, makeIndex, etc.
 *
 * @author Aavasna Rupakheti
 * @version March 12, 2023
 */
public class Index {
    public static final int MIN_WORD_LENGTH = 3;

    private BinarySearchTree<Word> contents;

    /**
     * Default-constructor
     */
    public Index() {
        contents = new BinarySearchTree<Word>();
    }

    /**
     * Get Word Object in this Index that has the key equal to given String
     * Case-insensitive for the first letter of the given String.
     * For example: "House" is considered equal to "house".
     *
     * @param word String word as desired key to find the Word Object
     * @return the Word object with key equals to given string. If not found, return null.
     */
    private Word getWordCaseInsensitive(String word) {
        Word toReturn = getWord(word);
        // If original word is not in Index, search for other case version
        if (toReturn == null) {
            String firstChar = word.substring(0, 1);
            if (firstChar.equals(firstChar.toLowerCase())) {
                return getWord(firstChar.toUpperCase() + word.substring(1));
            } else {
                return getWord(firstChar.toLowerCase() + word.substring(1));
            }
        } else {
            return toReturn;
        }
    }

    /**
     * Get Word Object in Index that has the key equal to given String
     *
     * @param word String word as desired key to find the Word Object
     * @return the Word object with key equals to given string. If not found, return null
     */
    private Word getWord(String word) {
        return contents.getElement(new Word(word));
    }

    /**
     * Insert a given string and its pageNumber to this Index.
     * If the given string is already in Index, add the new pageNumber to it.
     *
     * @param value String value to add to Index
     * @param pageNumber int pageNumber to add to Index
     */
    public void insert(String value, int pageNumber) {
        Word element = getWordCaseInsensitive(value);   // case-insensitive
        if (element == null) {    // word is not yet in Index
            Word newWord = new Word(value);
            contents.insert(newWord);
            newWord.addPageNumber(pageNumber);
        } else {   // if word is already in index
            element.addPageNumber(pageNumber); // add page number
        }
    }


    /**
     * Get a printable string of this Index object i.e.
     * The complete Index, with case preserved.
     * All words should be in ASCII alphabetical order in the following format:
     * like {3, 8}
     * ran {8}
     * run {3, 4, 5}
     *
     * @return String as printable string of this Index
     */
    public String toString() {
        String[] msg = contents.toString().split(" ");
        String toReturn = "";
        for (String word : msg) {
            if (!word.contains("(") && !word.contains(")") && !word.isBlank()) {
                if (word.contains("}")) {
                    toReturn = toReturn + word + "\n";
                } else if (word.contains(",")) {
                    toReturn += word;
                } else {
                    toReturn = toReturn + word + " ";
                }
            }
        }
        return toReturn;
    }


    /**
     * Delete the given Word object from Index and return that deleted Word object
     *
     * @param current Word Object given to deleted
     * @return the deleted Word Object
     */
    private Word delete(Word current) {
        return contents.delete(current);
    }


    /**
     * Make index from a given file, then print out 3 things:
     * 1. For any word deleted from the index and inserted into the dictionary, print out the word
     * along with its (full) pagelist. For example:
     * Deleting 'Dick {1, 2, 3, 4}' from index.
     *
     * 2. The complete index, with case preserved. All words should be in ASCII alphabetical order e.g.
     * like {3, 8}
     * ran {8}
     * run {3, 4, 5}
     *
     * 3. The complete dictionary. All words should be in ASCII alphabetical order. One word per line.
     *
     * @param filename String file name to make index of
     */
    public void makeIndexForFile(String filename) {
        Dictionary dictionary = new Dictionary();
        FileReader file = new FileReader(filename);
        String nextWord = file.nextToken();
        Word currentWord;
        int pageNumber = 1;

        while (nextWord != null) {     // while not the end of file
            if (nextWord.compareTo("#") == 0) {  // switch to next page
                pageNumber += 1;
            }
            else {
                if ((nextWord.length() >= MIN_WORD_LENGTH) && (!dictionary.searchCaseInsensitive(nextWord))) {
                    currentWord = getWordCaseInsensitive(nextWord);
                    // word is already in index
                    if (currentWord != null) {
                        // word's pageList doesn't yet have this number
                        if (!currentWord.hasPage(pageNumber)) {
                            // pageList isn't full
                            if (!currentWord.pageListIsFull()) {
                                // if 2 words different in cases then merge them and their page lists
                                if (!wordsAreIdentical(currentWord.getKey(), nextWord)) {
                                    Word wordToMerge = new Word(nextWord);
                                    wordToMerge.addPageNumber(pageNumber);
                                    currentWord.merge(wordToMerge);
                                }
                                else { // if identical, add page number to pagelist
                                    currentWord.addPageNumber(pageNumber);
                                }
                            }
                            // pageList is full
                            else {
                                printDeletedWord(currentWord);  // print word and pageList
                                delete(currentWord); // delete the word
                                dictionary.insert(currentWord.getKey()); // add word to dictionary
                            }
                        }
                    }
                    //  word isn't in index yet
                    else {
                        insert(nextWord, pageNumber);
                    }
                }
            }
            nextWord = file.nextToken();
        }
        System.out.println(this.toString()); // print index
        System.out.println(dictionary); // print dictionary
    }

    /**
     * Checks if 2 words are "identical" or not.
     * i.e: they have the same characters and same cases.
     *
     * @param word1 String word1 to check
     * @param word2 String word2 to check
     * @return true if identical; else return false
     */
    private boolean wordsAreIdentical(String word1, String word2) {
        return word1.equals(word2);
    }

    /**
     * Helper for when deleting the word. Print the given Word Object
     * Format: Deleting 'Dick {1, 2, 3, 4}' from index.
     *
     * @param givenWord given Word Object to print
     */
    private void printDeletedWord(Word givenWord) {
        System.out.println("Deleting '" + givenWord + "' from index.");
    }
}

