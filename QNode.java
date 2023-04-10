package proj5;

/**
 * A linked list (LL) node to store a queue entry
 * @author Aavasna Rupakheti
 * @version March 9, 2023
 */

public class QNode<E>{
        E key;
        QNode<E> next;
        // constructor to create a new linked list node
        public QNode( E key) {
            this.key = key;
            this.next = null;
        }
}
