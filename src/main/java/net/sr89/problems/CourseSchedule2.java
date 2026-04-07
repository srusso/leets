package net.sr89.problems;

import net.sr89.algorithm.TopologicalSort;

/**
 * <a href="https://leetcode.com/problems/course-schedule-ii/description/">Leetcode</a>
 */
public class CourseSchedule2 {
    TopologicalSort sort = new TopologicalSort();

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        return sort.topologicalSort_Array(numCourses, prerequisites);
    }
}
