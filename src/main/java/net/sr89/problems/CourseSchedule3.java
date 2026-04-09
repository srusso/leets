package net.sr89.problems;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.com/problems/course-schedule-iii/">Leetcode</a>
 */
public class CourseSchedule3 {
    public int scheduleCourse(int[][] courses) {
        // sort courses by end date
        Arrays.sort(courses, (course1, course2) -> {
            int course1LastPossibleEndDate = course1[1];
            int course2LastPossibleEndDate = course2[1];

            return Integer.compare(course1LastPossibleEndDate, course2LastPossibleEndDate);
        });

        int takenCoursesTotalDuration = 0;
        PriorityQueue<Integer> durationsOfTakenCourses = new PriorityQueue<>();

        for (int i = 0 ; i < courses.length ; i++) {
            int duration = courses[i][0];
            int endDate = courses[i][1];

            if (takenCoursesTotalDuration + duration <= endDate) { // can take this course, so take it (greedy algorithm)
                takenCoursesTotalDuration += duration;
                durationsOfTakenCourses.add(duration);
            } else {
                int longestTakenCourse = durationsOfTakenCourses.peek();

                // check if we can take this course because it's shorter than a course we already took.
                // note: we don't need to worry about end dates because the array is already sorted by ascending end dates
                if (longestTakenCourse > duration) {
                    durationsOfTakenCourses.remove();
                    durationsOfTakenCourses.add(duration);
                    takenCoursesTotalDuration -= longestTakenCourse;
                    takenCoursesTotalDuration += duration;
                }
            }
        }

        return durationsOfTakenCourses.size();
    }
}
