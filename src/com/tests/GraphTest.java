package com.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.algorithms.GraphBFS;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GraphTest {

    private GraphBFS graph;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

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

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testSequentialBFS() {
        outContent.reset(); 
        graph.sequentialBFS(0);
        assertEquals("0 1 2 3 4 5", outContent.toString().trim());
    }

    @Test
    public void testParallelBFS() {
        outContent.reset(); 
        graph.parallelBFS(0);
        assertEquals("0 1 2 3 4 5", outContent.toString().trim());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testInvalidStartNodeSequentialBFS() {
        outContent.reset();
        graph.sequentialBFS(6); 
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testInvalidStartNodeParallelBFS() {
        outContent.reset();
        graph.parallelBFS(6); 
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testInvalidStartNodeNegativeSequentialBFS() {
        outContent.reset();
        graph.sequentialBFS(-1); 
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testInvalidStartNodeNegativeParallelBFS() {
        outContent.reset();
        graph.parallelBFS(-1);
    }

    @Test(expected = NullPointerException.class)
    public void testNullGraphSequentialBFS() {
        outContent.reset();
        GraphBFS nullGraph = new GraphBFS(0);
        nullGraph.sequentialBFS(0);
    }

    @Test(expected = NullPointerException.class)
    public void testNullGraphParallelBFS() {
        outContent.reset();
        GraphBFS nullGraph = new GraphBFS(0); 
        nullGraph.parallelBFS(0);
    }
}
