package org.bloaty.aoc17.problems.impl;

import org.bloaty.aoc17.problems.Problem2;
import org.bloaty.aoc17.utils.IntegerUtils;

public class Problem2A extends Problem2 {

    public Problem2A(String input) {
        super(input);
    }

    @Override
    public int solve() {
        return input.stream()
                    .map(list -> IntegerUtils.minAndMaxOfSequence(list))
                    .map(pair -> pair.get(1) - pair.get(0))
                    .reduce((i, j) -> i + j)
                    .get();
    }

}
