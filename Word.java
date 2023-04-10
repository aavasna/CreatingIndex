package proj5;

/**
 * Construct a Word Object, which represent the word and its pageList in Index.
 * This class contains methods to construct and manipulate a Word Object:
 * Insert to pageList, compare 2 words, etc.
 *
 * @author Aavasna Rupakheti
 * @version March 10, 2023
 */
public class Word implements Comparable<Word> {
    public static final int PAGELIST_CAPACITY = 4;

    private String key;
    private Queue<Integer> pageList;


    /**
     * Non-default constructor. Construct new Word Object with key as given value
     *
     * @param value String value given to construct
     */
    public Word(String value) {
        key = value;
        pageList = new Queue<Integer>();
    }

    /**
     * Compare 2 Word Objects.
     *
     * @param otherWord other Word Object to compare
     * @return 0 if their keys are identical,
     * -1 if this key is < then other key;
     * else return 1
     */
    public int compareTo(Word otherWord) {
        return this.getKey().compareTo(otherWord.getKey());
    }

    /**
     * get the key of this object
     *
     * @return String key as key to return
     */
    public String getKey() {
        return key;
    }

    /**
     * Adds a pageNumber to pageList
     *
     * @param pageNumber integer as page to add
     */
    public void addPageNumber(int pageNumber) {
        pageList.insert(pageNumber);
    }

    /**
     * Check if current pageList has reached the capacity or not
     *
     * @return true if full, else false.
     */
    public boolean pageListIsFull() {
        return pageList.size() >= PAGELIST_CAPACITY;
    }

    /**
     * Constructs a printable String to represent this Word. Consecutive pages are represented with dashes.
     * Format: key + pageList
     * For example: Run {1, 3-5} - meaning the word "Run" occurs at page 1, 3, 4 and 5.
     *
     * @return String as representation
     */
    public String toString() {
        String toReturn = getKey() + " {";
        Queue<Integer> pageListClone = pageList.clone();
        boolean done;
        while (!pageListClone.isEmpty()) {
            if (pageListClone.size() == 1) {
                toReturn += pageListClone.remove();
            } else {
                int first = pageListClone.remove();
                int range = 1;
                done = false;
                while (!done) {
                    if (pageListClone.getHead() == null) {
                        done = true;
                    } else {
                        int last = pageListClone.getHead();
                        if ((last - first) == range) {
                            pageListClone.remove();
                            range += 1;
                        } else {
                            done = true;
                        }
                    }
                }
                if (range > 1) {
                    toReturn = toReturn + first + "-" + (first + range - 1);
                } else {
                    toReturn += first;
                }
                if (!pageListClone.isEmpty()) {
                    toReturn += ", ";
                }
            }
        }
        toReturn += "}";
        return toReturn;
    }

    /**
     * Check if pageList of this Word contains the given pageNumber or not
     *
     * @param pageNumber integer as page given
     * @return true if contains; else false
     */
    public boolean hasPage(int pageNumber) {
        Queue<Integer> pageListClone = pageList.clone();
        boolean found = false;

        while (!pageListClone.isEmpty()) {
            if (pageListClone.remove() == pageNumber) {
                found = true;
            }
        }
        return found;
    }

    /**
     * Merge a Word object to this Word Object.
     * The key of this Word object is changed to lower case.
     * The pages in pageList are merged, in order.
     * Changes to the other word object will not affect this current word after merge, and vice versa.
     *
     * Pre-conditions
     * 1. merge only occurs if 2 words have same characters but different cases
     * 2. Each word's pageList is already in order
     * e.g. Merge "House {2, 8}" and "hOuSe {1, 4}" will change current Word object
     * to "house {1, 2, 4, 8}"
     *
     * @param otherWord other Word object to merge
     * @return Word object as the merged word
     */
    public void merge(Word otherWord) {
        this.key = this.getKey().toLowerCase();
        Queue<Integer> newPageList = new Queue<Integer>();
        Queue<Integer> pageList1 = this.pageList.clone();
        Queue<Integer> pageList2 = otherWord.pageList.clone();

        while (!(pageList1.isEmpty() && pageList2.isEmpty())) {
            // either pagelist empty
            if (pageList1.isEmpty()) {
                newPageList.insert(pageList2.remove());
            }
            else if (pageList2.isEmpty()) {
                newPageList.insert(pageList1.remove());
            }
            // both pageList not empty
            else {
                int peek1 = pageList1.getHead();
                int peek2 = pageList2.getHead();
                if (peek1 < peek2) {
                    newPageList.insert(pageList1.remove());
                }
                else if (peek1 > peek2) {
                    newPageList.insert(pageList2.remove());
                }
                else { // both peek has same value
                    newPageList.insert(pageList1.remove());
                    pageList2.remove();
                }
            }
        }
        this.pageList = newPageList;
    }
}