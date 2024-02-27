package BinarySearchTree;

public class BSTNode<T> {
    private T data;
    private BSTNode<T> right;
    private BSTNode<T> left;


    public BSTNode(T data){
        this.data = data;
    }


    /**
     * Returns the data that this node wraps around
     * @return the data that the node contains
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data which this node wraps around
     * @param data  the data that the node should contain
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Returns this node's left child
     * @return the left child of this node
     */
    public BSTNode<T> getLeft() {
        return left;
    }

    /**
     * Sets a left child node for this node
     * @param link the left child for this node
     */
    public void setLeft(BSTNode<T> link) {
        this.left = link;
    }

    /**
     * Returns this node's right child
     * @return the right child of this node
     */
    public BSTNode<T> getRight() {
        return right;
    }

    /**
     * Sets a right child node for this node
     * @param link the right child for this node
     */
    public void setRight(BSTNode<T> link) {
        this.right = link;
    }


}
