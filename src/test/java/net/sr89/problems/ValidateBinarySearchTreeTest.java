package net.sr89.problems;

import net.sr89.types.TreeNode;
import org.junit.jupiter.api.Test;
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

    @Test
    void simpleTest() {
        assertEquals(true, solution.isValidBST(
                new TreeNode(2, new TreeNode(1), new TreeNode(3))
        ));
    }

    @Test
    void simpleTest2() {
        assertEquals(false, solution.isValidBST(
                new TreeNode(2, new TreeNode(3), new TreeNode(1))
        ));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(
                        new TreeNode(5,
                                new TreeNode(1),
                                new TreeNode(4,
                                        new TreeNode(3),
                                        new TreeNode(6))),
                        false
                )
                , Arguments.of(
                        new TreeNode(Integer.MAX_VALUE),
                        true
                )
                ,
                Arguments.of(
                        new TreeNode(Integer.MIN_VALUE, new TreeNode(Integer.MIN_VALUE), null),
                        false
                )
        );
    }

}