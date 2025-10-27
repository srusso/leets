package net.sr89;

/**
 * <a href="https://leetcode.com/problems/implement-magic-dictionary/">Leetcode link</a>
 */
public class MagicDictionary {
    private static final int ALPHABET_SIZE = 'z' - 'a' + 1;

    private final TrieNode dictionary;

    public MagicDictionary() {
        dictionary = newTerminalNode();
    }

    /**
     * Up to 100 words.
     * Each word up to 100 characters.
     * Each word only lowercase english letters.
     *
     */
    public void buildDict(String[] dict) {
        for (String word : dict) {
            addToDictionary(word);
        }
    }

    /**
     *
     * @param searchWord word of length up to 100 characters
     */
    public boolean search(String searchWord) {
        return false;
    }

    private void addToDictionary(String word) {
        var currentNode = dictionary;

        for (int i = 0; i < word.length(); i++) {
            int childIndex = indexOf(word.charAt(i));
            var cNode = currentNode.children[childIndex];

            if (cNode == null) {
                currentNode.children[childIndex] = newTerminalNode();
            }

            currentNode = currentNode.children[childIndex];
        }
    }

    private int indexOf(char c) {
        return c - 'a';
    }

    private static TrieNode newTerminalNode() {
        return new TrieNode(new TrieNode[ALPHABET_SIZE]);
    }

    record TrieNode(TrieNode[] children) {

    }
}
