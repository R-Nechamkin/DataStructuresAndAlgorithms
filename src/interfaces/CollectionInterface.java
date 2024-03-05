package interfaces;

import java.util.Iterator;

public interface CollectionInterface <T> {

    /**
     * Returns true if the collection is empty and false if it is non-empty
     * @return
     */
    boolean isEmpty();

    /**
     * Returns true if the collection is full and false if it is not.
     * @return
     */
    boolean isFull();

    /**
     * Attempts to add an element to the collection
     * @param element   The element to add
     * @return {@code} true if the element was successfully added and false if it wasn't
     */
    boolean add(T element);

    /**
     * Attempts to remove an element from the collection.
     * <br> If the element is not in the collection or cannot be removed, returns false.
     * @param element   The element to remove.
     */
    boolean remove(T element);

    int size();

    boolean contains(T target);
    T get(T target);


}
