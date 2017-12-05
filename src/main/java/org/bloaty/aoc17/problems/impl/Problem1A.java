package org.bloaty.aoc17.problems.impl;

import org.bloaty.aoc17.problems.Problem1;
import org.bloaty.aoc17.utils.IntegerUtils;

public final class Problem1A extends Problem1 {
    
    public Problem1A(String input) {
        super(input);
    }

    @Override
    public final Integer solve() {
        return IntegerUtils.sumOfMatchesAtOffset(this.input, 1);
    }

}
