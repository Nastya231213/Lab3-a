package com.algorithms;


public class QuickSortParallel extends AbstractQuickSort {

    private static class SortThread extends Thread {
        private final int[] array;
        private final int left;
        private final int right;
        private final AbstractQuickSort sorter;

        public SortThread(int[] array, int left, int right, AbstractQuickSort sorter) {
            this.array = array;
            this.left = left;
            this.right = right;
            this.sorter = sorter;
        }

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


