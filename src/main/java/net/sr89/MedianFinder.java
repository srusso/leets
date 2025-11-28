package net.sr89;

class MedianFinder {
    final int tenToTheFifth = 100000;

    // keeps a count of how many times a number was encountered.
    // i.e. numbers[108866] is how many times we encountered the number 108866
    // (not adjusting for the negative offset, number 0 is actually stored at index 100000 etc.)
    final int[] numbers = new int[200005];

    // how many numbers we met so far
    int totalNumbers = 0;

    public MedianFinder() {

    }

    public void addNum(int num) {
        numbers[num + tenToTheFifth]++;
        totalNumbers++;
    }

    public double findMedian() {
        return findMedianImpl() - tenToTheFifth;
    }

    private double findMedianImpl() {
        final var size = totalNumbers;

        if (size % 2 == 0) {
            final int oneIndex = size / 2 - 1;
            final int twoIndex = oneIndex + 1;
            int i = 0;
            int currentIndex = 0;
            int one = 0;
            int two = 0;

            for (; i < numbers.length; i++) {
                currentIndex += numbers[i];

                if (currentIndex == oneIndex) {
                    one = i;
                    break;
                } else if (currentIndex > oneIndex) {
                    one = i - 1;
                    break;
                }
            }

            for (; i < numbers.length; i++) {
                currentIndex += numbers[i];

                if (currentIndex == twoIndex) {
                    two = i;
                    break;
                } else if (currentIndex > twoIndex) {
                    two = i - 1;
                    break;
                }
            }

            return ((double) (one + two)) / 2;
        } else {
            final int oneIndex = size / 2;
            int i = 0;
            int currentIndex = 0;

            for (; i < numbers.length; i++) {
                currentIndex += numbers[i];

                if (currentIndex == oneIndex) {
                    return i;
                } else if (currentIndex > oneIndex) {
                    return i - 1;
                }
            }

            throw new RuntimeException();
        }
    }
}