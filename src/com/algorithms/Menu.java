package com.algorithms;

import java.util.*;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    static DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
    static ParallelDijkstraAlgorithm parallelDijkstraAlgorithm = new ParallelDijkstraAlgorithm();
    static FloydWarshall floydWarshall = new FloydWarshall();
    static QuickSortSequential quickSortSequential = new QuickSortSequential();
    static QuickSortParallel quickSortParallel = new QuickSortParallel();
    static MergeSortSequential mergeSortSequential = new MergeSortSequential();
    static MergeSortParallel mergeSortParallel = new MergeSortParallel();
    static GraphBFS graphBFS;

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    executeDijkstraAlgorithm();
                    break;
                case 2:
                    executeFloydWarshallAlgorithm();
                    break;
                case 3:
                    executeQuickSort();
                    break;
                case 4:
                    executeMergeSort();
                    break;
                case 5:
                    executeBFS();
                    break;
                case 0:
                    System.out.println("Завершення роботи програми.");
                    return;
                default:
                    System.out.println("Некоректний ввід. Спробуйте ще раз.");
            }
        }
    }

    static void displayMenu() {
        System.out.println("Виберіть дію:");
        System.out.println("1. Виконати алгоритм Дейкстри");
        System.out.println("2. Виконати алгоритм Флойда-Уоршелла");
        System.out.println("3. Виконати алгоритм QuickSort");
        System.out.println("4. Виконати алгоритм MergeSort");
        System.out.println("5. Виконати алгоритм BFS");
        System.out.println("0. Вихід");
    }

    static void executeDijkstraAlgorithm() {
        System.out.println("Введіть розмірність графа:");
        int size = scanner.nextInt();
        scanner.nextLine();
        int[][] graph = readGraph(size);
        System.out.println("Оберіть версію алгоритму Дейкстри:");
        System.out.println("1. Послідовна");
        System.out.println("2. Паралельна");
        int dijkstraChoice = scanner.nextInt();
        scanner.nextLine();
        int vertex = readVertex();
        int[] distances;
        if (dijkstraChoice == 1) {
            distances = dijkstraAlgorithm.dijkstra(graph, vertex);
        } else if (dijkstraChoice == 2) {
            distances = parallelDijkstraAlgorithm.dijkstra(graph, vertex);
        } else {
            System.out.println("Некоректний ввід. Повторіть спробу.");
            return;
        }
        printDistances(distances);
    }

    static void executeFloydWarshallAlgorithm() {
        System.out.println("Введіть розмірність графа:");
        int size = scanner.nextInt();
        scanner.nextLine();
        int[][] graph = readGraph(size);
        System.out.println("Оберіть версію алгоритму Флойда-Уоршелла:");
        System.out.println("1. Послідовна");
        System.out.println("2. Паралельна");
        int floydWarshallChoice = scanner.nextInt();
        scanner.nextLine();
        if (floydWarshallChoice == 1) {
            floydWarshall.floydWarshallSequential(graph);
        } else if (floydWarshallChoice == 2) {
            floydWarshall.floydWarshall(graph);
        } else {
            System.out.println("Некоректний ввід. Повторіть спробу.");
        }
    }

    static void executeQuickSort() {
        System.out.println("Введіть кількість елементів масиву:");
        int size = scanner.nextInt();
        scanner.nextLine();
        int[] array = readArray(size);
        System.out.println("Оберіть версію алгоритму QuickSort:");
        System.out.println("1. Послідовна");
        System.out.println("2. Паралельна");
        int quickSortChoice = scanner.nextInt();
        scanner.nextLine();
        if (quickSortChoice == 1) {
            quickSortSequential.sort(array);
        } else if (quickSortChoice == 2) {
            quickSortParallel.sort(array);
        } else {
            System.out.println("Некоректний ввід. Повторіть спробу.");
            return;
        }
        printArray(array);
    }

    static void executeMergeSort() {
        System.out.println("Введіть кількість елементів масиву:");
        int size = scanner.nextInt();
        scanner.nextLine();
        int[] array = readArray(size);
        System.out.println("Оберіть версію алгоритму MergeSort:");
        System.out.println("1. Послідовна");
        System.out.println("2. Паралельна");
        int mergeSortChoice = scanner.nextInt();
        scanner.nextLine();
        if (mergeSortChoice == 1) {
            mergeSortSequential.mergeSort(array, 0, array.length - 1);
        } else if (mergeSortChoice == 2) {
            mergeSortParallel.mergeSort(array, 0, array.length - 1);
        } else {
            System.out.println("Некоректний ввід. Повторіть спробу.");
            return;
        }
        printArray(array);
    }

    static void executeBFS() {
        System.out.println("Введіть кількість вершин графа:");
        int vertices = scanner.nextInt();
        scanner.nextLine();
        graphBFS = new GraphBFS(vertices);
        System.out.println("Введіть кількість ребер графа:");
        int edges = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < edges; i++) {
            System.out.println("Введіть ребро (початок і кінець):");
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            scanner.nextLine();
            graphBFS.addEdge(v, w);
        }
        System.out.println("Оберіть версію алгоритму BFS:");
        System.out.println("1. Послідовна");
        System.out.println("2. Паралельна");
        int bfsChoice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введіть початкову вершину:");
        int startVertex = scanner.nextInt();
        scanner.nextLine();
        if (bfsChoice == 1) {
            graphBFS.sequentialBFS(startVertex);
        } else if (bfsChoice == 2) {
            graphBFS.parallelBFS(startVertex);
        } else {
            System.out.println("Некоректний ввід. Повторіть спробу.");
        }
    }

    static int[][] readGraph(int size) {
        int[][] graph = new int[size][size];
        System.out.println("Введіть матрицю ваг графа:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }
        return graph;
    }

    static int[] readArray(int size) {
        int[] array = new int[size];
        System.out.println("Введіть елементи масиву:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    static int readVertex() {
        System.out.println("Введіть вершину графа:");
        return scanner.nextInt();
    }

    static void printDistances(int[] distances) {
        System.out.println("Відстані від заданої вершини:");
        for (int distance : distances) {
            System.out.print(distance + " ");
        }
        System.out.println();
    }

    static void printArray(int[] array) {
        System.out.println("Відсортований масив:");
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
