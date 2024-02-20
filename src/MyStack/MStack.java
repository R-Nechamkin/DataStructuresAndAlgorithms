package MyStack;


import java.util.Vector;

public class MStack<T> implements StackInterface<T> {
    /*
     * The internal variables and {@code Node<T>} class use the default modifier and not private so that they can be tested using the JUnit test class.
     * This obviously violates the principle of least privelege, but since the only classes in the package are this
     *  class and the test class, I judged the violation to be worth it to allow testing.
     */

    Node<T> head;
    int size;

    @Override
    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    @Override
    public T pop() {
        T toReturn = head.data;
        head = head.next;

        size --;
        return toReturn;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    class Node<T>{
        T data;
        Node<T> next;

        public Node(T data){
            this.data = data;
        }
    }



}
