package myQueue;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * My own queue class.
 * I decided to implement the JCF Queue interface; I do not think that adds any additional functionality
 *  to my class but would only be extra work.
 * @param <T>
 */
public class MQueue<T> implements Queue<T>, Iterable<T>{


	/*
	 * These methods use the default modifier and not private so that they can be tested using the JUnit test class.
	 * This obviously violates the principle of least privelege, but since the only classes in the package are this
	 *  class and the test class, I judged the violation to be worth it to allow testing.
	 */
	Object[] array;
	int tail;
	int size;
	int head;
	int capacity;
	
	
	/**
	 * Creates a new MQueue with a starting capacity of 10
	 */
	public MQueue() {
		capacity = 10;
		array = new Object[capacity];
		tail = 0;
		size = 0;
		head = 0;
	}

	/**
	 * Creates a new MQueue with an initial capacity equal to the parameter
	 * @param capacity An int holding what should be the initial capacity of the queue
	 */
	public MQueue(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException("You cannot create a queue with 0 or negative capacity.");
		this.capacity = capacity;
		array = new Object[capacity];
		tail = 0;
		size = 0;
		head = 0;
	}
	
	/**
	 * Places an element on the queue.
	 * Following what I thought was common practice, I implemented this method in the method called add().
	 * @param element The element to be added
	 */
	public void enqueue(T element) {
		add(element);
	}
	
	/**
	 * Removes the head of the queue from the queue and returns it.
	 * Following what I thought was common practice, I implemented this method in the method called remove().
	 * @return the element which is currently the head of the queue
	 */
	public T dequeue() {
		return remove();
	}
	
	/**
	 * Returns, but does not remove the head of the queue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T peek() {
		if (isEmpty())
			throw new IllegalStateException("Queue is empty");
		
		return (T) array[head];
	}
	
	/**
	 * Returns true if the array is empty and false otherwise
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns the number of elements in the array
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Returns the maximum number of elements allowed in the array
	 * @return
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * Changes the number of elements allowed in the array
	 * @param capacity An int holding what should be the new capacity of the queue
	 * @throws IllegalStateException if capacity > the number of elements currently in the queue
	 */
	public void changeCapacity(int capacity) {
		if (capacity < (head + size) % array.length)
			throw new IllegalStateException("There are more elements in the queue than the new capacity allows for.");
		
		this.capacity = capacity;

		Object[] newArray = new Object[capacity];		
		int index = 0;
		for (int i = head; i < head + size; i++) {
			newArray[index++] = array[i % array.length];
		}
		
		head = 0;
		tail = index;
		
		array = newArray;
	}

	@Override
	public boolean contains(Object o) {
		for (int i = head; i < head + size; i++) {
			if (array[i % array.length].equals(o))
				return true;
		}

		return false;
	}
	

	@Override
	public Iterator<T> iterator() {
		return new QIterator();
	}
	
	private class QIterator implements Iterator<T>{
		private int i = head;
		
		@Override
		public boolean hasNext() {
			return i < (head + size);
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new IllegalStateException("There are no more elements left in the queue.");
			}
			@SuppressWarnings("unchecked")
			T element = (T) array[i % array.length];
			i ++;
			return element;
		}
		
	}

	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];		
		int index = 0;
		for (int i = head; i < head + size; i++) {
			result[index++] = array[i % array.length];
		}
		
		return result;
	}
	


	@Override
	public boolean containsAll(Collection c) {
		for (Object o: c) {
			if (!contains(o))
				return false;
		}
		return true;
	}

	@Override
	public boolean addAll(Collection c) {
		for (Object o: c) {
			add((T) o);
		}
		return true;
	}



	@Override
	public void clear() {
		for (int i = head; i < head + size; i++) {
			array[i % array.length] = null;
		}
	}

	/**
	 * A method to add an element to the queue.
	 * Note that since this queue is resizable, the method is exactly the same 
	 *  as the offer() method.
	 */
	@Override
	public boolean add(T e) {
		return offer(e);
	}


	/**
	 * A method to add an element to the queue.
	 * This method follows the interface by returning true if the element was added and false if it was not,
	 * but since this is a resizable queue, the method should always return true.
	 */
	@Override
	public boolean offer(T e) {
		if (size == capacity)
			changeCapacity(capacity * 2);
		
		array[tail] = e;
		tail = (tail + 1) % capacity;
		size ++;
		return true;
	}

	@Override
	public T remove() {
		T element = poll();
		if (element == null)
			throw new IllegalStateException("Queue is empty");
			
		return element;
	}

	@Override
	public T poll() {
		if (isEmpty())
			return null;
		
		@SuppressWarnings("unchecked")
		T removed = (T) array[head];
		array[head] = null;
		
		head = (head + 1) % array.length;
		size --;
		return removed;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T element() {
		if (isEmpty())
			throw new IllegalStateException("Queue is empty");
		
		return (T) array[head];
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i = head;
		for (; i < head + size -1; i++) {
			sb.append(array[i % array.length] + ",");
		}
		sb.append(array[i % array.length]);
		return sb.toString();
				
	}
	
	
	/*Following are methods included in the Queue interface which I did not implement in my class.*/
	/*They all throw a Runtime Exception if they are called*/
	
	
	/**
	 * I did not implement this method in my queue class
	 * @throws RuntimeException if you try to call it on an object of this class
	 */
	@Override
	public Object[] toArray(Object[] a) {
		throw new RuntimeException("Sorry, this method doesn't work for this class");
	}

	/**
	 * I did not implement this method in my queue class
	 * @throws RuntimeException if you try to call it on an object of this class
	 */
	@Override
	public boolean remove(Object o) {
		throw new RuntimeException("Sorry, this method doesn't work for this class");
	}
	
	/**
	 * I did not implement this method in my queue class
	 * @throws RuntimeException if you try to call it on an object of this class
	 */
	@Override
	public boolean removeAll(Collection c) {
		throw new RuntimeException("Sorry, this method doesn't work for this class");
	}

	/**
	 * I did not implement this method in my queue class
	 * @throws RuntimeException if you try to call it on an object of this class
	 */
	@Override
	public boolean retainAll(Collection c) {
		throw new RuntimeException("Sorry, this method doesn't work for this class");
	}
	

}
