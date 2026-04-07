package net.sr89.problems;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/course-schedule-iii/">Leetcode</a>
 */
public class CourseSchedule3 {
    public int scheduleCourse(int[][] courses) {
        // would a greedy algorithm work here?
        // pick the next course that:
        // is shortest, AND, is about to "expire"

        // course = courses[0]
        // courseDuration = course[0]
        // courseLastPossibleEndDate = course[1]
        // courseLastPossibleStartDate = courseLastPossibleEndDate - duration

        // push courses as close as possible to their last possible start date?

        // Idea: list of courses sorted by last possible start date, in reverse order, AND by duration
        // first element: shorter among courses that should be started asap

        Arrays.sort(courses, (course1, course2) -> {
            int course1Duration = course1[0];
            int course1LastPossibleEndDate = course1[1];
            int course1LastPossibleStartDate = course1LastPossibleEndDate - course1Duration;

            int course2Duration = course2[0];
            int course2LastPossibleEndDate = course2[1];
            int course2LastPossibleStartDate = course2LastPossibleEndDate - course2Duration;

            int byStartDate = Integer.compare(course1LastPossibleStartDate, course2LastPossibleStartDate);

            if (byStartDate != 0) {
                return Integer.compare(course1Duration, course2Duration);
            } else {
                return byStartDate;
            }
        });

        // iterate over the above array
        // at each step, you got 2 choices:
        // 1. (if possible) take the course
        // 2. don't take the course

        // this is starting to look like something that we can solve with dynamic programming maybe?

        // if you have the solution for N, i.e. how many courses you can take max among the first N
        // does it tell you anything about N+1? no
        // but what about N+2? also no... durations and all that

        int takenCourses = 0;
        int takenCoursesTotalDuration = 0;

        for (int i = 0 ; i < courses.length ; i++) {

        }




        // different approach.... why not take all courses that you can take, greedily. put'em in a bag.
        // now you are FULL - no new courses can be added without removing another course
        // idea: to add another course to the bag (i.e. take it) you need to remove one from the bag.
        // question: which one to remove? probably the one with the longest duration! so the bag should be like a sorted set, sorted by duration
        // then: once you remove that... continue the greedy approach!
        // then: rinse and repeat!
        //

        return 0;
    }
}
