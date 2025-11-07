package net.sr89;

import java.util.List;

/**
 * <a href="https://leetcode.com/problems/word-break/description/">Leetcode link</a>
 */
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                if (wordBreak(s.substring(word.length()), wordDict)) {
                    return true;
                }
            }
        }

        return s.isEmpty();
    }
}
