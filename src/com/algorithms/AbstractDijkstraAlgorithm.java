package com.algorithms;

public abstract class AbstractDijkstraAlgorithm {
    public abstract int[] dijkstra(int[][] graph, int source);

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