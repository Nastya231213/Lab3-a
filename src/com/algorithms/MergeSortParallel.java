package com.algorithms;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeSortParallel extends AbstractMergeSort {

    private static class SortTask extends RecursiveAction {
        private final int[] array;
        private final int left;
        private final int right;
        private final MergeSortParallel mergeSort;

        public SortTask(int[] array, int left, int right, MergeSortParallel mergeSort) {
            this.array = array;
            this.left = left;
            this.right = right;
            this.mergeSort = mergeSort;
        }

        @Override
        protected void compute() {
            if (left < right) {
                int mid = (left + right) / 2;

                SortTask leftTask = new SortTask(array, left, mid, mergeSort);
                SortTask rightTask = new SortTask(array, mid + 1, right, mergeSort);

                invokeAll(leftTask, rightTask);

                mergeSort.merge(array, left, mid, right);
            }
        }
    }

    public static void parallelMergeSort(int[] array) {
        MergeSortParallel mergeSort = new MergeSortParallel();
        ForkJoinPool pool = new ForkJoinPool();
        SortTask task = new SortTask(array, 0, array.length - 1, mergeSort);
        pool.invoke(task);
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

