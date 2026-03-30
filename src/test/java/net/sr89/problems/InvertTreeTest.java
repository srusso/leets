package net.sr89.problems;

import net.sr89.types.TreeNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvertTreeTest {
    final InvertTree solution = new InvertTree();

    @ParameterizedTest
    @MethodSource("testCases")
    void runTests(TreeNode input, TreeNode expected) {
        assertEquals(expected, solution.invertTree(input));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(
                        new TreeNode(2, new TreeNode(3), new TreeNode(1)),
                        new TreeNode(2, new TreeNode(1), new TreeNode(3))
                ),
                Arguments.of(
                        new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(7, new TreeNode(6), new TreeNode(9))),
                        new TreeNode(4, new TreeNode(7, new TreeNode(9), new TreeNode(6)), new TreeNode(2, new TreeNode(3), new TreeNode(1)))
                ),
                Arguments.of(
                        new TreeNode(2,
                                new TreeNode(3, new TreeNode(1), null),
                                null),
                        new TreeNode(2,
                                null,
                                new TreeNode(3, null, new TreeNode(1)))
                )
        );
    }

}