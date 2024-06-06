/**
 * @file FloydWarshall.java
 * @brief Клас, що реалізує алгоритм Флойда-Уоршелла як у послідовному, так і в паралельному виконанні.
 */

package com.algorithms;

/**
 * @class FloydWarshall
 * @brief Реалізація алгоритму Флойда-Уоршелла для знаходження найкоротших шляхів у графі.
 *
 * Цей клас надає методи для послідовного та паралельного виконання алгоритму Флойда-Уоршелла.
 */
public class FloydWarshall {
    private final static int INF = Integer.MAX_VALUE;
    private final static int THREADS = 4; 

    /**
     * @brief Повертає значення, що використовується для позначення нескінченності (відсутності шляху).
     * @return Значення нескінченності (INF).
     */
    public static int getINF() {
        return INF;
    }

    /**
     * @brief Виконує алгоритм Флойда-Уоршелла в паралельному режимі.
     * 
     * @param graph Матриця суміжності графа.
     */
    public void floydWarshall(int[][] graph) {
        int V = graph.length;
        int[][] dist = initializeDistances(graph);

        for (int k = 0; k < V; k++) {
            Thread[] threads = new Thread[THREADS];
            final int kk = k; 
            for (int t = 0; t < THREADS; t++) {
                final int threadId = t;
                threads[t] = new Thread(() -> {
                    for (int i = threadId; i < V; i += THREADS) {
                        for (int j = 0; j < V; j++) {
                            updateDistance(dist, i, j, kk);
                        }
                    }
                });
                threads[t].start();
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        printSolution(dist);
    }

    /**
     * @brief Виконує алгоритм Флойда-Уоршелла в послідовному режимі.
     * 
     * @param graph Матриця суміжності графа.
     */
    public void floydWarshallSequential(int[][] graph) {
        int V = graph.length;
        int[][] dist = initializeDistances(graph);

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    updateDistance(dist, i, j, k);
                }
            }
        }

        printSolution(dist);
    }

    /**
     * @brief Ініціалізує матрицю відстаней на основі вхідного графа.
     * 
     * @param graph Матриця суміжності графа.
     * @return Ініціалізована матриця відстаней.
     */
    private int[][] initializeDistances(int[][] graph) {
        int V = graph.length;
        int[][] dist = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }
        return dist;
    }

    /**
     * @brief Оновлює відстань між вершинами, якщо знайдено коротший шлях через проміжну вершину.
     * 
     * @param dist Матриця відстаней.
     * @param i Індекс початкової вершини.
     * @param j Індекс кінцевої вершини.
     * @param k Індекс проміжної вершини.
     */
    private void updateDistance(int[][] dist, int i, int j, int k) {
        if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
        }
    }

    /**
     * @brief Друкує рішення, тобто матрицю найкоротших відстаней між вершинами.
     * 
     * @param dist Матриця відстаней.
     */
    private void printSolution(int[][] dist) {
        int V = dist.length;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}