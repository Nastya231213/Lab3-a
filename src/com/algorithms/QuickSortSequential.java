package com.algorithms;

/**
 * @file QuickSortSequential.java
 *
 * @brief Клас, який реалізує послідовну версію алгоритму QuickSort.
 *
 * Цей клас розширює AbstractQuickSort і перевизначає метод сортування для забезпечення послідовного сортування.
 */

/**
 * @class QuickSortSequential
 *
 * @brief Клас для реалізації послідовної версії алгоритму QuickSort.
 *
 * Клас надає методи для сортування масиву за допомогою рекурсивного підходу.
 */
public class QuickSortSequential extends AbstractQuickSort {

    /**
     * @brief Метод, що сортує заданий масив за допомогою алгоритму QuickSort.
     *
     * Цей метод ініціалізує процес сортування, викликаючи метод quickSort.
     *
     * @param array масив, який потрібно відсортувати.
     */
    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * @brief Рекурсивний метод, що виконує сортування підмасиву за допомогою алгоритму QuickSort.
     *
     * Цей метод розбиває підмасив навколо опорного елемента та рекурсивно сортує ліві та праві підмасиви.
     *
     * @param array масив, який потрібно відсортувати.
     * @param low початковий індекс підмасиву, який потрібно відсортувати.
     * @param high кінцевий індекс підмасиву, який потрібно відсортувати.
     */
    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }
}


