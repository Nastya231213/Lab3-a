package com.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.algorithms.GraphBFS;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @file GraphTest.java
 *
 * @brief Тести для алгоритму BFS в графі.
 */
public class GraphTest {

    private GraphBFS graph;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * @brief Підготовка тестового середовища.
     */
    @Before
    public void setUp() {
        graph = new GraphBFS(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        System.setOut(new PrintStream(outContent));
    }

    /**
     * @brief Відновлення оригінального потоку виведення.
     */
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * @brief Тестування послідовного BFS.
     */
    @Test
    public void testSequentialBFS() {
        outContent.reset(); 
        graph.sequentialBFS(0);
        assertEquals("0 1 2 3 4 5", outContent.toString().trim());
    }

    /**
     * @brief Тестування паралельного BFS.
     */
    @Test
    public void testParallelBFS() {
        outContent.reset(); 
        graph.parallelBFS(0);
        assertEquals("0 1 2 3 4 5", outContent.toString().trim());
    }

    /**
     * @brief Тестування послідовного BFS з недійсним стартовим вузлом.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testInvalidStartNodeSequentialBFS() {
        outContent.reset();
        graph.sequentialBFS(6); 
    }

    /**
     * @brief Тестування паралельного BFS з недійсним стартовим вузлом.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testInvalidStartNodeParallelBFS() {
        outContent.reset();
        graph.parallelBFS(6); 
    }

    /**
     * @brief Тестування послідовного BFS з від'ємним стартовим вузлом.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testInvalidStartNodeNegativeSequentialBFS() {
        outContent.reset();
        graph.sequentialBFS(-1); 
    }

    /**
     * @brief Тестування паралельного BFS з від'ємним стартовим вузлом.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testInvalidStartNodeNegativeParallelBFS() {
        outContent.reset();
        graph.parallelBFS(-1);
    }

    /**
     * @brief Тестування послідовного BFS з нульовим графом.
     */
    @Test(expected = NullPointerException.class)
    public void testNullGraphSequentialBFS() {
        outContent.reset();
        GraphBFS nullGraph = new GraphBFS(0);
        nullGraph.sequentialBFS(0);
    }

    /**
     * @brief Тестування паралельного BFS з нульовим графом.
     */
    @Test(expected = NullPointerException.class)
    public void testNullGraphParallelBFS() {
        outContent.reset();
        GraphBFS nullGraph = new GraphBFS(0); 
        nullGraph.parallelBFS(0);
    }
}