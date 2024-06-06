package com.algorithms;

/**
 * @file MergeSortParallel.java
 * @brief Реалізація паралельного сортування злиттям.
 */

/**
 * @class MergeSortParallel
 * @brief Реалізація паралельного сортування злиттям.
 *
 * Цей клас розширює AbstractMergeSort і надає реалізацію
 * паралельного сортування масиву цілих чисел методом злиття.
 */
public class MergeSortParallel extends AbstractMergeSort {

    /**
     * @class SortThread
     * @brief Потік для паралельного сортування методом злиття.
     */
    private static class SortThread extends Thread {
        private final int[] array;
        private final int left;
        private final int right;
        private final MergeSortParallel mergeSort;

        /**
         * Конструктор потоку сортування.
         * @param array Масив для сортування.
         * @param left Ліва межа сортування.
         * @param right Права межа сортування.
         * @param mergeSort Об'єкт класу MergeSortParallel, який керує сортуванням.
         */
        public SortThread(int[] array, int left, int right, MergeSortParallel mergeSort) {
            this.array = array;
            this.left = left;
            this.right = right;
            this.mergeSort = mergeSort;
        }

        @Override
        public void run() {
            if (left < right) {
                int mid = (left + right) / 2;
                SortThread leftThread = new SortThread(array, left, mid, mergeSort);
                SortThread rightThread = new SortThread(array, mid + 1, right, mergeSort);

                leftThread.start();
                rightThread.start();

                try {
                    leftThread.join();
                    rightThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mergeSort.merge(array, left, mid, right);
            }
        }
    }

    /**
     * Метод для паралельного сортування масиву цілих чисел методом злиття.
     * @param array Масив для сортування.
     */
    public static void parallelMergeSort(int[] array) {
        MergeSortParallel mergeSort = new MergeSortParallel();
        SortThread thread = new SortThread(array, 0, array.length - 1, mergeSort);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            super.merge(array, left, mid, right);
        }
    }
}


