
package com.tests;

import java.util.logging.Logger;
import java.util.logging.Level;
import org.junit.jupiter.api.Test;
import com.algorithms.DijkstraAlgorithm;
import com.algorithms.ParallelDijkstraAlgorithm;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
/**
 * @file DijkstraAlgorithmTest.java
 *
 * @brief Тести для алгоритмів Дейкстри (послідовного та паралельного).
 */
public class DijkstraAlgorithmTest {
    private static final Logger logger = Logger.getLogger(DijkstraAlgorithmTest.class.getName());

	/**
	 * @class DijkstraAlgorithmTest
	 * 
	 * @brief Тестовий клас для перевірки роботи алгоритмів Дейкстри.
	 */

	/**
	 * @brief Тестує послідовний алгоритм Дейкстри на графі з заданою матрицею
	 *        суміжності.
	 *
	 *        Перевіряє, чи відповідають результати очікуваним значенням.
	 */
	@Test
	public void testDijkstraAlgorithm() {
		int[][] graph = { { 0, 10, 20, 0, 0, 0 }, { 10, 0, 0, 50, 10, 0 }, { 20, 0, 0, 20, 33, 0 },
				{ 0, 50, 20, 0, 20, 2 }, { 0, 10, 33, 20, 0, 1 }, { 0, 0, 0, 2, 1, 0 } };

		DijkstraAlgorithm da = new DijkstraAlgorithm();
		int[] dist = da.dijkstra(graph, 0);
		int[] expected = { 0, 10, 20, 23, 20, 21 };

		// Print expected and actual arrays for debugging
		System.out.println("Expected: " + java.util.Arrays.toString(expected));
		System.out.println("Actual: " + java.util.Arrays.toString(dist));

		// Compare each element individually for detailed output
		for (int i = 0; i < expected.length; i++) {
			if (expected[i] != dist[i]) {
				System.out.println("Difference at index " + i + ": expected " + expected[i] + ", but got " + dist[i]);
			}
		}

		assertArrayEquals(expected, dist);
	}

	/**
	 * @brief Тестує паралельний алгоритм Дейкстри на графі з заданою матрицею
	 *        суміжності.
	 *
	 *        Перевіряє, чи відповідають результати очікуваним значенням.
	 */
	@Test
	public void testParallelDijkstraAlgorithm() {
		int[][] graph = { { 0, 10, 20, 0, 0, 0 }, { 10, 0, 0, 50, 10, 0 }, { 20, 0, 0, 20, 33, 0 },
				{ 0, 50, 20, 0, 20, 2 }, { 0, 10, 33, 20, 0, 1 }, { 0, 0, 0, 2, 1, 0 } };

		ParallelDijkstraAlgorithm pda = new ParallelDijkstraAlgorithm();
		int[] dist = pda.dijkstra(graph, 0);
		int[] expected = { 0, 10, 20, 23, 20, 21 };
		assertArrayEquals(expected, dist);
	}

	/**
	 * @brief Тестує алгоритм Дейкстри на графі з одним вузлом.
	 *
	 *        Перевіряє, чи відповідають результати очікуваним значенням.
	 */
	@Test
	public void testSingleNodeGraph() {
		int[][] graph = { { 0 } };

		DijkstraAlgorithm da = new DijkstraAlgorithm();
		int[] dist = da.dijkstra(graph, 0);
		int[] expected = { 0 };

		assertArrayEquals(expected, dist);
	}

	/**
	 * @brief Тестує алгоритм Дейкстри на графі з негативними вагами.
	 *
	 *        Перевіряє, чи відповідають результати очікуваним значенням.
	 */
	@Test
	public void testNegativeWeights() {
		int[][] graph = { { 0, 2, 4, 0 }, { 2, 0, -1, 3 }, { 4, -1, 0, 1 }, { 0, 3, 1, 0 } };

		DijkstraAlgorithm da = new DijkstraAlgorithm();
		int[] dist = da.dijkstra(graph, 0);
		int[] expected = { 0, 2, 1, 2 };

		assertArrayEquals(expected, dist);
	}

	/**
	 * @brief Тестує алгоритм Дейкстри на роз'єднаному графі.
	 *
	 *        Перевіряє, чи відповідають результати очікуваним значенням.
	 */
	@Test
	public void testDisconnectedGraph() {
		int[][] graph = { { 0, 1, 0, 0 }, { 1, 0, 0, 0 }, { 0, 0, 0, 1 }, { 0, 0, 1, 0 } };

		DijkstraAlgorithm da = new DijkstraAlgorithm();
		int[] dist = da.dijkstra(graph, 0);
		int[] expected = { 0, 1, Integer.MAX_VALUE, Integer.MAX_VALUE };

		assertArrayEquals(expected, dist);
	}

	/**
	 * @brief Тестує алгоритм Дейкстри на порожньому графі.
	 *
	 *        Перевіряє, чи викликає метод обробку винятку
	 *        ArrayIndexOutOfBoundsException.
	 */
	@Test
	public void testEmptyGraph() {
		int[][] graph = new int[0][0];

		DijkstraAlgorithm da = new DijkstraAlgorithm();
		try {
			int[] dist = da.dijkstra(graph, 0);
		} catch (ArrayIndexOutOfBoundsException e) {
			// Exception expected
		}
	}

	@Test
	public void testDijkstraAlgorithmTime() {
	    int[][] graph = generateGraph(100);
	    DijkstraAlgorithm da = new DijkstraAlgorithm();

	    long startTime = System.currentTimeMillis();
	    da.dijkstra(graph, 0);
	    long endTime = System.currentTimeMillis();

	    long duration = endTime - startTime;
	    logger.info("Час послідовного алгоритму Дейкстри: " + duration + " мілісекунд");
	}

	@Test
	public void testParallelDijkstraAlgorithmTime() {
	    int[][] graph = generateGraph(100);
	    ParallelDijkstraAlgorithm pda = new ParallelDijkstraAlgorithm();

	    long startTime = System.currentTimeMillis();
	    pda.dijkstra(graph, 0);
	    long endTime = System.currentTimeMillis();

	    long duration = endTime - startTime;
	    logger.info("Час паралельного алгоритму Дейкстри: " + duration + " мілісекунд");
	}

	private int[][] generateGraph(int size) {
	    int[][] graph = new int[size][size];
	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            if (i == j) {
	                graph[i][j] = 0;
	            } else {
	                graph[i][j] = (int) (Math.random() * 100) + 1;
	            }
	        }
	    }
	    return graph;
	}
}
