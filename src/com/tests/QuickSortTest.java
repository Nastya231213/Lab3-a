package com.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.algorithms.QuickSort;
import com.algorithms.QuickSortParallel;

public class QuickSortTest {

    @Test
    public void testQuickSort() {
        int[] unsortedArray = {5, 3, 8, 4, 2, 1, 7, 6};
        int[] expectedSortedArray = {1, 2, 3, 4, 5, 6, 7, 8};
        QuickSort.quickSort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);
    }
    @Test
    public void testParallelQuickSort() {
        int[] unsortedArray = {5, 3, 8, 4, 2, 1, 7, 6};
        int[] expectedSortedArray = {1, 2, 3, 4, 5, 6, 7, 8};
        QuickSortParallel.parallelQuickSort(unsortedArray);
        
        assertArrayEquals(expectedSortedArray, unsortedArray);
    }
}
