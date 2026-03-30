package net.sr89.problems;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/word-break/description/">Leetcode link</a>
 */
public class WordBreak {
    private Set<String> nope = new HashSet<>();

    public boolean wordBreak(String s, List<String> wordDict) {
        if (nope.contains(s)) {
            return false;
        }

        if (s.isEmpty()) {
            return true;
        }

        for (String word : wordDict) {
            if (s.startsWith(word)) {
                if (wordBreak(s.substring(word.length()), wordDict)) {
                    return true;
                }
            }
        }

        nope.add(s);
        return false;
    }
}
