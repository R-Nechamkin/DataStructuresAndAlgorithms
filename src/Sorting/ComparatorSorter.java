package Sorting;

import java.util.Arrays;
import java.util.Comparator;

public abstract class ComparatorSorter<T> implements Sorter<T> {

    private Comparator<T> comparator = null;


    public ComparatorSorter() {

    }

    /**
     * This method
     * @param comparator
     */
    public ComparatorSorter(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * This method is called to sort an array of {@code T} objects using the comparator already created for the class
     * <br>If no comparator was created for the class, this method attempts to sort using natural ordering. Notably, this
     *  attempt will fail if {@code T} does not implement the Comparable.java interface
     * @param arr An array of type {@code T}
     * @return A sorted copy of {@code arr}
     * @throws IllegalStateException If no comparator was previously set for the class and {@code T} does not implement Comparable.java
     */
    @Override
    public T[] sort(T[] arr)  throws IllegalArgumentException{
        Comparator<T> localComparator = null;
        if(comparator == null){
            try {
                localComparator = (a, b) -> ((Comparable<T>) a).compareTo(b);
            }catch (ClassCastException e) {
                throw new IllegalStateException("If you have not provided a comparator to the sorter," +
                        " you can only sort elements which implement Comparable.java");
            }
        }
        return sort(arr, localComparator);
    }

    public T[] sort(T[] arr, Comparator<T> localComparator){
        T[] toSort = Arrays.copyOf(arr, arr.length);

        classSpecificSort(toSort,localComparator);

        return toSort;
    }

    /**
     * This method returns a {@code String} description of the last sort which was performed.
     * <br>This description will likely be many lines long, describing every swap which was performed.
     * @return A {@code String} description of the last sort which was performed using this sorter.
     * @throws IllegalStateException If the {@code Sorter} has not yet performed any sorts yet
     */
    public abstract String getLastSortDescription() throws IllegalStateException;


    /**
     * This method sorts a given array using the given comparator. (This comparator can be one which mimics natural ordering).
     * @param arr
     * @param localComparator
     */
    protected abstract void classSpecificSort(T[] arr, Comparator<T> localComparator);

    protected int compare(T object1, T object2){
        return comparator.compare(object1, object2);
    }

    protected void swap(T[] arr, int index1, int index2){
        if (index1 != index2){
            T temp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = temp;
        }
    }

    protected String printArray(T[] arr){
        return printArray(arr, 0, arr.length);
    }

    /**
     * This method returns a String representation of an array from {@code begin} (inclusive) until {@code end1} (exclusive).
     *
     * @param arr
     * @param begin
     * @param end
     * @return
     */
    protected String printArray(T[] arr, int begin, int end){
        StringBuilder sb = new StringBuilder();
        sb.append("{" + arr[begin]);
        for (int i = begin + 1; i< end; i++){
            sb.append("," + arr[i]);
        }
        sb.append("}");
        return sb.toString();
    }


}
