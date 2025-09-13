package net.sr89;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/generate-parentheses/description/?envType=problem-list-v2&envId=dynamic-programming">Leetcode</a>
 */
public class Parentheses {
    public List<String> generateParenthesis(int n) {
        return generateParenthesisRecursive(n);
    }

    public List<String> generateParenthesisRecursive(int n) {
        List<String> res = new ArrayList<>();

        backtrack(res, "", 0, 0, n);

        return res;
    }

    private void backtrack(List<String> res, String currString, int openCount, int closeCount, int maxPairs) {
        if (currString.length() == maxPairs * 2) {
            res.add(currString);
            return;
        }

        if (openCount < maxPairs) { // you can still add an open parenthesis, so do it
            backtrack(res, currString + "(", openCount + 1, closeCount, maxPairs);
        }

        if (closeCount < openCount) { // you need/can close some parentheses, so do it
            backtrack(res, currString + ")", openCount, closeCount + 1, maxPairs);
        }
    }
}
