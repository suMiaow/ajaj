package com.meme.algs;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");

        v1.edges = List.of(
                new Edge(v3, 9),
                new Edge(v2, 7),
                new Edge(v6, 14)
        );
        v2.edges = List.of(
                new Edge(v4, 15)
        );
        v3.edges = List.of(
                new Edge(v4, 11),
                new Edge(v6, 2)
        );
        v4.edges = List.of(
                new Edge(v5, 6)
        );
        v5.edges = List.of();
        v6.edges = List.of(
                new Edge(v5, 9)
        );

        List<Vertex> graph = List.of(v1, v2, v3, v4, v5, v6);

        dijkstra(graph, v1);

        for (Vertex vertex : graph) {
            System.out.println(vertex + " " + vertex.distance + " from: " + vertex.prev);
        }

    }

    private static void dijkstra(List<Vertex> graph, Vertex source) {
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
                int newDistance = curr.distance + edge.weight;
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
