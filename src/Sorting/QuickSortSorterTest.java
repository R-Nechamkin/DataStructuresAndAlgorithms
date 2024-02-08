package Sorting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortSorterTest {

    @Test
    void TestSortWithStandardArray() {
        Integer[] arr = {3,8,1,7,4,2};
        Integer[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        ComparatorSorter<Integer> sorter = new QuickSortSorter<>();

        assertArrayEquals(sorter.sort(arr), sorted);
    }

    @Test
    void TestSortWithArrayWhichIsAlreadySorted() {
        Integer[] arr = {1,2,3,4,5,6,7};
        Integer[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        ComparatorSorter<Integer> sorter = new QuickSortSorter<>();

        assertArrayEquals(sorter.sort(arr), sorted);
    }

    @Test
    void TestSortWithArrayWhichIsAllFives(){
        Integer[] arr = {5,5,5,5,5,5,5,5,5};
        ComparatorSorter<Integer> sorter = new QuickSortSorter<>();

        assertArrayEquals(sorter.sort(arr), arr);
    }
}