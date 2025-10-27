package net.sr89;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/implement-magic-dictionary/">Leetcode link</a>
 */
public class MagicDictionary {
    private static final int ALPHABET_SIZE = 'z' - 'a' + 1;

    private final TrieNode dictionary;

    public MagicDictionary() {
        dictionary = newTerminalNode(false);
    }

    /**
     * Up to 100 words.
     * Each word up to 100 characters.
     * Each word only lowercase english letters.
     */
    public void buildDict(String[] dict) {
        for (String word : dict) {
            addToDictionary(word);
        }
    }

    /**
     * @param searchWord word of length up to 100 characters
     */
    public boolean search(String searchWord) {
        var currentNode = dictionary;

        for (int i = 0; i < searchWord.length(); i++) {
            int childIndex = indexOf(searchWord.charAt(i));
            var newNode = currentNode.children[childIndex];

            if (newNode == null) {
                // search every other child with mistakes + 1, startAt=i + 1
                // return the aggregate result
                final int newStartAt = i + 1;
                boolean foundContinuationWithOneMistake = Arrays.stream(currentNode.children)
                        .anyMatch(
                                child -> {
                                    return searchExact(
                                            searchWord,
                                            newStartAt,
                                            child
                                    );
                                }
                        );

                if (foundContinuationWithOneMistake) {
                    return true;
                } else {
                    return false;
                }
            }

            currentNode = newNode;
        }

        return false;
    }

    private boolean searchExact(String searchWord, int startAt, TrieNode startAtNode) {
        if (startAtNode == null) {
            return false;
        }

        var currentNode = startAtNode;

        for (int i = startAt; i < searchWord.length(); i++) {
            int childIndex = indexOf(searchWord.charAt(i));
            var newNode = currentNode.children[childIndex];

            if (newNode == null) {
                return false;
            }

            currentNode = newNode;
        }

        return currentNode != null && currentNode.isInDictionary();
    }

    private void addToDictionary(String word) {
        var currentNode = dictionary;

        for (int i = 0; i < word.length(); i++) {
            int childIndex = indexOf(word.charAt(i));
            var cNode = currentNode.children[childIndex];

            if (cNode == null) {
                currentNode.children[childIndex] = newTerminalNode(i == word.length() - 1);
            }

            currentNode = currentNode.children[childIndex];
        }
    }

    private int indexOf(char c) {
        return c - 'a';
    }

    private static TrieNode newTerminalNode(boolean isInDictionary) {
        return new TrieNode(new TrieNode[ALPHABET_SIZE], isInDictionary);
    }

    record TrieNode(TrieNode[] children, boolean isInDictionary) {

    }
}
