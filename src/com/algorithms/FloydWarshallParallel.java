package com.algorithms;

public class FloydWarshallParallel {
    final static int INF = 99999;
    final static int THREADS = 4; 

    void floydWarshall(int[][] graph) {
        int V = graph.length;
        int[][] dist = new int[V][V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        for (int k = 0; k < V; k++) {
            Thread[] threads = new Thread[THREADS];
            final int kk = k; 
            for (int t = 0; t < THREADS; t++) {
                final int threadId = t;
                threads[t] = new Thread(() -> {
                    for (int i = threadId; i < V; i += THREADS) {
                        for (int j = 0; j < V; j++) {
                            if (dist[i][kk] != INF && dist[kk][j] != INF && dist[i][kk] + dist[kk][j] < dist[i][j]) {
                                dist[i][j] = dist[i][kk] + dist[kk][j];
                            }
                        }
                    }
                });
                threads[t].start();
            }

            for (int t = 0; t < THREADS; t++) {
                try {
                    threads[t].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        printSolution(dist);
    }

    void printSolution(int[][] dist) {
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
