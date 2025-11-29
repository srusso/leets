package net.sr89;

class MedianFinder {
    final int tenToTheFifth = 100000;

    // keeps a count of how many times a number was encountered.
    // i.e. numbers[108866] is how many times we encountered the number 108866
    // (not adjusting for the negative offset, number 0 is actually stored at index 100000 etc.)
    final int[] numbers = new int[200005];

    // how many numbers we met so far
    int total = 0;

    // which number is the current median
    int currentMedian = 0;

    // should name this one better, but:
    // if the current median is X, and we encountered it T times,
    // which one of those T times is the median?
    int currentMedianPosition = 1;

    public MedianFinder() {

    }

    public void addNum(int num) {
        final var newNumber = num + tenToTheFifth;

        numbers[newNumber]++;
        total++;

        if (total == 1) {
            currentMedian = newNumber;
            return;
        }

        if (newNumber == currentMedian) {
            if (isOdd(total)) {
                currentMedianPosition++;
            }
        } else if (newNumber < currentMedian) {
            if (isEven(total)) {
                moveToPrevious(currentMedian);
            }
        } else {
            if (isOdd(total)) {
                moveToNext(currentMedian);
            }
        }
    }

    private int findNext(int startAt) {
        if (currentMedianPosition == numbers[currentMedian]) {
            for (int i = startAt + 1; i < numbers.length; i++) {
                if (numbers[i] > 0) {
                    return i;
                }
            }

            throw new RuntimeException();
        } else {
            return currentMedian;
        }
    }

    private void moveToNext(int startAt) {
        if (currentMedianPosition == numbers[currentMedian]) {
            currentMedianPosition = 1;

            for (int i = startAt + 1; i < numbers.length; i++) {
                if (numbers[i] > 0) {
                    currentMedian = i;
                    return;
                }
            }

            throw new RuntimeException();
        } else {
            currentMedianPosition++;
        }
    }

    private void moveToPrevious(int startAt) {
        if (currentMedianPosition == 1) {
            for (int i = startAt - 1; i > 0; i--) {
                if (numbers[i] > 0) {
                    currentMedian = i;
                    currentMedianPosition = numbers[i];
                    return;
                }
            }

            throw new RuntimeException();
        } else {
            currentMedianPosition--;
        }
    }

    public double findMedian() {
        if (!isEven(total)) {
            return currentMedian - tenToTheFifth;
        } else {
            return ((double)
                    (
                            (findNext(currentMedian) - tenToTheFifth)
                                    + (currentMedian - tenToTheFifth)
                    ) / 2);
        }
    }

    private boolean isEven(int n) {
        return n % 2 == 0;
    }

    private boolean isOdd(int n) {
        return !isEven(n);
    }
}