package net.sr89;

import net.sr89.types.TreeNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ValidateBinarySearchTreeTest {
    final ValidateBinarySearchTree solution = new ValidateBinarySearchTree();

    @ParameterizedTest
    @MethodSource("testCases")
    void runTests(TreeNode input, boolean expected) {
        assertEquals(expected, solution.isValidBST(input));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(
                        new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                        true
                )
                , Arguments.of(
                        new TreeNode(2, new TreeNode(3), new TreeNode(1)),
                        false
                )
                , Arguments.of(
                        new TreeNode(5,
                                new TreeNode(1),
                                new TreeNode(4,
                                        new TreeNode(3),
                                        new TreeNode(6))),
                        false
                )
        );
    }

}