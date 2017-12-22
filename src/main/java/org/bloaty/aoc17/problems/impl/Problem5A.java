package org.bloaty.aoc17.problems.impl;

import java.util.stream.IntStream;

import org.bloaty.aoc17.problems.Problem5;

public class Problem5A extends Problem5 {

    public Problem5A(String input) {
        super(input);
    }

    @Override
    public int solve() {
        int len = input.size();
        int[] steps = new int[] {0};
        IntStream.iterate(0, i -> {
                        if (i >= 0 && i < len) {
                            int j = input.get(i);
                            input.set(i, j + 1);
                            return i + j;
                        }
                        return 0;
                    })
                 .peek(n -> steps[0]++)
                 .allMatch(i -> i >= 0 && i < len);
        return steps[0] - 1;
    }

}
