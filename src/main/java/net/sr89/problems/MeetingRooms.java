package net.sr89.problems;

import net.sr89.types.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://neetcode.io/problems/meeting-schedule/question">Neetcode</a>
 */
public class MeetingRooms {
    public boolean canAttendMeetings(List<Interval> intervals) {
        List<Interval> schedule = new ArrayList<>(Arrays.asList(
                new Interval(Integer.MIN_VALUE, Integer.MIN_VALUE + 1),
                new Interval(Integer.MAX_VALUE - 1, Integer.MAX_VALUE)
        ));

        for (Interval newMeeting : intervals) {
            int addAtIndex = whereToAdd(schedule, newMeeting);

            if (addAtIndex < 0) {
                return false;
            }

            schedule.add(addAtIndex, newMeeting);
        }

        return true;
    }

    private int whereToAdd(List<Interval> schedule, Interval newMeeting) {
        int end = schedule.size() - 1;

        for (int i = 0; i < end; i++) {
            Interval currentMeeting = schedule.get(i);
            Interval nextMeeting = schedule.get(i + 1);

            if (newMeeting.start >= currentMeeting.end && newMeeting.end < nextMeeting.start) {
                return i + 1;
            }
        }

        return -1;
    }
}
