package net.sr89;

/**
 * <a href="https://leetcode.com/problems/decode-ways/description/">Leetcode</a>
 */
public class DecodeWays {
    public int numDecodings(String s) {
        final int size = s.length();

        if (size == 1) {
            return isInvalidCharacter(s.charAt(0)) || isZero(s.charAt(0)) ? 0 : 1;
        }

        if (size == 2) {
            final char first = s.charAt(0);
            final char second = s.charAt(1);
            if (isZero(first)) {
                return 0;
            } else if (needToPair(first, second)) {
                return 1;
            } else if (canPair(first, second)) {
                return 2;
            } else {
                return 1;
            }
        }

        return numDecodings(s, size - 3);
    }

    public int numDecodings(String s, int idx) {
        // represents the result at index idx + 2
        int nextNextRes = isInvalidCharacter(s.charAt(idx + 2)) || isZero(s.charAt(idx + 2)) ? 0 : 1;
        // represents the result at index idx + 1
        int nextRes;
        // represents the result at index idx
        int curr = 0;
        {
            final char first = s.charAt(idx + 1);
            final char second = s.charAt(idx + 2);
            if (isZero(first)) {
                nextRes = 0;
            } else if (needToPair(first, second)) {
                nextRes = 1;
            } else if (canPair(first, second)) {
                nextRes = 2;
            } else {
                nextRes = 1;
            }
        }

        for (int i = idx; i >= 0; i--) {
            final char first = s.charAt(i);
            final char second = s.charAt(i + 1);
            final char third = s.charAt(i + 2);

            if (needToPair(first, second)) {
                curr = nextNextRes;
            } else if (cannotPair(first, second, third)) {
                curr = nextRes;
            } else if (canPair(first, second, third)) {
                curr = nextRes + nextNextRes;
            } else if (isInvalidCombination(first, second)) {
                return 0;
            } else {
                curr = nextRes;
            }

            nextNextRes = nextRes;
            nextRes = curr;
        }

        return curr;
    }

    private boolean isInvalidCombination(char first, char second) {
        return isZero(first) && isZero(second);
    }

    private boolean cannotPair(char first, char second, char third) {
        return first > '2' || second > '6' || isZero(third);
    }

    private boolean needToPair(char c, char next) {
        return canPair(c, next) && isZero(next);
    }

    private boolean canPair(char c, char next) {
        return c == '1' || (c == '2' && (next <= '6'));
    }

    private boolean canPair(char c, char next, char nextNext) {
        return canPair(c, next) && !isZero(nextNext);
    }

    private boolean isZero(char c) {
        return c == '0';
    }

    private static boolean isInvalidCharacter(char c) {
        return c < '0' || c > '9';
    }
}
