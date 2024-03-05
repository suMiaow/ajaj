package com.meme.algs;

import lombok.Getter;

import java.util.List;
import java.util.Objects;

public class Vertex {

    @Getter
    String name;
    List<Edge> edges;

    boolean visited;
    int inDegree;
    /**
     * 0-not visited 1-visiting 2-visited
     */
    int status;

    int distance = INF;

    static final Integer INF = Integer.MAX_VALUE;

    Vertex prev = null;

    public Vertex(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");

        a.edges = List.of(new Edge(b), new Edge(c));
        b.edges = List.of(new Edge(d));
        c.edges = List.of(new Edge(d));
        d.edges = List.of();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(name, vertex.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
