package net.sr89.problems;

import net.sr89.types.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://neetcode.io/problems/meeting-schedule/question">Neetcode</a>
 */
public class MeetingRooms {
    public boolean canAttendMeetings(List<Interval> intervals) {
        List<Interval> schedule = new ArrayList<>();

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
        if (schedule.isEmpty()) {
            return 0;
        }

        if (schedule.size() == 1) {
            Interval firstMeeting = schedule.getFirst();

            if (newMeeting.end <= firstMeeting.start) {
                return 0;
            }

            if (newMeeting.start >= firstMeeting.end) {
                return 1;
            }

            return -1;
        }

        int end = schedule.size() - 1;

        for (int i = 0; i < end; i++) {
            Interval currentMeeting = schedule.get(i);
            Interval nextMeeting = schedule.get(i + 1);

            if (newMeeting.start >= currentMeeting.end && newMeeting.end < nextMeeting.start) {
                return i + 1;
            }
        }

        Interval lastMeeting = schedule.getLast();

        return newMeeting.start >= lastMeeting.end ? schedule.size() : -1;
    }
}
