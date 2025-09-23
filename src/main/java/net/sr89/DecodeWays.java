package net.sr89;

import java.util.Optional;

/**
 * <a href="https://leetcode.com/problems/decode-ways/description/">Leetcode</a>
 */
public class DecodeWays {
    public int numDecodings(String s) {
        if (isZero(s.charAt(0))) {
            return 0;
        }

        final int size = s.length();

        if (size == 1) {
            return ways(s.charAt(0));
        }

        if (size == 2) {
            final char first = s.charAt(0);
            final char second = s.charAt(1);
            return ways(first, second).orElse(0);
        }

        return numDecodings(s, size - 3);
    }

    private int numDecodings(String s, int idx) {
        // represents the result at index idx + 2
        final int nextNextRes = ways(s.charAt(idx + 2));
        final var ways = ways(s.charAt(idx + 1), s.charAt(idx + 2));

        if (ways.isEmpty()) {
            return 0;
        }

        // represents the result at index idx + 1
        final int nextRes = ways.get();

        return numDecodings(s, idx, nextRes, nextNextRes);
    }

    private int numDecodings(String s, int idx, int nextRes, int nextNextRes) {
        int ways = 0;

        for (int i = idx; i >= 0; i--) {
            final char first = s.charAt(i);
            final char second = s.charAt(i + 1);
            final char third = s.charAt(i + 2);

            if (isInvalidCombination(first, second)) {
                return 0;
            }

            if (needToPair(first, second)) {
                ways = nextNextRes;
            } else if (cannotPair(first, second, third)) {
                ways = nextRes;
            } else if (canPair(first, second, third)) {
                ways = nextRes + nextNextRes;
            } else {
                ways = nextRes;
            }

            nextNextRes = nextRes;
            nextRes = ways;
        }

        return ways;
    }

    // number of ways to decode a one-sized array: [a]
    private int ways(char a) {
        return isZero(a) ? 0 : 1;
    }

    // number of ways to decode a two-sized array: [a, b]
    // empty optional if the combination is invalid
    private Optional<Integer> ways(char a, char b) {
        if (isInvalidCombination(a, b)) {
            return Optional.empty();
        } else if (isZero(a)) {
            return Optional.of(0);
        } else if (needToPair(a, b)) {
            return Optional.of(1);
        } else if (canPair(a, b)) {
            return Optional.of(2);
        } else {
            return Optional.of(1);
        }
    }

    private boolean isInvalidCombination(char first, char second) {
        return (isZero(first) && isZero(second))
                || (first >= '3' && isZero(second));
    }

    private boolean cannotPair(char first, char second, char third) {
        return first > '2' || (first == '2' && second > '6') || isZero(third);
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
}
