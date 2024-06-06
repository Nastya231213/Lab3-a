package com.algorithms;

import java.util.concurrent.RecursiveTask;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

/**
 * @file ParallelDijkstraAlgorithm.java
 * 
 * @brief Реалізація алгоритму Дейкстри з використанням паралельних потоків.
 */

/**
 * @class ParallelDijkstraAlgorithm
 * 
 * @brief Клас, який реалізує алгоритм Дейкстри з використанням паралельних потоків.
 * 
 * Цей клас розширює AbstractDijkstraAlgorithm і перевизначає метод dijkstra для забезпечення паралельного виконання.
 */
public class ParallelDijkstraAlgorithm extends AbstractDijkstraAlgorithm {

    private static final int THRESHOLD = 100;

    /**
     * @brief Знаходить найкоротші шляхи від заданого вихідного вузла до всіх інших вузлів у графі за допомогою алгоритму Дейкстри.
     *
     * @param graph Матриця суміжності, що представляє граф.
     * @param source Вихідний вузол, від якого треба знайти найкоротші шляхи.
     * @return Масив, що містить найкоротші відстані від вихідного вузла до всіх інших вузлів.
     */
    @Override
    public int[] dijkstra(int[][] graph, int source) {
        int V = graph.length;
        int[] dist = new int[V]; 
        boolean[] visited = new boolean[V]; 
        ForkJoinPool pool = new ForkJoinPool();
        Arrays.fill(dist, Integer.MAX_VALUE); 
        dist[source] = 0; 

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, visited); 

            visited[u] = true;

            pool.invoke(new DijkstraTask(graph, dist, visited, u));
        }

        pool.shutdown();
        return dist; 
    }

    private class DijkstraTask extends RecursiveTask<Void> {
        private final int[][] graph;
        private final int[] dist;
        private final boolean[] visited;
        private final int u;

        public DijkstraTask(int[][] graph, int[] dist, boolean[] visited, int u) {
            this.graph = graph;
            this.dist = dist;
            this.visited = visited;
            this.u = u;
        }

        @Override
        protected Void compute() {
            if (u < THRESHOLD) {
                for (int v = 0; v < graph.length; v++) {
                    if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            } else {
                DijkstraTask[] subTasks = new DijkstraTask[graph.length];
                for (int v = 0; v < graph.length; v++) {
                    if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                        subTasks[v] = new DijkstraTask(graph, dist, visited, v);
                        subTasks[v].fork();
                    }
                }
                for (DijkstraTask task : subTasks) {
                    if (task != null) {
                        task.join();
                    }
                }
            }
            return null;
        }
    }
}


