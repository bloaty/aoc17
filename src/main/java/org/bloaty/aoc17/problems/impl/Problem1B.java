package org.bloaty.aoc17.problems.impl;

import org.bloaty.aoc17.problems.Problem1;
import org.bloaty.aoc17.utils.IntegerUtils;

public final class Problem1B extends Problem1 {
    
    public Problem1B(String input) {
        super(input);
    }

    @Override
    public final int solve() {
        return IntegerUtils.sumOfMatchesAtOffset(this.input, this.input.size() / 2);
    }

}
