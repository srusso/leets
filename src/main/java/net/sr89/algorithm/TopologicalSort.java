package net.sr89.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSort {
    /**
     * @return The sorted vertices of the graph. Empty list if the graph contains one or more cycles.
     */
    public List<Integer> topologicalSort(int vertexCount, int[][] edges) {
        // Vertex "i" depends on the vertices at graph.get(i)
        // By the way, this is literally the adjacency list of the graph. It IS the graph.
        List<List<Integer>> graph = new ArrayList<>(vertexCount);

        for (int i = 0; i < vertexCount; i++) {
            graph.add(new ArrayList<>());
        }

        // inDegree[i] counts how many vertices depend directly on "i"
        int[] inDegree = new int[vertexCount];
        for (int[] edge : edges) {
            graph.get(edge[1]).add(edge[0]); // edge[1] -> edge[0]
            inDegree[edge[0]]++;
        }

        // at any time, this queue contains vertices that have no prerequisites left
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertexCount; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            // take a node with no prerequisites left
            int node = queue.poll();
            // add it to the result
            result.add(node);
            // each node that depended on this node now has one less prerequisite...
            for (int neighbor : graph.get(node)) {
                if (--inDegree[neighbor] == 0) {
                    // ...and if it now has 0, then add it to the queue
                    queue.add(neighbor);
                }
            }
        }

        return result.size() == vertexCount ? result : new ArrayList<>();
    }
}
