package com.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;

import org.junit.Test;

import com.algorithms.MergeSortSequential;
import com.algorithms.MergeSortParallel;

public class MergeSortTest {

    private void testMergeSort(int[] array) {
        int[] expected = array.clone();
        Arrays.sort(expected); 

        // Test sequential merge sort
        int[] sequentialArray = array.clone();
        MergeSortSequential mergeSort = new MergeSortSequential();
        mergeSort.mergeSort(sequentialArray, 0, sequentialArray.length - 1);
        assertArrayEquals("Sequential merge sort failed", expected, sequentialArray);

        // Test parallel merge sort
        int[] parallelArray = array.clone();
        MergeSortParallel.parallelMergeSort(parallelArray);
        assertArrayEquals("Parallel merge sort failed", expected, parallelArray);
    }

    @Test
    public void testSortedArray() {
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        testMergeSort(sortedArray);
    }

    @Test
    public void testReverseSortedArray() {
        int[] reverseSortedArray = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        testMergeSort(reverseSortedArray);
    }

    @Test
    public void testRandomArray() {
        int[] randomArray = {3, 5, 1, 6, 9, 8, 2, 7, 4, 10};
        testMergeSort(randomArray);
    }

    @Test
    public void testIdenticalElementsArray() {
        int[] identicalArray = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
        testMergeSort(identicalArray);
    }

    @Test
    public void testEmptyArray() {
        int[] emptyArray = {};
        testMergeSort(emptyArray);
    }

    @Test
    public void testSingleElementArray() {
        int[] singleElementArray = {1};
        testMergeSort(singleElementArray);
    }

    @Test
    public void testLargeRandomArray() {
        int[] largeRandomArray = new int[1000];
        for (int i = 0; i < largeRandomArray.length; i++) {
            largeRandomArray[i] = (int) (Math.random() * 1000);
        }
        testMergeSort(largeRandomArray);
    }


    @Test
    public void testNullArray() {
        assertThrows(NullPointerException.class, () -> {
            int[] nullArray = null;
            MergeSortSequential mergeSort = new MergeSortSequential();
            mergeSort.mergeSort(nullArray, 0, 0);
        });

        assertThrows(NullPointerException.class, () -> {
            int[] nullArray = null;
            MergeSortParallel.parallelMergeSort(nullArray);
        });
    }

    @Test
    public void testInvalidIndices() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            int[] array = {1, 2, 3};
            MergeSortSequential mergeSort = new MergeSortSequential();
            mergeSort.mergeSort(array, -1, 2);
        });

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            int[] array = {1, 2, 3};
            MergeSortParallel.parallelMergeSort(array);
        });

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            int[] array = {1, 2, 3};
            MergeSortSequential mergeSort = new MergeSortSequential();
            mergeSort.mergeSort(array, 0, 5);
        });

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            int[] array = {1, 2, 3};
            MergeSortParallel.parallelMergeSort(array);
        });
    }

    @Test
    public void testIncorrectRange() {
        int[] array = {1, 2, 3};
        MergeSortSequential mergeSort = new MergeSortSequential();
        mergeSort.mergeSort(array, 2, 0); 

        int[] parallelArray = {1, 2, 3};
        MergeSortParallel.parallelMergeSort(parallelArray); 

        assertArrayEquals("Sequential merge sort with incorrect range failed", new int[]{1, 2, 3}, array);
        assertArrayEquals("Parallel merge sort with incorrect range failed", new int[]{1, 2, 3}, parallelArray);
    }
}
