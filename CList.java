/**
 * Circular List implementation of List interface.
 * Adapted from Doubly Linked List implementation provided by Joanne Selinski
 *
 * @author Karl Tayeb, Ryan Denz
 * @param <T> the type of the List
 */
public class CList<T> implements List<T> {

    /**
     * Inner doubly linked Node class for convenience.
     * Note that the generic type is implied since we are within DLList<T>.
     */
    public class Node {

        /** The data in the element. */
        private T data;
        /** The left neighbor node. */
        private Node prev;
        /** The right neighbor node. */
        private Node next;

        /**
         * Make a node.
         * @param item the data to put in it
         * @param p the link to the previous node
         * @param n the link to the next node
         */
        public Node(T item, Node p, Node n) {
            this.data = item;
            this.prev = p;
            this.next = n;
        }
    }

    /** Head node. */
    private Node head;
    /** Number of actual data nodes in list. */
    private int size;
    /** Current node (think of as a cursor between nodes). */
    private Node curr;

    /**
     * Create an empty list with sentinels.
     */
    public CList() {
        this.clear();  // code reuse!
    }
    
    /**
     * Remove all contents from the list, so it is once again empty.
     */
    public void clear() {
        this.size = 0;
        this.head = null; 
        this.curr = null;  // because both are null when list is empty 
    }

    /**
     * Insert a value at (after) the current location.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to insert
     * @return true if successfully inserted, false otherwise
     */
    public boolean insert(T t) {
        this.size++;
        /** Case where list is empty */
        if (this.size == 1) {
            Node n = new Node(t,null,null);
            n.next = n;
            n.prev = n;
            this.head = n;
            this.curr = n;
            return true;
        }
        Node n = new Node (t, this.curr.prev, this.curr);
        n.prev.next = n;
        n.next.prev = n;
        // move the head if you inserted at head
        if (this.curr == this.head) {
            this.head = n;
        }
        // update current position so it is the same after insert
        this.curr = n;
        return true;
        
    }

    /**
     * Append a value at the end of the list.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to append
     * @return true if successfully appended, false otherwise
     */
    public boolean append(T t) {
        /** Case for empty list */
        if (this.size == 0) {
            this.insert(t);
            return true;
        }
        /** otherwise append inserts after the current position. */
        Node temp = this.curr;        // hold onto original position
        this.moveToEnd();
        Node n = new Node(t, this.curr, this.curr.next);
        n.prev.next = n;
        n.next.prev = n;
        this.size++;
        return true;
    }

    /**
     * Remove and return the current element (one to right of cursor). 
     * @return the value of the element removed, null if list is empty
     */
    public T remove() {
        if (this.size == 0) {
            return null;
        }
        T temp = this.curr.data;
        if (this.size == 1) {
            this.clear();
            return temp;
        }
        this.curr.prev.next = this.curr.next;
        this.curr.next.prev = this.curr.prev;
        /**Case for when you delete the last item. */
        if (this.curr == this.head) {
            this.head = this.head.next;
        } 
        if (this.curr.next == this.head) {
            this.moveToStart();
            this.size--;
            return temp;
        } else {
            this.next();
            this.size--;
            return temp;
        }
    }

    /**
     * Return the current element (data to right of cursor).
     * @return the value of the current element, null if none
     */
    public T getValue() {
        if (this.size == 0) {
            return null;
        }
        return this.curr.data;
    }

    /**
     * Return the number of elements in the list.
     * @return the length of the list
     */
    public int length() {
        return this.size;
    }

    /* ---------- METHODS BELOW THIS LINE ARE NOT IMPLEMENTED ------------ */

    /**
     * Set the current position to the start of the list.
     */
    public void moveToStart() {
        if (size != 0) {
            this.curr = this.head;    // move curr to head
        }
    }

    /**
     * Set the current position to the end of the list.
     */
    public void moveToEnd() {
        if (this.size != 0) {
            this.curr = this.head.prev;
        }
    }

    /**
     * Move the current position one step left.
     */
    public void prev() {
        if (this.size <= 1 || this.curr == this.head) {
            return;
        } else {
            this.curr = this.curr.prev;
        }
    }

    /**
     * Move the current position one step right.
     */
    public void next() {
        if (this.size <= 1 || this.curr.next == this.head) {
            return;
        } else {
            this.curr = this.curr.next;
        }
    }

    /**
     * Move the current position to the right always. 
     */
    public void strongNext() {
        this.curr = this.curr.next;
    }

    /**
     * Return the position of the current element.
     * @return the current position in the list
     */
    public int currPos() {
        int curPos = 0;        // counter for position number
        if (this.size == 0){
            return curPos;
        }
        Node temp = this.curr;    // save current location;
        this.moveToStart();    // move to start of CList
        /** loop will go to saved position */
        while (this.curr != temp && curPos < this.size) {
            this.next();
            curPos++;
        }
        return curPos;
    }

    /**
     * Set the current position.
     * @param pos the value to set the position to
     * @return true if successfully changed position, false otherwise
     */
    public boolean moveToPos(int pos) {
        if (pos >= 0 && pos < this.size) {
            this.moveToStart();
            for (int i = 0; i < pos; i++) {
                this.next();
            }
            return true;
        } 
        return false;
        
    }

    /**
     * Return true if current position is at end of the list.
     * @return true if the current position is the end of the list
     */
    public boolean isAtEnd() {
        if (this.curr== this.head.prev) {
            return true;
        } 
        return false;
    }

    public String toString() {
        if (this.size == 0) {
            return "[]";
        }
        Node temp = this.curr;
        this.moveToStart();
        String out = new String();
        out = "[ ";
        for (int i = 1; i < this.size; i++) {
            out += this.curr.data.toString();
            this.next();
            out += " ";
        }
        out += this.curr.data;
        out += " ]";
        this.curr = temp;
        return out;
    }

}

