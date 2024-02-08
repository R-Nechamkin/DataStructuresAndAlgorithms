package Sorting;

import java.util.Comparator;

public class QuickSortSorter<T> extends ComparatorSorter<T> {

    private StringBuilder sortingDescription;

    public QuickSortSorter() {
    }

    public QuickSortSorter(Comparator<T> comparator) {
        super(comparator);
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


    @Override
    protected void classSpecificSort(T[] arr, Comparator<T> localComparator){
        sortingDescription = new StringBuilder();
        classSpecificSort(arr, localComparator,0, arr.length);
    }

    private void classSpecificSort(T[] arr, Comparator<T> localComparator, int begin, int end) throws ClassCastException{
        if(begin >= end){
            return;
        }
        sortingDescription.append("Sorting " + printArray(arr) + " from indices " + begin + " to " + end + "\n");
        T pivot = arr[end -1];
        sortingDescription.append("The current pivot is " + pivot + "\n");
        int i = -1;
        for (int j = 0; j < end -1; j++) {
            if(lessThanPivot(arr[j], pivot, localComparator)){
                i ++;
                swap(arr, i, j);
            }
        }
        i++;
        swap(arr, i, end-1);
        int pivotIndex = i;
        sortingDescription.append("We sorted the array to become " + printArray(arr) + "\n\n");

        classSpecificSort(arr, localComparator,begin, pivotIndex);
        classSpecificSort(arr, localComparator, pivotIndex + 1, end);

    }

    private boolean lessThanPivot(T element, T pivot, Comparator<T> comparator){
        return comparator.compare(element, pivot) < 1;
    }


}
