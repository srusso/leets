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
                Set<Integer> visitedInCurrentSubtree = new HashSet<>();
                Queue<Node> currentSubtree = new LinkedList<>();
                currentSubtree.add(nextNode);
                while (!currentSubtree.isEmpty()) {
                    Node node = currentSubtree.poll();

                    if (visitedInCurrentSubtree.contains(node.value)) {
                        return false;
                    }

                    visitedInCurrentSubtree.add(node.value);
                    allVisited.add(node.value);

                    currentSubtree.addAll(node.children);
                }
            }
        }

        return true;
    }
}
