package net.sr89;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/course-schedule/description/">Leetcode</a>
 */
public class CourseSchedule {
    private record Node (int value, List<Node> children) {}

    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
        Queue<Node> allNodes = new LinkedList<>(Arrays.asList(nodes));
        Set<Integer> allVisited = new HashSet<>();

        while(!allNodes.isEmpty()) {
            Node nextNode = allNodes.poll();

            if (!allVisited.contains(nextNode.value)) {
                if (hasCycle(nextNode, allVisited)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean hasCycle(Node root, Set<Integer> allVisited) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recursionStack = new HashSet<>();
        return dfs(root, visited, recursionStack, allVisited);
    }

    private boolean dfs(Node node, Set<Integer> visited, Set<Integer> recursionStack, Set<Integer> allVisited) {
        if (recursionStack.contains(node.value)) {
            return true; // Cycle detected
        }
        if (visited.contains(node.value)) {
            return false; // Already visited, no cycle from this path
        }

        allVisited.add(node.value);
        visited.add(node.value);
        recursionStack.add(node.value);

        for (Node child : node.children()) {
            if (dfs(child, visited, recursionStack, allVisited)) {
                return true;
            }
        }

        recursionStack.remove(node.value);
        return false;
    }
}
