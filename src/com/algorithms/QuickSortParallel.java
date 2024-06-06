package com.algorithms;

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

    /**
     * @class SortThread
     * 
     * @brief Приватний статичний вкладений клас, який представляє завдання сортування для підмасиву за допомогою алгоритму QuickSort.
     * 
     * Він наслідує клас Thread.
     */
    private static class SortThread extends Thread {
        private final int[] array;
        private final int left;
        private final int right;
        private final AbstractQuickSort sorter;

        /**
         * @brief Конструктор класу SortThread.
         * 
         * @param array масив, який потрібно відсортувати.
         * @param left початковий індекс підмасиву, який потрібно відсортувати.
         * @param right кінцевий індекс підмасиву, який потрібно відсортувати.
         * @param sorter екземпляр сортувальника для використання при розбитті.
         */
        public SortThread(int[] array, int left, int right, AbstractQuickSort sorter) {
            this.array = array;
            this.left = left;
            this.right = right;
            this.sorter = sorter;
        }

        /**
         * @brief Метод, що виконує завдання сортування.
         * 
         * Він сортує підмасив шляхом розбиття його навколо опорного елемента,
         * а потім рекурсивно сортує ліві та праві підмасиви паралельно.
         */
        @Override
        public void run() {
            if (right > left) {
                int pivotIndex = sorter.partition(array, left, right);
                SortThread leftThread = new SortThread(array, left, pivotIndex - 1, sorter);
                SortThread rightThread = new SortThread(array, pivotIndex + 1, right, sorter);

                leftThread.start();
                rightThread.start();

                try {
                    leftThread.join();
                    rightThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @brief Метод, що сортує заданий масив за допомогою паралельного алгоритму QuickSort.
     * 
     * Цей метод ініціалізує процес сортування, запустивши перший SortThread.
     * 
     * @param array масив, який потрібно відсортувати.
     */
    @Override
    public void sort(int[] array) {
        SortThread thread = new SortThread(array, 0, array.length - 1, this);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

