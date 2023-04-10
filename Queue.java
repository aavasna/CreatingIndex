package proj5;

/**
 * Constructs a Queue ADT - a collection whose elements are
 * added at one end (the rear or tail of the queue)
 * and removed from the other end (the front or head of the queue)
 * A queue is a FIFO (first in, first out) data structure, meaning the order of elements are preserved.
 *
 * This class uses generic to construct and contains manipulation method for a Queue:
 * insert, remove, etc.
 *
 * @author Aavasna Rupakheti
 * @version March 10, 2023
 */
public class Queue<E> {
    QNode<E> front, rear;
    int count;

    public Queue() { this.front = this.rear = null; }

    /**
     * Insert new element to the queue
     * @param key E - new Item to insert
     */
    public void insert(E key)
    {
        // Create a new LL node
        QNode<E> newKey = new QNode<E>(key);

        // If queue is empty
        if (this.front == null) {
            // front and end of queue is key
            this.front = this.rear = newKey;
        }
        else{ // if not empty,
            // add the new node at the end of queue and change rear
            this.rear.next = newKey;
            this.rear = newKey;
        }
        count++;
    }

    /**
     * remove the element at head of the queue and return it
     * @return E - element got removed, if queue is not empty; else return null
     */
    public E remove()
    {
        // If queue is empty, return NULL.
        if (isEmpty()){
            return null;
        }
        else { // if not empty
            QNode<E> temp = this.front;
            E tempItem = temp.key;
            this.front = this.front.next;
            // if front is null, then rear is also null
            if (this.front == null)
                this.rear = null;
            count--;
            return tempItem;
        }
    }

    /**
     * Get a peek the element at head of the queue, without removing it
     * @return E - element got peeked, if queue is not empty; else return null
     */
    public E getHead(){
        if (isEmpty()){ // return null if empty
            return null;
        }
        else{
            return this.front.key;
        }
    }
    /**
     * Get the number of elements in this queue
     * @return int as number of elements
     */
    public int size(){
        return count;
    }

    /**
     * Checks if this queue is empty or ot
     * @return boolean true if empty; else false
     */
    public boolean isEmpty(){
        return (size() == 0);   // if empty, count is zero
    }

    /**
     * Get a clone of this queue, with same front, rear, number of elements
     * with order preserved.
     * Changes in this clone will not affect original queue, and vice versa.
     * @return Queue<E> as clone queue
     */
    public Queue<E> clone(){
        Queue<E> cloneQueue = new Queue<>();
        QNode<E> runner = front;
        while (runner != null){
            cloneQueue.insert(runner.key);
            runner = runner.next;
        }
        return cloneQueue;
    }

    /**
     * Construct a printable string to represent the queue, closed by "{}", with ">" before the first
     * element in the queue.
     *
     * For example: {>1, 5, 8, 10} - 1 is the first element
     * @return printable string
     */
    public String toString(){
        String toReturn = "{>";
        Queue<E> cloneQueue = this.clone();

        while (!cloneQueue.isEmpty()){
            if (cloneQueue.size()>1){
                toReturn = toReturn + cloneQueue.remove().toString() +", ";
            }
            else {
                toReturn += cloneQueue.remove().toString();
            }
        }
        toReturn += "}";
        return toReturn;
    }
}
