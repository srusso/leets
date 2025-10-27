package net.sr89;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

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
        return searchInternal(searchWord, 0, dictionary);
    }

    private boolean searchInternal(String searchWord, int startAt, TrieNode startAtNode) {
        var currentNode = startAtNode;

        for (int i = startAt; i < searchWord.length(); i++) {
            int childIndex = indexOf(searchWord.charAt(i));
            var newNode = currentNode.children[childIndex];

            if (newNode == null) {
                // character at index 'i' doesn't match with 'currentNode', so this is our mistake
                // now let's look into every child for an exact match
                final int newStartAt = i + 1;

                final var exactMatch = currentNode.nonNullChildren()
                        .anyMatch(
                                child -> searchExact(
                                        searchWord,
                                        newStartAt,
                                        child
                                ) != null
                        );

                if (exactMatch) {
                    return true;
                } else {

                }
            }

            currentNode = newNode;
        }

        return false;
    }

    /**
     * Returns the trie node representing the searched word, null if not found.
     */
    private TrieNode searchExact(String searchWord, int startAt, TrieNode startAtNode) {
        var currentNode = startAtNode;

        for (int i = startAt; i < searchWord.length(); i++) {
            int childIndex = indexOf(searchWord.charAt(i));
            var newNode = currentNode.children[childIndex];

            if (newNode == null) {
                return null;
            }

            currentNode = newNode;
        }

        return currentNode.isInDictionary ? currentNode : null;
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

    private static class TrieNode {
        final TrieNode[] children;
        boolean isInDictionary;

        private TrieNode(TrieNode[] children, boolean isInDictionary) {
            this.children = children;
            this.isInDictionary = isInDictionary;
        }

        public Stream<TrieNode> nonNullChildren() {
            return Arrays.stream(children).filter(Objects::nonNull);
        }
    }
}
