package com.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.algorithms.FloydWarshall;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;

/**
 * @file FloydWarshallTest.java
 *
 * @brief Тести для алгоритму Флойда-Уоршелла.
 */
public class FloydWarshallTest {

    private FloydWarshall floydWarshall;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private static final Logger logger = Logger.getLogger(FloydWarshallTest.class.getName());

    /**
     * @brief Налаштування перед кожним тестом.
     */
    @Before
    public void setUp() {
        floydWarshall = new FloydWarshall();
        System.setOut(new PrintStream(outContent));
    }

    /**
     * @brief Відновлення потоків після кожного тесту.
     */
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * @brief Тестує послідовний алгоритм Флойда-Уоршелла.
     */
    @Test
    public void testFloydWarshallSequential() {
        int[][] graph = {
            {0, 3, FloydWarshall.getINF(), 5},
            {2, 0, FloydWarshall.getINF(), 4},
            {FloydWarshall.getINF(), 1, 0, FloydWarshall.getINF()},
            {FloydWarshall.getINF(), FloydWarshall.getINF(), 2, 0}
        };
        int[][] expected = {
            {0, 3, 7, 5},
            {2, 0, 6, 4},
            {3, 1, 0, 5},
            {5, 3, 2, 0}
        };

        outContent.reset();
        floydWarshall.floydWarshallSequential(graph);
        assertArrayEquals(expected, extractResult(outContent.toString().trim()));
    }

    /**
     * @brief Тестує паралельний алгоритм Флойда-Уоршелла.
     */
    @Test
    public void testFloydWarshallParallel() {
        int[][] graph = {
            {0, 3, FloydWarshall.getINF(), 5},
            {2, 0, FloydWarshall.getINF(), 4},
            {FloydWarshall.getINF(), 1, 0, FloydWarshall.getINF()},
            {FloydWarshall.getINF(), FloydWarshall.getINF(), 2, 0}
        };
        int[][] expected = {
            {0, 3, 7, 5},
            {2, 0, 6, 4},
            {3, 1, 0, 5},
            {5, 3, 2, 0}
        };

        outContent.reset();
        floydWarshall.floydWarshall(graph);
        assertArrayEquals(expected, extractResult(outContent.toString().trim()));
    }

    /**
     * @brief Тестує випадок передачі порожнього графа для послідовного алгоритму Флойда-Уоршелла.
     */
    @Test
    public void testNullGraphSequential() {
        int[][] graph = null;
        assertThrows(NullPointerException.class, () -> floydWarshall.floydWarshallSequential(graph));
    }

    /**
     * @brief Тестує випадок передачі порожнього графа для паралельного алгоритму Флойда-Уоршелла.
     */
    @Test
    public void testNullGraphParallel() {
        int[][] graph = null;
        assertThrows(NullPointerException.class, () -> floydWarshall.floydWarshall(graph));
    }

    /**
     * @brief Тестує час виконання послідовного алгоритму Флойда-Уоршелла.
     */
    @Test
    public void testFloydWarshallSequentialTime() {
        int[][] graph = generateLargeGraph(100); // Генерація великого графа
        long startTime = System.currentTimeMillis();
        floydWarshall.floydWarshallSequential(graph);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        logger.info("Час послідовного Флойда-Уоршелла: " + duration + " мілісекунд");
    }

    /**
     * @brief Тестує час виконання паралельного алгоритму Флойда-Уоршелла.
     */
    @Test
    public void testFloydWarshallParallelTime() {
        int[][] graph = generateLargeGraph(100); // Генерація великого графа
        long startTime = System.currentTimeMillis();
        floydWarshall.floydWarshall(graph);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        logger.info("Час паралельного Флойда-Уоршелла: " + duration + " мілісекунд");
    }

    /**
     * @brief Генерує великий граф для тестування.
     *
     * @param size Розмір графа.
     * @return Згенерований граф.
     */
    private int[][] generateLargeGraph(int size) {
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

    /**
     * @brief Витягує результат з рядка вихідних даних.
     *
     * @param output Рядок вихідних даних.
     * @return Двовимірний масив результатів.
     */
    private int[][] extractResult(String output) {
        String[] lines = output.split("\n");
        int V = lines.length;
        int[][] result = new int[V][V];

        for (int i = 0; i < V; i++) {
            String[] values = lines[i].split(" ");
            for (int j = 0; j < V; j++) {
                if (values[j].equals("INF")) {
                    result[i][j] = FloydWarshall.getINF();
                } else {
                    result[i][j] = Integer.parseInt(values[j]);
                }
            }
        }

        return result;
    }
}

