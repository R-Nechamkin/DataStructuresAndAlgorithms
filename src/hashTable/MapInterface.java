package hashTable;

public interface MapInterface<K, V> extends Iterable<LinkedMapEntry<K, V>>{

    /**
     * Puts an entry into the map.
     * If there is already an entry in the map associated with {@code key}, replaces
     *  that entry with {@code value} and returns the old entry.
     * If there was no such entry before, adds a new entry to the map and returns null.
     * @param key
     * @param value
     * @return
     */
    V put(K key, V value);

    /**
     * Returns the value associated with the key.
     * If there is no entry associated with the key, returns {@code null}.
     * @param key
     * @return
     */
    V get(K key);

    /**
     * Removes an entry in the map associated with {@code key}.
     * @param key
     * @return  The value which used to be associated with {@code key}, or null if
     *  there was no entry associated with {@code  key}.
     */
    V remove(K key);

    /**
     * Returns true if the map contains an entry associated with {@code key}.
     * @param key
     * @return
     */
    boolean contains(K key);


    /**
     * Returns true if the map is empty, and false otherwise.
     * @return
     */
    boolean isEmpty();

    /**
     * Returns true if the map is full and false otherwise
     * @return
     */
    boolean isFull();

    /**
     * Returns the number of entries in this map.
     * @return
     */
    int size();
}
