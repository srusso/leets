package net.sr89;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
                Map<Integer, Set<Integer>> visitedInCurrentSubtreeBy = new HashMap<>();
                Queue<Node> toVisit = new LinkedList<>();
                toVisit.add(nextNode);
                while (!toVisit.isEmpty()) {
                    Node parent = toVisit.poll();

                    allVisited.add(parent.value);
                    toVisit.addAll(parent.children);

                    for (Node child : parent.children) {
                        boolean added = visitedInCurrentSubtreeBy.computeIfAbsent(child.value, i -> new HashSet<>()).add(parent.value);

                        if (!added) { // if it's the second time we visit child from the same parent, we found a cycle
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
