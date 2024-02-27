package BinarySearchTree.stdOutBst;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import BinarySearchTree.BSTInterface;
import BinarySearchTree.BSTNode;

import myQueue.MQueue;

/**
 * A BinarySearchTree class.
 * <strong>IMPORTANT:</strong> While performing operations, this class performs the steps to StdOut.
 * @param <T>
 */
public class BinarySearchTree <T> implements BSTInterface<T> {
    protected BSTNode<T> root;
    protected Comparator<T> comp;

    /**
     * Creates a new BinarySearchTree for {@code T}s which implement the {@link Comparable} interface.
     * @throws ClassCastException if {@code T} does not implement {@link Comparable<T>}
     */
    public BinarySearchTree() throws ClassCastException{
        comp = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return ((Comparable<T>)o1).compareTo(o2);
            }
        };
        System.out.println("Created a new BST of comparable objects.");
    }

    /**
     * Creates a new Binary Search Tree using a custom comparator
     * @param comp
     */
    public BinarySearchTree(Comparator<T> comp) {
        this.comp = comp;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public boolean isFull() {
        return false;
    }

    /**
     * {@inheritDoc}
     * @param element   The element to add
     * @return
     */
    @Override
    public boolean add(T element){
        if(element == null)
            throw new IllegalArgumentException("You attempted to add an element with the value of null to the tree"
            +"\nThis tree does not accept null values.");

        root = addRecordRecursive(root, element);
        return true;
    }

    @Override
    public boolean remove(T target) {
        System.out.println("Checking if target element exists in the tree.");
        if(!this.contains(target)) {
            return false;
        }
        System.out.println("\nRemoving target.");
        root = removeRecursive(root, target);
        return true;
    }

    @Override
    public int size() {
        return sizeRecursive(root, 0);
    }

    private BSTNode<T> addRecordRecursive(BSTNode<T> root, T data){
        if(root == null){
            root = new BSTNode<T>(data);
            System.out.println("Inserted " + data);
        }
        else if (comp.compare(root.getData(),data) > 0){
            System.out.print("Moved left; ");
            root.setLeft(addRecordRecursive(root.getLeft(), data));
        }
        else {
            System.out.print("Moved right; ");
            root.setRight(addRecordRecursive(root.getRight(), data));
        }
        return root;
    }

    public T min(){
        return getMinRecursive(root);
    }

    public T max(){
        return getMaxRecursive(root);
    }

    public boolean contains(T element){
        return containsRecursive(root, element);
    }

    @Override
    public T get(T target) {
        return target;
    }

    private T getMinRecursive(BSTNode<T> root){
        if(root.getLeft() == null)
            return root.getData();
        else
            return getMinRecursive(root.getLeft());
    }

    private T getMaxRecursive(BSTNode<T> root){
        if(root.getRight() == null)
            return  root.getData();
        else
            return getMaxRecursive(root.getRight());
    }


    private boolean containsRecursive(BSTNode<T> root, T data){
        if (root == null) {
            System.out.println("Data is not in tree; returning false.");
            return false;
        }
        if(data.equals(root.getData())) {
            System.out.println("Found data.");
            return true;
        }
        else if (comp.compare(root.getData(), data) > 0) {
            System.out.print("Moving left; ");
            return containsRecursive(root.getLeft(), data);
        }
        else {
            System.out.print("Moving right; ");
            return containsRecursive(root.getRight(), data);
        }
    }

    private BSTNode<T> removeRecursive(BSTNode<T> root, T data){
        if(root == null) {
            System.out.println("Returning");
            return null;
        }
        if(root.getData().equals(data)){
            System.out.println("Found data.");
            root = removeAndHeapify(root);
            return root;
        }
        else if (comp.compare(root.getData(), data) > 0) {
            System.out.print("Moving left; ");
            return removeRecursive(root.getLeft(), data);
        }
        else {
            System.out.print("Moving right; ");
            return removeRecursive(root.getRight(), data);
        }
    }

    private BSTNode<T> removeAndHeapify(BSTNode<T> root){
        if(root.getLeft() == null && root.getRight() == null) {
            System.out.println("No children; setting root to null.");
            root = null;
        }
        else if(root.getLeft() == null) {
            System.out.println("No left child, moving parent's pointer to right child.");
            root = root.getRight();
        }
        else if(root.getRight() == null) {
            System.out.println("No right child, moving parent's pointer to left child.");
            root = root.getLeft();
        }
        else {
            System.out.println("Both children exist. Setting data to logical predecessor's data and removing logical predecessor.");
            T predecessorData = getPredecessor(root).getData();
            root.setData(predecessorData);
            root = removeRecursive(root.getLeft(), predecessorData);
        }

        return  root;
    }

    private int sizeRecursive(BSTNode<T> root, int size){
        if (root == null)
            return  size;
        size += sizeRecursive(root.getLeft(), size);
        return size + sizeRecursive(root.getRight(), size);
    }

    @Override
    public Iterator<T> getIterator(Traversal traversalType){
        final MQueue<T> dataQueue = new MQueue<>();
        fillTraversalQueue(dataQueue, traversalType);

        return new Iterator<T>(){

            /**
             * Returns {@code true} if the iteration has more elements.
             * (In other words, returns {@code true} if {@link #next} would
             * return an element rather than throwing an exception.)
             *
             * @return {@code true} if the iteration has more elements
             */
            @Override
            public boolean hasNext() {
                return !dataQueue.isEmpty();
            }

            /**
             * Returns the next element in the iteration.
             *
             * @return the next element in the iteration
             * @throws NoSuchElementException if the iteration has no more elements
             */
            @Override
            public T next() {
                if(hasNext())
                    return dataQueue.remove();
                throw new NoSuchElementException("No more elements in the tree.");
            }
        };

    }

    private void fillTraversalQueue(MQueue<T> queue, Traversal traversalType){
        if(traversalType == Traversal.Preorder){
            preorderTraversal(queue, root);
        } else if (traversalType == Traversal.Inorder) {
            inOrderTraversal(queue, root);
        }else if (traversalType == Traversal.Postorder){
            postOrderTraversal(queue, root);
        }else {
            breadthFirstTraversal(queue, root);
        }
    }

    private void preorderTraversal(MQueue<T> infoQueue, BSTNode<T> root){
        if(root != null) {
            System.out.println("\nAdding \"" + root.getData() + "\" to queue");
            infoQueue.add(root.getData());
            System.out.print("Moving left; ");
            preorderTraversal(infoQueue, root.getLeft());
            System.out.print("Moving right; ");
            preorderTraversal(infoQueue, root.getRight());
        }
    }

    private void inOrderTraversal(MQueue<T> infoQueue, BSTNode<T> root){
        if (root != null){
            System.out.print("Moving left; ");
            inOrderTraversal(infoQueue, root.getLeft());
            System.out.println("\nAdding \"" + root.getData() + "\" to queue");
            infoQueue.add(root.getData());
            System.out.print("Moving right; ");
            inOrderTraversal(infoQueue, root.getRight());
        }
    }

    private void postOrderTraversal(MQueue<T> infoQueue, BSTNode<T> root){
        if (root != null){
            System.out.print("Moving left; ");
            postOrderTraversal(infoQueue, root.getLeft());
            System.out.print("Moving right; ");
            postOrderTraversal(infoQueue, root.getRight());
            System.out.println("\nAdding \"" + root.getData() + "\" to queue");
            infoQueue.add(root.getData());
        }
    }

    private void breadthFirstTraversal(MQueue<T> infoQueue, BSTNode<T> root){
        if(root == null)
            return;

        MQueue<BSTNode<T>> temp = new MQueue<>();
        temp.add(root);
        while (!temp.isEmpty()){
            BSTNode<T> curr = temp.remove();
            infoQueue.add(curr.getData());

            if(curr.getLeft() != null){
                temp.add(curr.getLeft());
            }
            if(curr.getRight() != null){
                temp.add(curr.getRight());
            }
        }
    }

    /**
     * Returns the logical predecessor of a given node.
     * @param root
     * @return
     */
    private BSTNode<T> getPredecessor(BSTNode<T> root){
        if(root.getLeft() == null){
            return null;
        }
        while (root.getRight() != null){
            root = root.getRight();
        }
        return root;
    }


    /**
     * Returns an iterator which traverses the binary tree using a DepthFirst Inorder Traversal.
     * <br/>This method is equivalent to calling {@code getIterator(Traversal.Inorder)}
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return getIterator(Traversal.Inorder);
    }
}
