package BinarySearchTree;

import java.util.Iterator;
import java.util.Random;

/**
 * A wrapper class around a {@code BSTInterface} tree containing {@code Strings}, intended for testing the tree.
 * This class contains all the methods of the tree class.
 * It also contains a few utility methods, such as one to insert random data into the tree.
 */
public class TestTreeWrapper implements BSTInterface<String>{
    BSTInterface<String> tree;

    public TestTreeWrapper(BSTInterface<String> tree) {
        this.tree = tree;
    }

    /**
     * This method generates and inserts random words into the tree.
     * @param amount    The amount of words to insert
     */
    public void insertRandomData(int amount){
        insertRandomData(new Random(), amount);
    }

    /**
     * This method generates and inserts random words into the tree.
     * @param rand  An instance of {@link java.util.Random} which will be used to generate the data
     * @param amount    The amount of words to insert
     */
    public void insertRandomData(Random rand, int amount){
        String[] words = generateRandomData(rand, amount);
        insertArrayOfData(words);
    }

    /**
     * This method inserts all of the contents of a String array into the tree
     * @param data  An array of Strings, all of which should be inserted into the tree.
     */
    public void insertArrayOfData(String[] data){
        for (int i = 0; i < data.length; i++) {
            tree.add(data[i]);
        }
    }

    public void setTree(BSTInterface<String> tree) {
        this.tree = tree;
    }

    public BSTInterface<String> getTree() {
        return tree;
    }

    /**
     * This method generates and returns a String array of random data
     * @param rand an instance of {@link java.util.Random} which will be used to generate the data
     * @param amount    the number of Strings to be generated
     * @return
     */
    private String[] generateRandomData(Random rand, int amount){
        String[] arr = new String[amount];

        for (int i = 0; i < amount; i++) {
            int wordLength = rand.nextInt(3, 10);
            char[] word = new char[wordLength];

            for (int j = 0; j < wordLength; j++) {
                char letter = (char) rand.nextInt('a', 'z' + 1);
                word[j] = letter;
            }
            arr[i] = new String(word);
        }
        return arr;
    }


    @Override
    public String min() {
        return tree.min();
    }

    @Override
    public String max() {
        return tree.max();
    }

    @Override
    public Iterator<String> getIterator(Traversal orderType) {
        return tree.getIterator(orderType);
    }

    /**
     * Returns true if the collection is empty and false if it is non-empty
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    /**
     * Returns true if the collection is full and false if it is not.
     *
     * @return
     */
    @Override
    public boolean isFull() {
        return tree.isFull();
    }

    /**
     * Attempts to add an element to the collection
     *
     * @param element The element to add
     * @return {@code} true if the element was successfully added and false if it wasn't
     */
    @Override
    public boolean add(String element) {
        return tree.add(element);
    }

    /**
     * Attempts to remove an element from the collection.
     * <br> If the element is not in the collection or cannot be removed, returns false.
     *
     * @param element The element to remove.
     */
    @Override
    public boolean remove(String element) {
        return tree.remove(element);
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean contains(String target) {
        return tree.contains(target);
    }

    @Override
    public String get(String target) {
        return tree.get(target);
    }

    /**
     * Returns an iterator over elements of type {@code String}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<String> iterator() {
        return tree.iterator();
    }
}
