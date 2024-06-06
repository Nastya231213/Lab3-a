/**
 * @file AbstractDijkstraAlgorithm.java
 * @brief Абстрактний клас, що визначає структуру реалізацій алгоритму Дейкстри.
 */

package com.algorithms;

/**
 * @class AbstractDijkstraAlgorithm
 * @brief Абстрактний клас для алгоритму Дейкстри.
 *
 * Цей клас надає шаблон для реалізацій алгоритму Дейкстри,
 * включаючи метод для знаходження вершини з мінімальною відстанню.
 */
public abstract class AbstractDijkstraAlgorithm {

    /**
     * @brief Абстрактний метод, який мають реалізувати підкласи для виконання алгоритму Дейкстри.
     *
     * @param graph Матриця суміжності, що представляє граф.
     * @param source Вершина джерела.
     * @return Масив найкоротших відстаней від джерела до кожної вершини.
     */
    public abstract int[] dijkstra(int[][] graph, int source);

    /**
     * @brief Знаходить вершину з мінімальною відстанню зі множини непроцесованих вершин.
     *
     * @param dist Масив найкоротших відстаней від джерела.
     * @param visited Булевий масив, який вказує, чи була вершина оброблена.
     * @return Індекс вершини з мінімальною відстанню.
     */
    protected int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
}