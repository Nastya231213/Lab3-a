package com.algorithms;

public class QuickSortParallel {

    private static class SortThread extends Thread {
        private final int[] array;
        private final int left;
        private final int right;

        public SortThread(int[] array, int left, int right) {
            this.array = array;
            this.left = left;
            this.right = right;
        }

        @Override
        public void run() {
            if (right > left) {
                int pivotIndex = partition(array, left, right);
                SortThread leftThread = new SortThread(array, left, pivotIndex - 1);
                SortThread rightThread = new SortThread(array, pivotIndex + 1, right);

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

        private int partition(int[] array, int left, int right) {
            int pivot = array[right];
            int i = left - 1;
            for (int j = left; j < right; j++) {
                if (array[j] < pivot) {
                    i++;
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            int temp = array[i + 1];
            array[i + 1] = array[right];
            array[right] = temp;
            return i + 1;
        }
    }

    public static void parallelQuickSort(int[] array) {
        SortThread thread = new SortThread(array, 0, array.length - 1);
        thread.start();
    }

}

