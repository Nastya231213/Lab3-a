/**
 * @file AbstractQuickSort.java
 * @brief Абстрактний клас, що визначає структуру для реалізацій алгоритму Quick Sort.
 */

package com.algorithms;

/**
 * @class AbstractQuickSort
 * @brief Абстрактний клас для алгоритму Quick Sort.
 *
 * Цей клас надає шаблон для реалізацій алгоритму Quick Sort, включаючи методи для розділення масиву та обміну елементів.
 */
public abstract class AbstractQuickSort {

    /**
     * @brief Розділяє масив на дві частини навколо опорного елемента.
     *
     * @param array Масив для розділення.
     * @param low Початковий індекс підмасиву.
     * @param high Кінцевий індекс підмасиву.
     * @return Індекс опорного елемента після розділення.
     */
    protected int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    /**
     * @brief Обмінює два елементи в масиві.
     *
     * @param array Масив, у якому здійснюється обмін.
     * @param i Індекс першого елемента.
     * @param j Індекс другого елемента.
     */
    protected void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public void quickSortSequential(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(array, left, right);
            quickSortSequential(array, left, pivotIndex - 1);
            quickSortSequential(array, pivotIndex + 1, right);
        }
    }
    /**
     * @brief Абстрактний метод для сортування масиву.
     *
     * @param array Масив для сортування.
     */
    public abstract void sort(int[] array);
}
