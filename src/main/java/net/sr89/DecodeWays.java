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

        if (isZero(s.charAt(0))) {
            return 0;
        }

        int ways = 0;

        int consecutiveTwoUpdates = 0;

        for (int i = 0; i < size - 1; i++) {
            final char c = s.charAt(i);
            final char next = s.charAt(i + 1);

            if (isInvalidCharacter(c)) {
                return 0;
            }


            if (c == '0' && (i == 0 || s.charAt(i - 1) > '2')) {
                return 0;
            } else if (c > '2') {
                if (consecutiveTwoUpdates > 1) {
                    ways--;
                }
                consecutiveTwoUpdates = 0;
            } else if (c == '1' || (c == '2' && (s.charAt(i + 1) <= '6'))) {
                if (isZero(next)) {
                    i++;
                    if (consecutiveTwoUpdates > 0) {
                        ways--;
                    } else {
                        ways++;
                    }
                    consecutiveTwoUpdates = 0;
                } else {
                    ways += 2;
                    consecutiveTwoUpdates++;
                }
            } else {
                if (consecutiveTwoUpdates > 1) {
                    ways--;
                }
                consecutiveTwoUpdates = 0;
                ways++;
            }
        }

        if (consecutiveTwoUpdates > 1) {
            ways--;
        }

        return ways;
    }

    private boolean isZero(char c) {
        return c == '0';
    }

    private static boolean isInvalidCharacter(char c) {
        return c < '0' || c > '9';
    }
}
