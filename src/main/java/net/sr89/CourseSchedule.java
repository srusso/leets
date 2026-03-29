package net.sr89;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
        Set<Integer> allVisited = new HashSet<>();

        for(Node nextNode : nodes) {
            if (!allVisited.contains(nextNode.value)) {
                if (hasCycle(nextNode, allVisited)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean hasCycle(Node root, Set<Integer> allVisited) {
        return dfs(root, allVisited, new HashSet<>());
    }

    private boolean dfs(Node node, Set<Integer> visited, Set<Integer> recursionStack) {
        if (recursionStack.contains(node.value)) {
            return true; // Cycle detected
        }
        if (visited.contains(node.value)) {
            return false; // Already visited, no cycle from this path
        }

        visited.add(node.value);
        recursionStack.add(node.value);

        for (Node child : node.children()) {
            if (dfs(child, visited, recursionStack)) {
                return true;
            }
        }

        recursionStack.remove(node.value);
        return false;
    }
}
