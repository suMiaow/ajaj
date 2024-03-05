package com.meme.algs;

import java.util.ArrayList;
import java.util.List;

public class Prim {

    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");
        Vertex v7 = new Vertex("v7");

        v1.edges = List.of(
                new Edge(v2, 2),
                new Edge(v4, 1),
                new Edge(v3, 4)
        );

        v2.edges = List.of(
                new Edge(v1, 2),
                new Edge(v4, 3),
                new Edge(v5, 10)
        );

        v3.edges = List.of(
                new Edge(v1, 4),
                new Edge(v4, 2),
                new Edge(v6, 5)
        );

        v4.edges = List.of(
                new Edge(v1, 1),
                new Edge(v2, 3),
                new Edge(v5, 7),
                new Edge(v7, 4),
                new Edge(v6, 8),
                new Edge(v3, 2)
        );

        v5.edges = List.of(
                new Edge(v2, 10),
                new Edge(v4, 7),
                new Edge(v7, 6)
        );

        v6.edges = List.of(
                new Edge(v3, 5),
                new Edge(v4, 8),
                new Edge(v7, 1)
        );

        v7.edges = List.of(
                new Edge(v6, 1),
                new Edge(v4, 4),
                new Edge(v5, 6)
        );

        List<Vertex> graph = List.of(v1, v2, v3, v4, v5, v6, v7);

        prim(graph, v1);

        for (Vertex vertex : graph) {
            System.out.println(vertex + " <- " + vertex.prev);
        }

    }

    public static void prim(List<Vertex> graph, Vertex source) {
        ArrayList<Vertex> list = new ArrayList<>(graph);
        source.distance = 0;

        while (!list.isEmpty()) {
            Vertex curr = chooseMinDistVertex(list);
            updateNeighboursDist(curr);
            list.remove(curr);
            curr.visited = true;
        }
    }

    private static void updateNeighboursDist(Vertex curr) {
        for (Edge edge : curr.edges) {
            Vertex neighbour = edge.linked;
            if (!neighbour.visited) {
                int newDistance = edge.weight;
                if (newDistance < neighbour.distance) {
                    neighbour.distance = newDistance;
                    neighbour.prev = curr;
                }
            }
        }
    }

    private static Vertex chooseMinDistVertex(ArrayList<Vertex> list) {
        Vertex min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            Vertex vertex = list.get(i);
            if (vertex.distance < min.distance) {
                min = vertex;
            }
        }
        return min;
    }


}
