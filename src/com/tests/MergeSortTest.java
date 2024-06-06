package com.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;

import org.junit.Test;

import com.algorithms.MergeSortSequential;
import com.algorithms.MergeSortParallel;

/**
 * @file MergeSortTest.java
 *
 * @brief Тести для алгоритму сортування злиттям.
 */
public class MergeSortTest {

    /**
     * @brief Проводить тестування алгоритму сортування злиттям для заданого масиву.
     *
     * @param array масив для сортування
     */
    private void testMergeSort(int[] array) {
        int[] expected = array.clone();
        Arrays.sort(expected); 

        // Test sequential merge sort
        int[] sequentialArray = array.clone();
        MergeSortSequential mergeSort = new MergeSortSequential();
        mergeSort.mergeSort(sequentialArray, 0, sequentialArray.length - 1);
        assertArrayEquals("Послідовне сортування злиттям не вдалось", expected, sequentialArray);

        // Test parallel merge sort
        int[] parallelArray = array.clone();
        MergeSortParallel.parallelMergeSort(parallelArray);
        assertArrayEquals("Паралельне сортування злиттям не вдалось", expected, parallelArray);
    }

    /**
     * @brief Тестує відсортований масив.
     */
    @Test
    public void testSortedArray() {
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        testMergeSort(sortedArray);
    }

    /**
     * @brief Тестує зворотньо відсортований масив.
     */
    @Test
    public void testReverseSortedArray() {
        int[] reverseSortedArray = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        testMergeSort(reverseSortedArray);
    }

    /**
     * @brief Тестує випадковий масив.
     */
    @Test
    public void testRandomArray() {
        int[] randomArray = {3, 5, 1, 6, 9, 8, 2, 7, 4, 10};
        testMergeSort(randomArray);
    }

    /**
     * @brief Тестує масив з ідентичними елементами.
     */
    @Test
    public void testIdenticalElementsArray() {
        int[] identicalArray = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
        testMergeSort(identicalArray);
    }

    /**
     * @brief Тестує порожній масив.
     */
    @Test
    public void testEmptyArray() {
        int[] emptyArray = {};
        testMergeSort(emptyArray);
    }

    /**
     * @brief Тестує масив з одним елементом.
     */
    @Test
    public void testSingleElementArray() {
        int[] singleElementArray = {1};
        testMergeSort(singleElementArray);
    }

    /**
     * @brief Тестує великий випадковий масив.
     */
    @Test
    public void testLargeRandomArray() {
        int[] largeRandomArray = new int[1000];
        for (int i = 0; i < largeRandomArray.length; i++) {
            largeRandomArray[i] = (int) (Math.random() * 1000);
        }
        testMergeSort(largeRandomArray);
    }


    /**
     * @brief Тестує нульовий масив.
     */
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

    /**
     * @brief Тестує неправильні індекси масиву.
     */
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

    /**
     * @brief Тестує неправильний діапазон сортування.
     */
    @Test
    public void testIncorrectRange() {
        int[] array = {1, 2, 3};
        MergeSortSequential mergeSort = new MergeSortSequential();
        mergeSort.mergeSort(array, 2, 0); 

        int[] parallelArray = {1, 2, 3};
        MergeSortParallel.parallelMergeSort(parallelArray); 

        assertArrayEquals("Послідовне сортування злиттям з неправильним діапазоном не вдалось", new int[]{1, 2, 3}, array);
        assertArrayEquals("Паралельне сортування злиттям з неправильним діапазоном не вдалось", new int[]{1, 2, 3}, parallelArray);
    }
}
