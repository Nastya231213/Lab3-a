package com.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.algorithms.FloydWarshall;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @file FloydWarshallTest.java
 *
 * @brief Тести для алгоритму Флойда-Уоршелла.
 */
public class FloydWarshallTest {

    private FloydWarshall floydWarshall;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

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
