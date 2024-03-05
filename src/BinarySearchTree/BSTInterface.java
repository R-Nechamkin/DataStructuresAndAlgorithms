package BinarySearchTree;

import interfaces.CollectionInterface;

import java.util.Iterator;

/**
 * An interface for an unbounded Binary Search Tree.
 * The tree does not allow null elements.
 */
public interface BSTInterface<T> extends CollectionInterface<T>, Iterable<T>{
    public enum Traversal {Preorder, Inorder, Postorder, BreadthFirst};

    T min();

    T max();

    public Iterator<T> getIterator(Traversal orderType);
}
