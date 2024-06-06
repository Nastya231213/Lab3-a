package com.algorithms;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

/**
 * @file QuickSortParallel.java
 * 
 * @brief Клас, який реалізує паралельну версію алгоритму QuickSort.
 * 
 * Він розширює клас AbstractQuickSort і перевизначає метод сортування для забезпечення паралельного сортування.
 */

/**
 * @class QuickSortParallel
 * 
 * @brief Клас для реалізації паралельної версії алгоритму QuickSort.
 * 
 * Клас надає методи для сортування масиву за допомогою паралельного підходу.
 */
public class QuickSortParallel extends AbstractQuickSort {

    private static final int THRESHOLD = 1000;

    private static class QuickSortTask extends RecursiveAction {
        private final int[] array;
        private final int left;
        private final int right;
        private final AbstractQuickSort sorter;

        public QuickSortTask(int[] array, int left, int right, AbstractQuickSort sorter) {
            this.array = array;
            this.left = left;
            this.right = right;
            this.sorter = sorter;
        }

        @Override
        protected void compute() {
            if (right - left < THRESHOLD) {
                sorter.quickSortSequential(array, left, right); // Виклик послідовного сортування, якщо розмір масиву менше порогового значення
            } else {
                int pivotIndex = sorter.partition(array, left, right);
                QuickSortTask leftTask = new QuickSortTask(array, left, pivotIndex - 1, sorter);
                QuickSortTask rightTask = new QuickSortTask(array, pivotIndex + 1, right, sorter);
                invokeAll(leftTask, rightTask); // Викликаємо обидва підзавдання асинхронно
            }
        }
    }

    @Override
    public void sort(int[] array) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new QuickSortTask(array, 0, array.length - 1, this));
        pool.shutdown();
    }
}

