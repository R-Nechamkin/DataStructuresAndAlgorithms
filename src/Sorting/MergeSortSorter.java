package Sorting;


import java.util.Arrays;
import java.util.Comparator;

public class MergeSortSorter<T> extends ComparatorSorter<T> {
    StringBuilder sortingDescription;


    public MergeSortSorter() {
    }

    public MergeSortSorter(Comparator<T> comparator) {
        super(comparator);
    }


    @Override
    protected void classSpecificSort(T[] arr, Comparator<T> localComparator){
        sortingDescription = new StringBuilder();
        T[] sorted = Arrays.copyOf(arr, arr.length);
        sorted = sortArraySection(arr, sorted, localComparator, 0, arr.length/2, arr.length);

        for (int i = 0; i < sorted.length; i++) {
            arr[i] = sorted[i];
        }
    }


    private T[] sortArraySection(T[] arr, T[] temp, Comparator<T> comparator, int begin, int end1, int end2){
        sortingDescription.append("Sorting "+ printArray(arr, begin, end1-1) + " and " + printArray(arr, end1, end2-1) + "\n");
        int mid1 = (end1 - begin)/ 2 + begin;
        int mid2 = (end2 - end1)/2  + end1;

        // base case
        if(begin == mid1 && end1 == mid2){
            sortingDescription.append("Both sections consist of only one element.\n");
            sortingDescription.append("Merging the sections.\n");
            mergeSections(arr, temp, comparator, begin, end1, end2);
            return arr;
        }

        if (begin != mid1) {
            sortingDescription.append("Splitting section 1.\n");
            sortArraySection(arr, temp, comparator, begin, mid1, end1);
        }
        sortingDescription.append("Splitting section 2.\n");
        sortArraySection(arr, temp, comparator, end1, mid2, end2);


        mergeSections(arr, temp, comparator, begin, end1, end2);
        return arr;
    }

    /**
     * This method copies two sections of a sorted array into a second array, sorting them as it does so.
     * <br>The first section goes from {@code begin} (inclusive) until {@code end1} (exclusive). The second
     *  section goes from {@code end1} (inclusive) until {@code end2} (exclusive).
     * @param arr
     * @param temp
     * @param comparator
     * @param begin
     * @param end1  An integer representing the first index of the second section
     * @param end2  An integer representing one more than the last index of the second section
     */
    private void mergeSections(T[] arr, T[] temp, Comparator<T> comparator, int begin, int end1, int end2){
        sortingDescription.append("Merging sections " + printArray(arr, begin, end1-1) + " and " + printArray(arr, end1, end2-1) + "\n");

        int leftIndex = begin;
        int rightIndex = end1;
        for (int i = begin; i < end2; i++) {
            if(leftIndex == end1){
                temp[i] = arr[rightIndex++];
            } else if (rightIndex == end2) {
                temp[i] = arr[leftIndex++];
            }
            else if(comparator.compare(arr[leftIndex], arr[rightIndex]) <= 0){
                temp[i] = arr[leftIndex++];
            }
            else {
                temp[i] = arr[rightIndex++];
            }
            sortingDescription.append("Placed " + temp[i] + " in index " + i + "\n");
        }

        for (int i = begin; i < end2; i++) {
            arr[i] = temp[i];
        }
        sortingDescription.append("Now the sections look like " + printArray(arr, begin, end2 -1) + "\n");
    }


    /**
     * This method returns a {@code String} description of the last sort which was performed.
     * <br>This description will likely be many lines long, describing every swap which was performed.
     *
     * @return A {@code String} description of the last sort which was performed using this sorter.
     * @throws IllegalStateException If the {@code Sorter} has not yet performed any sorts yet
     */
    @Override
    public String getLastSortDescription() throws IllegalStateException{
        if (sortingDescription == null) {
            throw new IllegalStateException("No sort has been performed yet.");
        }
        return sortingDescription.toString();
    }


}
