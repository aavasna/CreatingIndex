package proj5;

/**
 * BSTNode class uses generic to holds value, constructing the Binary Search Tree.
 * A Node should hold data, and have a left link and a right link pointing to its
 * children.
 *
 * This class contains methods to manipulate a BSTNode:
 * isLeaf, hasRightChildOnly, hasLeftChildOnly
 *
 * @author Aavasna Rupakheti
 * @version March 5th, 2023
 */
public class BSTNode<T> {

    public T key;
    public BSTNode<T> llink;
    public BSTNode<T> rlink;

    /**
     * non-default constructor
     * @param newData T that node will hold
     */
    public BSTNode(T newData)
    {
        key = newData;
        llink = null;
        rlink = null;
    }

    /**
     * Construct printable string for this node
     * @return string as representation
     */
    public String toString()
    {
        return key.toString();
    }


    /**
     * Checks if this node is leaf or not
     * @return true if this node is a leaf, else false
     */
    public boolean isLeaf() {
        return this.llink == null && this.rlink == null;
    }

    /**
     * Checks if this node only has a right child or not
     * @return true iff this node has a non-null right subtree
     * and a null left subtree
     */
    public boolean hasRightChildOnly() {
        return this.llink == null && this.rlink != null;
    }

    /**
     * Checks if this node only has a right child or not
     * @return true iff this node has a non-null left subtree
     * and a null right subtree
     */
    public boolean hasLeftChildOnly() {
        return this.llink != null && this.rlink == null;
    }

}
