package net.sr89.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.com/problems/path-with-maximum-probability/">Leetcode.</a>
 */
public class PathWithMaximumProbability {
    // Helper class to represent an edge in the graph
    static class Edge {
        int target;
        double weight;

        Edge(int target, double weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    // Helper class to store nodes in the PriorityQueue
    static class Node implements Comparable<Node> {
        int id;
        double probability;

        Node(int id, double probability) {
            this.id = id;
            this.probability = probability;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.probability, other.probability);
        }
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        // dist[v] holds the shortest distance from source to v
        Map<Integer, Double> dist = new HashMap<>();
        // prev[v] holds the previous node in the shortest path
//        Map<Integer, Integer> prev = new HashMap<>();
        // Priority Queue to store unvisited nodes
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 1. Initialization
        for (int i = 0 ; i < n; i++) {
            dist.put(i, 0D);
        }

        dist.put(start_node, 1D); // 100% probability of being able to reach the starting node
        pq.add(new Node(start_node, 1));

        Map<Integer, List<Edge>> graph = buildGraph(edges, succProb);

        while (!pq.isEmpty()) {
            // Extract the node with the smallest distance (highest probability)
            Node current = pq.poll();
            int u = current.id;

            // Optimization: If we found a shorter path already (more probable), skip this entry
            if (current.probability < dist.get(u)) continue;

            // 2. Relaxation
            for (Edge edge : graph.getOrDefault(u, new ArrayList<>())) {
                int v = edge.target;
                double weight = edge.weight;
                double alt = dist.get(u) * weight;

                if (alt > dist.get(v)) {
                    dist.put(v, alt);
                    pq.add(new Node(v, alt));
                }
            }
        }

        return dist.get(end_node);
    }

    private Map<Integer, List<Edge>> buildGraph(int[][] edges, double[] succProb) {
        Map<Integer, List<Edge>> graph = new HashMap<>();

        for (int i = 0 ; i < edges.length; i++) {
            int source = edges[i][0];
            int destination = edges[i][1];
            double probability = succProb[i];

            // add source -> destination edge
            if (!graph.containsKey(source)) {
                graph.put(source, new ArrayList<>());
            }
            graph.get(source).add(new Edge(destination, probability));

            // add the reverse edge
            if (!graph.containsKey(destination)) {
                graph.put(destination, new ArrayList<>());
            }
            graph.get(destination).add(new Edge(source, probability));
        }

        return graph;
    }
}
