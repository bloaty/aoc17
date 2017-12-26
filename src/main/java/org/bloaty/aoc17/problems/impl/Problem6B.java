package org.bloaty.aoc17.problems.impl;

import org.bloaty.aoc17.problems.Problem6;
import org.bloaty.aoc17.utils.MemoryCycleFinder;

public class Problem6B extends Problem6 {
    
    public Problem6B(String input) {
        super(input);
    }

    @Override
    public int solve() {
        int[] inputData = input.stream().mapToInt(i -> i.intValue()).toArray();
        MemoryCycleFinder mcf = new MemoryCycleFinder(inputData);
        return mcf.findCycleLength();
    }

}
