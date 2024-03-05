package com.meme.algs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraPriorityQueue {
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
        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.distance));
        for (Vertex vertex : graph) {
            queue.offer(vertex);
        }
        source.distance = 0;

        while (!queue.isEmpty()) {
            Vertex curr = queue.peek();
            if (!curr.visited) {
                updateNeighboursDist(curr, queue);
                curr.visited = true;
            }
            queue.poll();
        }
    }

    private static void updateNeighboursDist(Vertex curr, PriorityQueue<Vertex> queue) {
        for (Edge edge : curr.edges) {
            Vertex neighbour = edge.linked;
            if (!neighbour.visited) {
                int newDistance = curr.distance + edge.weight;
                if (newDistance < neighbour.distance) {
                    neighbour.distance = newDistance;
                    neighbour.prev = curr;
                    queue.offer(neighbour);
                }
            }
        }
    }

}
