package com.algorithms;

public class FloydWarshall {
    private final static int INF = Integer.MAX_VALUE;
    private final static int THREADS = 4; 

    public static int getINF() {
        return INF;
    }

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

    private void updateDistance(int[][] dist, int i, int j, int k) {
        if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
        }
    }

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
