package MyStack;

public interface StackInterface<T> {

    void push(T data);
    T pop();
    int size();

    boolean isEmpty();
}
