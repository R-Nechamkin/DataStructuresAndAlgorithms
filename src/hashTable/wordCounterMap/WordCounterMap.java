package hashTable.wordCounterMap;

import hashTable.LinkedMapEntry;
import hashTable.MapInterface;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class is a map with {@link String} keys and {@link Integer} values.
 * <br>The expected use is to count the number of occurrences of words.
 */
public abstract class WordCounterMap implements MapInterface<String, Integer> {

    /*
    Class variables are not private in order that the test class can see them
     */
    LinkedMapEntry<String, Integer>[] arr;
    int size;
    double loadFactor;

    /**
     * Initializes a map with a default capacity of 10 and a load factor of .75
     */
    public WordCounterMap() {
        this(10, .75);
    }

    public WordCounterMap(int capacity, double loadFactor) {
        arr = new LinkedMapEntry[capacity];
        this.loadFactor = loadFactor;
        size = 0;
    }



    /**
     * Puts an entry into the map.
     * If there is already an entry in the map associated with {@code key}, replaces
     * that entry with {@code value} and returns the old entry.
     * If there was no such entry before, adds a new entry to the map and returns null.
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Integer put(String key, Integer value) {
        int hash = hash(key) % arr.length;
        LinkedMapEntry<String, Integer> node = arr[hash];
        if(node == null){
            size ++;
            arr[hash] = new LinkedMapEntry<>(key, value);
            return null;
        } else if (node.getKey().equals(key)) {
            Integer num = node.getValue();
            node.setValue(value);
            return num;
        }

        while (node.hasNext() && !node.getKey().equals(key)){
            node = node.getNext();
        }
        if(!node.hasNext()){
            node.setNext(new LinkedMapEntry<>(key, value));
            size ++;
            return null;
        }
        else {
            node = node.getNext();
            Integer num = node.getValue();
            node.setValue(value);
            return num;
        }
    }

    /**
     * Returns the value associated with the key.
     * If there is no entry associated with the key, returns {@code null}.
     *
     * @param key
     * @return
     */
    @Override
    public Integer get(String key) {
        int hash = hash(key) % arr.length;
        LinkedMapEntry<String, Integer> node = arr[hash];
        if(node == null){
            return null;
        } else if (node.getKey().equals(key)) {
            return node.getValue();
        }

        while (node.hasNext() && !node.getKey().equals(key)){
            node = node.getNext();
        }
        if(!node.hasNext()){
            return null;
        }
        else {
            node = node.getNext();
            return node.getValue();
        }
    }

    /**
     * Removes an entry in the map associated with {@code key}.
     *
     * @param key
     * @return The value which used to be associated with {@code key}, or null if
     * there was no entry associated with {@code  key}.
     */
    @Override
    public Integer remove(String key) {
        int hash = hash(key ) % arr.length;
        LinkedMapEntry<String, Integer> node = arr[hash];

        if(arr[hash] == null){
            return null;
        }
        else if(arr[hash].getKey().equals(key)){
            size --;
            Integer num = node.getNext().getValue();
            arr[hash] = null;
            return num;
        }

        while (node.hasNext() && !node.getKey().equals(key)){
            node = node.getNext();
        }
        if(!node.hasNext()){
            return null;
        }
        else {
            size --;
            Integer num = node.getNext().getValue();
            node.setNext(node.getNext().getNext());
            return num;
        }
    }

    /**
     * Returns true if the map contains an entry associated with {@code key}.
     *
     * @param key
     * @return
     */
    @Override
    public boolean contains(String key) {
        int hash = hash(key)  % arr.length;
        LinkedMapEntry<String, Integer> node = arr[hash];
        if(arr[hash] == null){
            return false;
        }
        else if (arr[hash].getKey().equals(key)) {
            return false;
        }

        while (node.hasNext() && !node.getKey().equals(key)){
            node = node.getNext();
        }

        return node.getNext() != null;
    }

    /**
     * Returns true if the map is empty, and false otherwise.
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns true if the map is full and false otherwise
     *
     * @return
     */
    @Override
    public boolean isFull() {
        return false;
    }

    /**
     * Returns the number of entries in this map.
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * This method takes in a string and hashes it
     * @return
     */
    protected abstract int hash(String str);

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<LinkedMapEntry<String, Integer>> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<LinkedMapEntry<String, Integer>>{
        int i;
        LinkedMapEntry<String, Integer> curr;

        public Itr() {
            this.i = 0;
            this.curr = arr[i];
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return (i < arr.length) || (curr.hasNext());
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public LinkedMapEntry<String, Integer> next() {
            if(!hasNext())
                throw new NoSuchElementException();

            if(curr.hasNext()){
                curr = curr.getNext();
                return curr;
            }
            else {
                while (arr[i++] == null);
                return arr[i];
            }
        }
    }

    /**
     * This utility method places a key in the hash map with the value of {@code  amount}.
     * If {@code  key} is already in the map, it increments the value associated with {@code key} by {@code amount}.
     * @param key
     * @param amount
     * @return {@code true} if the incrementation is successful;
     * {@code false} if the key already exists and is associated with a value of Integer.MAX_VALUE
     */
    public boolean putAndIncrement(String key, Integer amount){
        int hash = hash(key) % arr.length;
        LinkedMapEntry<String, Integer> node = arr[hash];
        if(node == null){
            size ++;
            arr[hash] = new LinkedMapEntry<>(key, amount);
            return true;
        } else if (node.getKey().equals(key)) {
            if(node.getValue() == Integer.MAX_VALUE){
                return false;
            }
            else {
                node.setValue(node.getValue() + amount);
            }
        }

        while (node.hasNext() && !node.getKey().equals(key)){
            node = node.getNext();
        }
        if(!node.hasNext()){
            node.setNext(new LinkedMapEntry<>(key, amount));
            size ++;
            return true;
        }
        else {
            node = node.getNext();
            if(node.getValue() == Integer.MAX_VALUE){
                return false;
            }
            else {
                node.setValue(node.getValue() + amount);
                return true;
            }
        }
    }
}
