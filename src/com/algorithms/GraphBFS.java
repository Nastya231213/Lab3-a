package com.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @file GraphBFS.java
 *
 * @brief Клас, який реалізує алгоритми послідовного та паралельного пошуку в ширину (BFS) для графів.
 *
 * Клас містить реалізацію як послідовного, так і паралельного алгоритму BFS.
 */
/**
 * @class GraphBFS
 * 
 * @brief Клас, який реалізує алгоритми послідовного та паралельного пошуку в ширину (BFS) для графів.
 */
public class GraphBFS {
    private int V;  
    private LinkedList<Integer> adj[];  

    /**
     * @brief Конструктор класу GraphBFS.
     *
     * Ініціалізує граф з заданою кількістю вершин.
     *
     * @param v Кількість вершин у графі.
     */
    public GraphBFS(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<>();
    }

    /**
     * @brief Додає ребро в граф.
     *
     * @param v Вершина, з якої виходить ребро.
     * @param w Вершина, в яку входить ребро.
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    /**
     * @brief Послідовний алгоритм пошуку в ширину (BFS).
     *
     * Виводить вершини графа в порядку їх відвідування.
     *
     * @param s Початкова вершина для пошуку.
     */
    public void sequentialBFS(int s) {
        boolean visited[] = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        visited[s] = true;
        queue.add(s);

        while (!queue.isEmpty()) {
            s = queue.poll();
            System.out.print(s + " ");

            for (int n : adj[s]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    /**
     * @brief Паралельний алгоритм пошуку в ширину (BFS) за допомогою ForkJoinPool.
     *
     * Виводить вершини графа в порядку їх відвідування, використовуючи багатопоточність.
     *
     * @param s Початкова вершина для пошуку.
     */
    public void parallelBFS(int s) {
        final boolean visited[] = new boolean[V];
        final Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        ForkJoinPool pool = new ForkJoinPool();

        visited[s] = true;
        queue.add(s);

        pool.invoke(new BFSAction(s, visited, queue));

        pool.shutdown();
    }

    /**
     * @class BFSAction
     * @brief Клас, що реалізує задачу для ForkJoinPool для паралельного виконання BFS.
     */
    private class BFSAction extends RecursiveAction {
        private final int node;
        private final boolean[] visited;
        private final Queue<Integer> queue;

        BFSAction(int node, boolean[] visited, Queue<Integer> queue) {
            this.node = node;
            this.visited = visited;
            this.queue = queue;
        }

        @Override
        protected void compute() {
            List<BFSAction> tasks = new ArrayList<>();
            while (true) {
                Integer currentNode;
                synchronized (queue) {
                    currentNode = queue.poll();
                }

                if (currentNode == null) {
                    break;
                }

                System.out.print(currentNode + " ");

                for (int neighbor : adj[currentNode]) {
                    synchronized (visited) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.add(neighbor);
                            tasks.add(new BFSAction(neighbor, visited, queue));
                        }
                    }
                }
            }
            invokeAll(tasks);
        }
    }
}


