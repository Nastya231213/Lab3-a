package com.algorithms;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

public class GraphBFS {
    private int V;
    private LinkedList<Integer> adj[];

    public GraphBFS(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }
    // Послідовний алгоритм BFS
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

    // Паралельний алгоритм BFS
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


