package com.algorithms;

public class ParallelStrassen {

    private static final int THRESHOLD = 64;

    public static int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        Thread[][] threads = new Thread[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                threads[i][j] = new Thread(new StrassenTask(A, B, result, i, j, n));
                threads[i][j].start();
            }
        }

        try {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    threads[i][j].join();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static class StrassenTask implements Runnable {
        private final int[][] A;
        private final int[][] B;
        private final int[][] result;
        private final int row;
        private final int col;
        private final int size;

        StrassenTask(int[][] A, int[][] B, int[][] result, int row, int col, int size) {
            this.A = A;
            this.B = B;
            this.result = result;
            this.row = row;
            this.col = col;
            this.size = size;
        }

        @Override
        public void run() {
            if (size <= THRESHOLD) {
                for (int k = 0; k < size; k++) {
                    result[row][col] += A[row][k] * B[k][col];
                }
            } else {
                int newSize = size / 2;
                int[][] A11 = new int[newSize][newSize];
                int[][] A12 = new int[newSize][newSize];
                int[][] A21 = new int[newSize][newSize];
                int[][] A22 = new int[newSize][newSize];

                int[][] B11 = new int[newSize][newSize];
                int[][] B12 = new int[newSize][newSize];
                int[][] B21 = new int[newSize][newSize];
                int[][] B22 = new int[newSize][newSize];

                splitMatrix(A, A11, row, col);
                splitMatrix(A, A12, row, col + newSize);
                splitMatrix(A, A21, row + newSize, col);
                splitMatrix(A, A22, row + newSize, col + newSize);

                splitMatrix(B, B11, row, col);
                splitMatrix(B, B12, row, col + newSize);
                splitMatrix(B, B21, row + newSize, col);
                splitMatrix(B, B22, row + newSize, col + newSize);

                int[][] M1 = multiply(add(A11, A22), add(B11, B22));
                int[][] M2 = multiply(add(A21, A22), B11);
                int[][] M3 = multiply(A11, subtract(B12, B22));
                int[][] M4 = multiply(A22, subtract(B21, B11));
                int[][] M5 = multiply(add(A11, A12), B22);
                int[][] M6 = multiply(subtract(A21, A11), add(B11, B12));
                int[][] M7 = multiply(subtract(A12, A22), add(B21, B22));

                int[][] C11 = add(subtract(add(M1, M4), M5), M7);
                int[][] C12 = add(M3, M5);
                int[][] C21 = add(M2, M4);
                int[][] C22 = add(subtract(add(M1, M3), M2), M6);

                joinMatrix(C11, result, row, col);
                joinMatrix(C12, result, row, col + newSize);
                joinMatrix(C21, result, row + newSize, col);
                joinMatrix(C22, result, row + newSize, col + newSize);
            }
        }
    }

    private static void splitMatrix(int[][] parent, int[][] child, int startRow, int startCol) {
        for (int i1 = 0, i2 = startRow; i1 < child.length; i1++, i2++) {
            for (int j1 = 0, j2 = startCol; j1 < child.length; j1++, j2++) {
                child[i1][j1] = parent[i2][j2];
            }
        }
    }

    private static void joinMatrix(int[][] child, int[][] parent, int startRow, int startCol) {
        for (int i1 = 0, i2 = startRow; i1 < child.length; i1++, i2++) {
            for (int j1 = 0, j2 = startCol; j1 < child.length; j1++, j2++) {
                parent[i2][j2] = child[i1][j1];
            }
        }
    }

    private static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    private static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }

  
}