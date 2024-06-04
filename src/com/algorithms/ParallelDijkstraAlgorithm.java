package com.algorithms;

import java.util.*;

public class ParallelDijkstraAlgorithm {

    public static int[] dijkstra(int[][] graph, int source) throws InterruptedException {
        int V = graph.length;
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        List<Thread> threads = new ArrayList<>();

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE) {
                    int finalU = u;
                    int finalV = v;
                    Thread thread = new Thread(() -> {
                        if (dist[finalU] + graph[finalU][finalV] < dist[finalV]) {
                            dist[finalV] = dist[finalU] + graph[finalU][finalV];
                        }
                    });
                    threads.add(thread);
                    thread.start();
                }
            }

            for (Thread thread : threads) {
                thread.join();
            }
            threads.clear();
        }

        return dist;
    }

    private static int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) throws InterruptedException {
        int[][] graph = { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                          { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                          { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                          { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                          { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                          { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                          { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                          { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                          { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
        int source = 0;
        int[] result = dijkstra(graph, source);
        System.out.println("Shortest distances from vertex " + source + ": " + Arrays.toString(result));
    }
}
