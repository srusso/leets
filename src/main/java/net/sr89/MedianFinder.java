package net.sr89;

/**
 * <a href="https://leetcode.com/problems/find-median-from-data-stream">Leetcode link</a>.
 * <p>
 * The "proper" solution uses two priority queues; let's have some fun with arrays.
 * <p>
 * This implementation becomes more efficient the more data is added to the structure, since the moveToNext and moveToPrevious
 * methods have to travel less the more the structure is populated.
 */
class MedianFinder {
    // leetcode says each number is between -10^5 and 10^5.
    // In the numbers array below, we want to store -10^5 at position 0, and so on,
    // so this is the offset.
    final int offset = 100000;
    final int offsetTimesTwo = offset * 2;

    // This array keeps the count of how many times a number was encountered.
    // For example, numbers[108866 + offset] stores how many times we encountered the number 108866.
    final int[] numbers = new int[200001];

    // How many numbers we added so far.
    int total = 0;

    // Self-explanatory: stores the current median value (includes the offset).
    int currentMedian = 0;

    // Should name this one better, but this field answers the following question:
    // if the current median is X, and we encountered it T times,
    // which one of those T times is the median?
    // See comments in the addNum() method for another explanation.
    int currentMedianPointer = 1;

    public MedianFinder() {

    }

    /**
     * If you have an odd amount of numbers, the median is the middle number.
     * <p>
     * If you have an even amount of numbers, the median is the average of the two middle numbers.
     * <p>
     * In the even case, this solution keeps a "pointer" to the first of the two middle numbers, then returns
     * the average of that and the next number.
     */
    public double findMedian() {
        if (isEven(total)) {
            return (double) (findNext() + currentMedian - offsetTimesTwo) / 2;
        } else {
            return currentMedian - offset;
        }
    }

    public void addNum(int num) {
        final var newNumber = num + offset;

        numbers[newNumber]++;
        total++;

        if (total == 1) {
            currentMedian = newNumber;
            return;
        }

        if (newNumber == currentMedian) {
            // Imagine you keep adding the same number over and over...
            // The median won't change, but you need to keep track of which occurrence of the number is the median.
            // Write down some examples and try yourself: the "pointer" to the current median should only move
            // when we go from an even amount of numbers to an odd amount of numbers.
            //
            // Let's say you start with an even amount of numbers:
            //
            // median pointer \ points to the FIRST 3
            //                 \
            //                  v
            //               1, 3, 3, 5.
            // The median is (3 + 3) / 2 = 3.
            //
            //
            // Now add another 3, so you have an odd amount of numbers:
            //
            // median pointer \ now points to the SECOND 3
            //                 \
            //                  v
            //            1, 3, 3, 3, 5.
            // The median is 3.
            if (isOdd(total)) {
                currentMedianPointer++;
            }
        } else if (newNumber < currentMedian) {
            // Similar reasoning as the comment above, but this time
            // we may update not only the currentMedianPointer, but also the currentMedian itself.
            // The moveToPrevious method does that.
            if (isEven(total)) {
                moveToPrevious();
            }
        } else { // newNumber > currentMedian
            // Same again, but other way around. For intuition try some examples yourself.
            if (isOdd(total)) {
                moveToNext();
            }
        }
    }

    private int findNext() {
        if (currentMedianPointer == numbers[currentMedian]) {
            for (int i = currentMedian + 1; i < numbers.length; i++) {
                if (numbers[i] > 0) {
                    return i;
                }
            }

            throw new RuntimeException();
        } else {
            return currentMedian;
        }
    }

    private int findPrevious() {
        if (currentMedianPointer == 1) {
            for (int i = currentMedian - 1; i > 0; i--) {
                if (numbers[i] > 0) {
                    return i;
                }
            }

            throw new RuntimeException();
        } else {
            return currentMedian;
        }
    }

    private void moveToNext() {
        final int nextMedian = findNext();

        if (nextMedian == currentMedian) {
            currentMedianPointer++;
        } else {
            currentMedian = nextMedian;
            currentMedianPointer = 1;
        }
    }

    private void moveToPrevious() {
        final int previous = findPrevious();

        if (previous == currentMedian) {
            currentMedianPointer--;
        } else {
            currentMedian = previous;
            currentMedianPointer = numbers[currentMedian];
        }
    }

    private boolean isEven(int n) {
        return n % 2 == 0;
    }

    private boolean isOdd(int n) {
        return !isEven(n);
    }
}