package net.sr89.problems;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParenthesesTest {
    final Parentheses solution = new Parentheses();

    @ParameterizedTest
    @MethodSource("testCases")
    void name(int n, Set<String> expected) {
        assertEquals(expected, Set.copyOf(solution.generateParenthesis(n)));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(2, Set.of(
                        "(())", "()()"
                )),

                Arguments.of(3, Set.of(
                        "((()))", "(()())", "(())()", "()(())", "()()()"
                )),

                Arguments.of(4, Set.of(
                        "(((())))",
                        "((()()))",
                        "((())())",
                        "(()(()))",
                        "(()()())",

                        "()(())()",
                        "((()))()",
                        "(()())()",
                        "(())()()",

                        "()()(())",
                        "(())(())",
                        "()(()())",
                        "()((()))",

                        "()()()()"
                ))
        );
    }
}