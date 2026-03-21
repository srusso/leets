package net.sr89;

import net.sr89.types.TreeNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LowestCommonAncestorOfBSTTest {
    LowestCommonAncestorOfBST solution = new LowestCommonAncestorOfBST();

    @ParameterizedTest
    @MethodSource("testCases")
    void decodeWays(TreeNode expected, TreeNode root, TreeNode a, TreeNode b) {
        assertEquals(expected, solution.lowestCommonAncestor(root, a, b));
    }

    private static Stream<Arguments> testCases() {
        TreeNode tree = new TreeNode(
                6,
                new TreeNode(2,
                        new TreeNode(0),
                        new TreeNode(4,
                                new TreeNode(3),
                                new TreeNode(5))),
                new TreeNode(8,
                        new TreeNode(7),
                        new TreeNode(9))
        );

        return Stream.of(
                Arguments.of(
                        tree,
                        tree,
                        tree.left,
                        tree.right
                ),
                Arguments.of(
                        tree.left,
                        tree,
                        tree.left,
                        tree.left.right
                ));
    }

}