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
    boolean increaseCapacity;


    /**
     * Initializes a map with a default capacity of 10 and a load factor of .75
     */
    public WordCounterMap() {
        this(10, .75, true);
    }

    public WordCounterMap(int capacity, double loadFactor, boolean increaseCapacity) {
        arr = new LinkedMapEntry[capacity];
        this.loadFactor = loadFactor;
        size = 0;
        this.increaseCapacity = increaseCapacity;
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
        int index = getIndex(key);
        LinkedMapEntry<String, Integer> node = arr[index];
        System.out.println("Trying to place " + key + " in index " + index);
        if(node == null){
            incrementSize();
            arr[index] = new LinkedMapEntry<>(key, value);
            System.out.println("Placed " + key + " in the head of this index.");
            return null;
        } else if (node.getKey().equals(key)) {
            Integer num = node.getValue();
            node.setValue(value);
            System.out.println(key + " was already in head of the index. Replaced value of " + num + " with " + value);
            return num;
        }

        while (node.hasNext() && !node.getKey().equals(key)){
            System.out.println("Moving to the next node.");
            node = node.getNext();
        }
        if(!node.hasNext()){
            incrementSize();
            System.out.println("Placing " + key + " into next node.");
            node.setNext(new LinkedMapEntry<>(key, value));
            return null;
        }
        else {
            node = node.getNext();
            Integer num = node.getValue();
            node.setValue(value);
            System.out.println(key + " is already at the next node. Replaced value of " + num + " with " + value);
            return num;
        }
    }

    /**
     * This method should be called whenever a new node is added to the map.
     * The main point is to increment {@code size} and possibly increase the capacity of the backing array.
     */
    private void incrementSize(){
        size ++;
        System.out.println("Increasing size to " + size);
        checkCapacity();
    }

    /**
     * This method checks whether the length of the array should be increased in order to
     *  reduce collisions.
     */
    private void checkCapacity(){
        System.out.println("Checking capacity.");
        if(increaseCapacity && (loadFactor * arr.length < size())){
            System.out.printf("Increasing capacity, %2f percent of array filled\n", (double) size() / arr.length);
            doubleCapacity();
        }
    }

    /**
     * This method doubles the capacity of the array which backs this map.
     */
    private void doubleCapacity(){
        int newLength = Integer.MAX_VALUE / 2 > arr.length? arr.length * 2: Integer.MAX_VALUE;
        System.out.println("Resizing...");
        LinkedMapEntry<String, Integer>[] newArray = new LinkedMapEntry[newLength];
        System.out.println("Creating new array of size " + newLength);
        for (LinkedMapEntry<String, Integer> entry : arr) {
            LinkedMapEntry<String, Integer> node = entry;
            while (node != null) {
                System.out.println("Placing " + node + " into new array.");
                placeIntoNewArray(node, newArray);
                node = node.getNext();
            }
        }
        arr = newArray;
    }


    /**
     * This method places a node into an array at the proper place.
     * <strong>This method assumes that all nodes have unique keys.</strong>
     * <strong>The method also does not call the incrementSize() method</strong>
     * It is intended to be used when copying over the nodes of the map into a new array.
     * @param node  The node to place in the array.
     * @param array The array to place the node in.
     */
    private void placeIntoNewArray(LinkedMapEntry<String, Integer> node, LinkedMapEntry<String, Integer>[] array){
        int hash = Math.abs(hash(node.getKey())) % array.length;
        LinkedMapEntry<String, Integer> position = array[hash];
        if(position == null){
            array[hash] = new LinkedMapEntry<>(node.getKey(), node.getValue());
            return;
        }
        while(position.hasNext()){
            position = position.getNext();
        }
        position.setNext(new LinkedMapEntry<>(node.getKey(), node.getValue()));
    }

    @Override
    public Integer get(String key) {
        return getOrDefault(key, null);
    }

    /**
     * Returns the value associated with the key.
     * If there is no entry associated with the key, returns {@code defaultVal}.
     *
     * @param key
     * @param defaultVal
     * @return
     */
    public Integer getOrDefault(String key, Integer defaultVal) {
        int index = getIndex(key);
        LinkedMapEntry<String, Integer> node = arr[index];
        System.out.println("Looking for " + key + " at index: " + index);
        if(node == null){
            System.out.println("There is nothing at that index, returning " + defaultVal);
            return defaultVal;
        } else if (node.getKey().equals(key)) {
            System.out.println("Found " + key + ", returning " + node.getValue());
            return node.getValue();
        }

        while (node.hasNext() && !node.getKey().equals(key)){
            System.out.println("Going to next node.");
            node = node.getNext();
        }
        if(node.getKey().equals(key)){
            System.out.println("Found " + key + ", returning " + node.getValue());
            return node.getValue();
        }
        else {
            System.out.println("Node is not here, returning " + defaultVal);
            return defaultVal;
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
        int index = getIndex(key);
        LinkedMapEntry<String, Integer> node = arr[index];
        System.out.println("Looking for " + key + " at index: " + index);

        if (arr[index] == null) {
            System.out.println("There is nothing at that index, returning null");
            return null;
        } else if (arr[index].getKey().equals(key)) {
            size--;
            Integer num = node.getValue();
            if (node.hasNext()) {
                arr[index] = node.getNext();
            } else {
                arr[index] = null;
            }
            System.out.println("Found " + key + ", reducing size to " + size);
            return num;
        }

        LinkedMapEntry<String, Integer> next;
        do {
            if (!node.hasNext()) {
                System.out.println("Node is not here, returning null");
                return null;
            }
            next = node.getNext();
            System.out.println("Going to next node.");
        } while (!next.getKey().equals(key));

        System.out.println("Found node.");
        Integer num = next.getValue();
        if (next.hasNext()) {
            node.setNext(next.getNext());
        } else {
            node.setNext(null);
        }
        size--;
        System.out.println("Removed node and decremented size.");
        return num;

    }

    /**
     * Returns true if the map contains an entry associated with {@code key}.
     *
     * @param key
     * @return
     */
    /*
    This code implements the get function in the same way that the JCF does
     */
    @Override
    public boolean contains(String key) {
        return get(key) != null;
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
            if(i >= arr.length && !(curr.hasNext()))
                return false;
            for (int j = i +1; j < arr.length; j++) {
                if(arr[j] != null){
                    return true;
                }
            }
            return false;
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
                while (arr[i] == null){
                    i++;
                }
                return arr[i++];
            }
        }
    }


    protected int getIndex(String key){
        return Math.abs(hash(key))  % arr.length;
    }
}
