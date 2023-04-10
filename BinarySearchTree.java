package proj5;

/** This is the Binary Search Tree ADT.
 * It has methods for insertion, deletion and search.
 *
 * @author Aavasna Rupakheti
 * @version March 5, 2023
 */
public class BinarySearchTree<T extends Comparable<T>>
{
	private BSTNode<T> root;

	/**
	 * Default Constructor.
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * Insert a new value to the BST.
	 *
	 * @param value T value to insert
	 */
	public void insert(T value) {
		BSTNode<T> newNode = new BSTNode<>(value);   // create new data node
		root = insert(root, newNode);
	}

	/**
	 * Recursively inserts a new node into the binary search tree rooted at currentRoot.
	 *
	 * @param currentRoot inserts into subtree rooted at currentRoot
	 * @param newNode node to insert
	 * @return currentRoot with newNode already inserted
	 */
	private BSTNode<T> insert(BSTNode<T> currentRoot, BSTNode<T> newNode) {
		// if the current root is null, create a new node and return it
		if (currentRoot == null) {
			return newNode;
		}
		// if the given data is less than the data in current root, recur for the right subtree
		else if (currentRoot.key.compareTo(newNode.key) < 0) {
			currentRoot.rlink = insert(currentRoot.rlink,newNode);
			return currentRoot;
		}
		// if the given data is less than the data at current root , recur for the left subtree
		else {
			currentRoot.llink = insert(currentRoot.llink,newNode);
			return currentRoot;
		}
	}


	/**
	 * return tree as printable string
	 * @return tree in string format with form (left subtree) value (right subtree)
	 */
	public String toString(){
		return toString(root);
	}

	/**
	 * recursive helper method for toString()
	 *
	 * @param N root of subtree to make into a string
	 * @return string version of tree rooted at N
	 */
	private String toString(BSTNode<T> N){
		String ret = "";
		if (N != null){
			ret += "(";
			ret += toString(N.llink);
			ret += "  " + N + "  ";
			ret += toString(N.rlink);
			ret += ")";
		}
		return ret;
	}


	/**
	 * checks whether the target value is in the tree
	 *
	 * @param target T as target to search
	 * @return true if the target is in BST, false otherwise
	 */
	public boolean search(T target){
		return (getElement(target)!=null);
	}

	/**
	 * Get element in BST that equals to the given target. If there's no equal element, return null
	 * @param target T as target to search
	 * @return T as element in BST if founded, null otherwise
	 */
	public T getElement(T target){
		return getElement(target, root);
	}

	/**
	 * Private helper for getElement. Given a root current and a target,
	 * search for the node in the subtree rooted at current that has key equals to target,
	 * then return the key of that node
	 * @param target T as target to search
	 * @param currentRoot BST Node as root of BST to start searching
	 * @return T as element in BST if founded, null otherwise
	 *
	 */
	private T getElement(T target, BSTNode<T> currentRoot){
		if (currentRoot == null) {   // base case: empty BST always returns false
			return null;
		}
		else {// if BST is not empty
			// checks whether it's the key of currentRoot
			if (target.compareTo(currentRoot.key) == 0) {
				return currentRoot.key;
			} else if (target.compareTo(currentRoot.key) > 0) { // left subtree
				return getElement(target, currentRoot.rlink);
			} else {
				return getElement(target, currentRoot.llink);  // right subtree
			}
		}
	}

	/**
	 * Returns number of data items in the tree
	 * @return integer as number of items
	 */
	public int size()
	{
		return size(root);
	}


	/**
	 * Private helper for size(). Figure out the number of data items in the BST
	 * given the root of it, recursively
	 * @param current BST Node as root
	 * @return integer as number of items
	 */
	private int size(BSTNode<T> current){
		if (current == null){   // size of an empty BST is 0
			return 0;
		}
		else{  // Size of a tree equals size of left BST + size of right BST + 1 (as current root)
			return 1 + size(current.llink) + size(current.rlink);
		}
	}

	/**
	 * delete the value equals to given value from this BST, and return the value deleted
	 * if given value as target is null, do nothing and return null
	 *
	 * @param value T as target to deleted
	 * @return T value from this tree that got deleted; null otherwise
	 */
	public T delete(T value){
		T toReturn = getElement(value);
		root = delete(root, value);
		return toReturn;
	}

	/**
	 * deletes value from tree rooted at currentRoot
	 * @param currentRoot  root of tree to be deleted from
	 * @param value T - element to delete
	 * @return pointer to tree rooted at currentRoot that has value removed from it
	 */
	private BSTNode<T> delete(BSTNode<T> currentRoot, T value) {
		if (currentRoot == null){ // current root
			return null;
		}
		else if(value.compareTo(currentRoot.key) < 0){ // left subtree
			currentRoot.llink = delete(currentRoot.llink, value);
		}
		else if(value.compareTo(currentRoot.key) > 0){ // right subtree
			currentRoot.rlink = delete(currentRoot.rlink, value);
		}
		else {
			if (currentRoot.isLeaf()){
				return null;
			}
			else if(currentRoot.hasLeftChildOnly()){
				return currentRoot.llink;
			}
			else if(currentRoot.hasRightChildOnly()){
				return currentRoot.rlink;
			}
			else {
				// find the smallest node in right subtree
				BSTNode<T> smallestNode = findSmallest(currentRoot.rlink);
				currentRoot.key = smallestNode.key; // set smallest as current root
				currentRoot.rlink = delete(currentRoot.rlink, smallestNode.key); // delete the smallest from subtree
			}
		}
		return currentRoot;
	}

	/**
	 * finds the node with the smallest value from the tree rooted at currentRoot
	 * @param currentRoot
	 * @return
	 */
	private BSTNode<T> findSmallest(BSTNode<T> currentRoot){
		BSTNode<T> runner = currentRoot;
		while (runner.llink != null){
			runner = runner.llink;
		}
		return runner;
	}

}


