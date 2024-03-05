package com.meme.algs;

import java.util.LinkedList;
import java.util.List;

/**
 * Depth-first search
 */
public class DFS {

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

        dfs2(v1);
    }

    public static void dfs(Vertex v) {
        v.visited = true;
        System.out.println(v);
        for (Edge edge : v.edges) {
            Vertex linked = edge.linked;
            if (!linked.visited) {
                dfs(linked);
            }
        }
    }
    public static void dfs2(Vertex v) {
        LinkedList<Vertex> stack = new LinkedList<>();
        stack.push(v);
        while (!stack.isEmpty()) {
            Vertex pop = stack.pop();
            pop.visited = true;
            System.out.println(pop);
            for (Edge edge : pop.edges) {
                if (!edge.linked.visited) {
                    stack.push(edge.linked);
                }
            }
        }
    }
}
