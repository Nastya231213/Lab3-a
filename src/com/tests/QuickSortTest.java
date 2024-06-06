package com.tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import com.algorithms.QuickSortSequential;
import com.algorithms.QuickSortParallel;

/**
 * @file QuickSortTest.java
 *
 * @brief Тести для алгоритму швидкого сортування.
 */
public class QuickSortTest {

    /**
     * @brief Тестує рекурсивний алгоритм швидкого сортування.
     */
    @Test
    public void testRecursiveQuickSort() {
        int[] unsortedArray = {5, 3, 8, 4, 2, 1, 7, 6};
        int[] expectedSortedArray = {1, 2, 3, 4, 5, 6, 7, 8};
        new QuickSortSequential().sort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);
    }

    /**
     * @brief Тестує паралельний алгоритм швидкого сортування.
     */
    @Test
    public void testParallelQuickSort() throws InterruptedException {
        int[] unsortedArray = {5, 3, 8, 4, 2, 1, 7, 6};
        int[] expectedSortedArray = {1, 2, 3, 4, 5, 6, 7, 8};
        new QuickSortParallel().sort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);
    }

    /**
     * @brief Тестує порожній масив.
     */
    @Test
    public void testEmptyArray() {
        int[] unsortedArray = new int[0];
        int[] expectedSortedArray = new int[0];
        new QuickSortSequential().sort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);

        new QuickSortParallel().sort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);
    }

    /**
     * @brief Тестує відсортований масив.
     */
    @Test
    public void testSortedArray() {
        int[] unsortedArray = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] expectedSortedArray = {1, 2, 3, 4, 5, 6, 7, 8};
        new QuickSortSequential().sort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);

        new QuickSortParallel().sort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);
    }

    /**
     * @brief Тестує великий масив.
     */
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

    /**
     * @brief Тестує масив з дубльованими значеннями.
     */
    @Test
    public void testDuplicateValues() {
        int[] unsortedArray = {5, 3, 8, 4, 2, 2, 1, 7, 6};
        int[] expectedSortedArray = {1, 2, 2, 3, 4, 5, 6, 7, 8};
        new QuickSortSequential().sort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);

        new QuickSortParallel().sort(unsortedArray);
        assertArrayEquals(expectedSortedArray, unsortedArray);
    }

    /**
     * @brief Генерує масив з випадковими значеннями.
     *
     * @param size розмір масиву
     * @return згенерований масив
     */
    private int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * size);
        }
        return array;
    }

    /**
     * @brief Тестує час виконання рекурсивного алгоритму швидкого сортування.
     */
    @Test
    public void testRecursiveQuickSortTime() {
        int size = 100000; // Збільшений розмір масиву
        int[] unsortedArray = generateRandomArray(size);

        long startTime = System.nanoTime();
        new QuickSortSequential().sort(unsortedArray);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000; // Конвертація в мілісекунди
        System.out.println("Час послідовного швидкого сортування: " + duration + " мілісекунд");

        assertTrue(duration >= 0);
    }

    /**
     * @brief Тестує час виконання паралельного алгоритму швидкого сортування.
     */
    @Test
    public void testParallelQuickSortTime() throws InterruptedException {
        int size = 100000; // Збільшений розмір масиву
        int[] unsortedArray = generateRandomArray(size);

        long startTime = System.nanoTime();
        new QuickSortParallel().sort(unsortedArray);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000; // Конвертація в мілісекунди
        System.out.println("Час паралельного швидкого сортування: " + duration + " мілісекунд");

        assertTrue(duration >= 0);
    }
}

