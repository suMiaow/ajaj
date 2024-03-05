package com.meme.algs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FloydWarshall {

    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(new Edge(v3, -2));
        v2.edges = List.of(new Edge(v1, 4), new Edge(v3, 3));
        v3.edges = List.of(new Edge(v4, 2));
        v4.edges = List.of(new Edge(v2, -1));

        List<Vertex> graph = List.of(v1, v2, v3, v4);

        floydWarshall(graph);
    }

    private static void floydWarshall(List<Vertex> graph) {
        int size = graph.size();
        int[][] dists = new int[size][size];
        Vertex[][] prevs = new Vertex[size][size];
        for (int i = 0; i < size; i++) {
            Vertex v = graph.get(i);
            Map<Vertex, Integer> edgeTable = v.edges.stream().collect(Collectors.toMap(edge -> edge.linked, edge -> edge.weight));
            for (int j = 0; j < size; j++) {
                Vertex u = graph.get(j);
                if (v == u) {
                    dists[i][j] = 0;
                } else {
                    Integer dist = edgeTable.get(u);
                    if (dist != null) {
                        dists[i][j] = dist;
                        prevs[i][j] = v;
                    } else {
                        dists[i][j] = Integer.MAX_VALUE;
                    }
                }
            }
        }
        print(dists);
        print(prevs);
        for (int i = 0; i < dists.length; i++) {
            // turn
            for (int j = 0; j < dists.length; j++) {
                // row
                if (j != i) {
                    int dist1 = dists[j][i];
                    if (dist1 != Integer.MAX_VALUE) {
                        for (int k = 0; k < dists.length; k++) {
                            // column
                            int dist2 = dists[i][k];
                            if (dist2 != Integer.MAX_VALUE) {
                                int dist = dist1 + dist2;
                                if (dist < dists[j][k]) {
                                    if (j == k) {
                                        throw new RuntimeException("found minus ring");
                                    }
                                    dists[j][k] = dist;
                                    prevs[j][k] = prevs[i][k];
                                }
                            }
                        }
                    }
                }
            }
        }
        print(dists);
        print(prevs);

        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.size(); j++) {
                path(prevs, graph, i, j);
            }
        }
    }

    private static void print(int[][] dist) {
        System.out.println("------------------------");
        for (int[] row : dist) {
            System.out.println(
                    Arrays.stream(row).boxed()
                            .map(x -> x == Integer.MAX_VALUE ? "∞" : String.valueOf(x))
                            .map(s -> String.format("%2s", s))
                            .collect(Collectors.joining(", ", "[", "]"))
            );
        }
    }

    private static void print(Vertex[][] dist) {
        System.out.println("------------------------");
        for (Vertex[] row : dist) {
            System.out.println(
                    Arrays.stream(row)
                            .map(v -> v == null ? "null" : v.name)
                            .map(v -> String.format("%4s", v))
                            .collect(Collectors.joining(", ", "[", "]"))
            );
        }
    }

    private static void path(Vertex[][] prevs, List<Vertex> graph, int i, int j) {
        LinkedList<Vertex> stack = new LinkedList<>();
        System.out.print(graph.get(i).name + " -> " + graph.get(j).name + " ");
        stack.push(graph.get(j));

        while (i != j) {
            Vertex prev = prevs[i][j];
            stack.push(prev);
            j = graph.indexOf(prev);
        }
        System.out.println(stack);
    }
}
