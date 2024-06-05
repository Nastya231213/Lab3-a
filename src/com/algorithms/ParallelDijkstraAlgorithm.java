package com.algorithms;

import java.util.*;

public class ParallelDijkstraAlgorithm extends AbstractDijkstraAlgorithm {

	   @Override
	    public int[] dijkstra(int[][] graph, int source) {
	        int V = graph.length;
	        int[] dist = new int[V];
	        boolean[] visited = new boolean[V];
	        Arrays.fill(dist, Integer.MAX_VALUE);
	        dist[source] = 0;

	        for (int count = 0; count < V - 1; count++) {
	            int u = minDistance(dist, visited);
	            visited[u] = true;

	            List<Thread> threads = new ArrayList<>();
	            for (int v = 0; v < V; v++) {
	                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE) {
	                    int finalU = u;
	                    int finalV = v;
	                    Thread thread = new Thread(() -> {
	                        synchronized (dist) {
	                            if (dist[finalU] + graph[finalU][finalV] < dist[finalV]) {
	                                dist[finalV] = dist[finalU] + graph[finalU][finalV];
	                            }
	                        }
	                    });
	                    threads.add(thread);
	                    thread.start();
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

	        return dist;
	    }


}
