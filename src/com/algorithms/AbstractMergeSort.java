/**
 * @file AbstractMergeSort.java
 * @brief Абстрактний клас, що визначає структуру для реалізацій алгоритму Merge Sort.
 */

package com.algorithms;

/**
 * @class AbstractMergeSort
 * @brief Абстрактний клас для алгоритму Merge Sort.
 *
 * Цей клас надає шаблон для реалізацій алгоритму Merge Sort, включаючи метод для об'єднання двох підмасивів.
 */
public abstract class AbstractMergeSort {

    /**
     * @brief Абстрактний метод, який має бути реалізований підкласами для виконання алгоритму Merge Sort.
     *
     * @param array Масив для сортування.
     * @param left Ліва межа підмасиву.
     * @param right Права межа підмасиву.
     */
    protected abstract void mergeSort(int[] array, int left, int right);

    /**
     * @brief Об'єднує два підмасиви в один відсортований масив.
     *
     * @param array Масив, який містить підмасиви для об'єднання.
     * @param left Ліва межа першого підмасиву.
     * @param mid Середина, що розділяє підмасиви.
     * @param right Права межа другого підмасиву.
     */
    protected void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; ++i) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    /**
     * @brief Друкує елементи масиву.
     *
     * @param array Масив для друку.
     */
    public void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
