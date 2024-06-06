package com.algorithms;

import java.util.*;

/**
 * @file DijkstraAlgorithm.java
 * @brief Реалізація алгоритму Дейкстри для пошуку найкоротших шляхів у графі.
 */

/**
 * @class DijkstraAlgorithm
 * @brief Реалізація алгоритму Дейкстри для пошуку найкоротших шляхів у графі.
 *
 * Цей клас розширює AbstractDijkstraAlgorithm і надає реалізацію
 * алгоритму Дейкстри для пошуку найкоротших шляхів від заданої
 * вершини джерела до всіх інших вершин у графі.
 */
public class DijkstraAlgorithm extends AbstractDijkstraAlgorithm {

    /**
     * @brief Застосовує алгоритм Дейкстри для пошуку найкоротших шляхів від заданої вершини до всіх інших вершин.
     *
     * @param graph Матриця суміжності, що представляє граф.
     * @param source Вершина джерела, з якої потрібно знайти найкоротші шляхи.
     * @return Масив, що містить найкоротші відстані від вершини джерела до всіх інших вершин.
     */
    public int[] dijkstra(int[][] graph, int source) {
        int V = graph.length;
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        return dist;
    }
}


