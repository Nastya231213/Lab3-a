package com.tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import com.algorithms.QuickSortSequential;
import com.algorithms.QuickSortParallel;

public class QuickSortTest {

    @Test
    public void testRecursiveQuickSort() {
        int[] unsortedArray = {5, 3, 8, 4, 2, 1, 7, 6};
        int[] expectedSortedArray = {1, 2, 3, 4, 5, 6, 7, 8};
        new QuickSortSequential().sort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);
    }

    @Test
    public void testParallelQuickSort() throws InterruptedException {
        int[] unsortedArray = {5, 3, 8, 4, 2, 1, 7, 6};
        int[] expectedSortedArray = {1, 2, 3, 4, 5, 6, 7, 8};
        new QuickSortParallel().sort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);
    }
    @Test
    public void testEmptyArray() {
        int[] unsortedArray = new int[0];
        int[] expectedSortedArray = new int[0];
        new QuickSortSequential().sort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);

        new QuickSortParallel().sort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);
    }
    @Test
    public void testSortedArray() {
        int[] unsortedArray = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] expectedSortedArray = {1, 2, 3, 4, 5, 6, 7, 8};
        new QuickSortSequential().sort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);

        new QuickSortParallel().sort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);
    }
    @Test
    public void testLargeArray() throws InterruptedException {
        int size = 100000;
        int[] unsortedArray = new int[size];
        for (int i = 0; i < size; i++) {
            unsortedArray[i] = (int) (Math.random() * size); 
        }
        int[] expectedSortedArray = Arrays.copyOf(unsortedArray, size);
        Arrays.sort(expectedSortedArray); 

        new QuickSortSequential().sort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);

        new QuickSortParallel().sort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);
    }
    @Test
    public void testDuplicateValues() {
        int[] unsortedArray = {5, 3, 8, 4, 2, 2, 1, 7, 6};
        int[] expectedSortedArray = {1, 2, 2, 3, 4, 5, 6, 7, 8};
        new QuickSortSequential().sort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);

        new QuickSortParallel().sort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);
    }

}