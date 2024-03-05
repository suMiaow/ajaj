package com.meme.algs;

import java.util.List;

public class BellmanFord {

    public static void main(String[] args) {

        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(new Edge(v2, 2), new Edge(v3, 1));
        v2.edges = List.of(new Edge(v3, -2));
        v3.edges = List.of(new Edge(v4, 1));
        v4.edges = List.of();
        List<Vertex> graph = List.of(v1, v2, v3, v4);

        bellmanFord(graph, v1);

        for (Vertex vertex : graph) {
            System.out.println(vertex + " " + vertex.distance + " from: " + vertex.prev);
        }
    }

    private static void bellmanFord(List<Vertex> graph, Vertex source) {
        source.distance = 0;
        for (int i = 0; i < graph.size(); i++) {
            for (Vertex vertex : graph) {
                if (vertex.distance != Vertex.INF) {
                    for (Edge edge : vertex.edges) {
                        Vertex neighbour = edge.linked;
                        int dist = vertex.distance + edge.weight;
                        if (dist < neighbour.distance) {
                            if (i == graph.size() -1) {
                                throw new RuntimeException("minus ring");
                            }
                            neighbour.distance = dist;
                            neighbour.prev = vertex;
                        }
                    }
                }
            }
        }
    }
}
