package com.algorithms;

/**
 * @file MergeSortSequential.java
 * @brief Реалізація послідовного сортування злиттям.
 */

/**
 * @class MergeSortSequential
 * @brief Реалізація послідовного сортування злиттям.
 *
 * Цей клас розширює AbstractMergeSort і надає реалізацію
 * послідовного сортування масиву цілих чисел методом злиття.
 */
public class MergeSortSequential extends AbstractMergeSort {

    /**
     * Метод для послідовного сортування масиву цілих чисел методом злиття.
     * @param array Масив для сортування.
     * @param left Ліва межа сортування.
     * @param right Права межа сортування.
     */
    @Override
    public void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            merge(array, left, mid, right);
        }
    }
}
