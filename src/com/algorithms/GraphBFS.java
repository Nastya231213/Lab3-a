package com.algorithms;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

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
 * 
 * Він наслідує клас Thread.
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
     * @brief Паралельний алгоритм пошуку в ширину (BFS).
     *
     * Виводить вершини графа в порядку їх відвідування, використовуючи багатопоточність.
     *
     * @param s Початкова вершина для пошуку.
     */
    public void parallelBFS(int s) {
        final boolean visited[] = new boolean[V];
        final Queue<Integer> queue = new LinkedList<>();
        List<Thread> threads = new ArrayList<>();

        synchronized (queue) {
            visited[s] = true;
            queue.add(s);
        }

        while (true) {
            Integer currentNode;
            synchronized (queue) {
                currentNode = queue.poll();
            }

            if (currentNode == null) {
                break;
            }

            final int current = currentNode;
            synchronized (System.out) {
                System.out.print(current + " ");
            }

            for (int n : adj[current]) {
                synchronized (visited) {
                    if (!visited[n]) {
                        visited[n] = true;
                        synchronized (queue) {
                            queue.add(n);
                        }
                        final int node = n;
                        Thread thread = new Thread(() -> parallelVisitNode(node, visited, queue));
                        thread.start();
                        threads.add(thread);
                    }
                }
            }
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @brief Метод, що виконує паралельне відвідування вершини.
     *
     * @param n Вершина, яку потрібно відвідати.
     * @param visited Масив відвіданих вершин.
     * @param queue Черга для BFS.
     */
    private void parallelVisitNode(int n, boolean[] visited, Queue<Integer> queue) {
        for (int neighbor : adj[n]) {
            synchronized (visited) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    synchronized (queue) {
                        queue.add(neighbor);
                    }
                }
            }
        }
    }
}



