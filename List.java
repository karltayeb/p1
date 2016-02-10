/**
 * Interface for a List data structure, based on the ADT from OpenDSA.
 * @param <T> the type of the List
 */
public interface List<T> {

    /*
    NOTE THAT WE DON'T HAVE TO INCLUDE THE PUBLIC ACCESS MODIFIER
    SINCE ALL METHODS IN AN INTERFACE ARE PUBLIC BY DEFAULT.
    CHECKSTYLE WANTS US TO USE DEFAULT PERMISSIONS.
    */
    
    /**
     * Remove all contents from the list, so it is once again empty.
     */
    void clear();

    /**
     * Insert a value at the current location.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to insert
     * @return true if successfully inserted, false otherwise
     */
    boolean insert(T t);

    /**
     * Append a value at the end of the list.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to append
     * @return true if successfully appended, false otherwise
     */
    boolean append(T t);

    /**
     * Remove and return the current element.
     * @return the value of the element removed
     */
    T remove();

    /**
     * Set the current position to the start of the list.
     */
    void moveToStart();

    /**
     * Set the current position to the end of the list.
     */
    void moveToEnd();

    /**
     * Move the current position one step left,
     * no change if already at beginning.
     */
    void prev();

    /**
     * Move the current position one step right, no change if already at end.
     */
    void next();

    /**
     * Return the number of elements in the list.
     * @return the length of the list
     */
    int length();

    /**
     * Return the position of the current element.
     * @return the current position in the list
     */
    int currPos();

    /**
     * Set the current position.
     * @param pos the value to set the position to
     * @return true if successfully changed position, false otherwise
     */
    boolean moveToPos(int pos);

    /**
     * Return true if current position is at end of the list.
     * @return true if the current position is the end of the list
     */
    boolean isAtEnd();

    /**
     * Return the current element.
     * @return the value of the current element
     */
    T getValue();
}
