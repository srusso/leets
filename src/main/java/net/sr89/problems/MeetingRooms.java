package net.sr89.problems;

import net.sr89.types.Interval;

import java.util.Comparator;
import java.util.List;

/**
 * <a href="https://neetcode.io/problems/meeting-schedule/question">Neetcode</a>
 */
public class MeetingRooms {
    public boolean canAttendMeetings(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(inter -> inter.start));

        Interval previous = null;

        for (Interval interval : intervals) {
            if (previous == null) {
                previous = interval;
                continue;
            }

            if (interval.start < previous.end) {
                return false;
            }

            previous = interval;
        }

        return true;
    }
}
