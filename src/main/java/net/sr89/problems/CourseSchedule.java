package net.sr89.problems;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/course-schedule/description/">Leetcode</a>
 */
public class CourseSchedule {
    private static class Node {
        boolean visited = false;
        int value;
        List<Node> children;

        public Node(int value, List<Node> children) {
            this.value = value;
            this.children = children;
        }
    }

    // Solution using topological sort
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return false;
    }

    // Simple solution which builds a directed tree and detects cycles
    public boolean canFinish_SimpleCycleDetectionSolution(int numCourses, int[][] prerequisites) {
        Node [] nodes = new Node[numCourses + 1];

        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i, new LinkedList<>());
        }

        for (int[] pair : prerequisites) {
            int a = pair[0];
            int b = pair[1];
            nodes[a].children.add(nodes[b]);
        }

        return canFinish(nodes);
    }

    private boolean canFinish(Node[] nodes) {
        for(Node nextNode : nodes) {
            if (!nextNode.visited) {
                if (hasCycle(nextNode)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean hasCycle(Node root) {
        return dfs(root, new HashSet<>());
    }

    private boolean dfs(Node node, Set<Integer> recursionStack) {
        if (recursionStack.contains(node.value)) {
            return true; // Cycle detected
        }
        if (node.visited) {
            return false; // Already visited, no cycle from this path
        }

        node.visited = true;
        recursionStack.add(node.value);

        for (Node child : node.children) {
            if (dfs(child, recursionStack)) {
                return true;
            }
        }

        recursionStack.remove(node.value);
        return false;
    }
}
