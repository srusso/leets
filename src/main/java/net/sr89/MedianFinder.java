package net.sr89;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class MedianFinder {
    List<Integer> numbers = new ArrayList<>();

    public MedianFinder() {
        
    }
    
    public void addNum(int num) {
        numbers.add(num);
    }
    
    public double findMedian() {
        numbers.sort(Integer::compareTo);

        final var size = numbers.size();
        if (size % 2 == 0) {
            int one = numbers.get(size / 2 - 1);
            int two = numbers.get(size / 2);

            return ((double) (one + two)) / 2;
        } else {
            return numbers.get(size / 2);
        }
    }
}