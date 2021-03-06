package org.bloaty.aoc17.problems.impl;

import org.bloaty.aoc17.problems.Problem6;
import org.bloaty.aoc17.utils.MemoryCycleFinder;

public class Problem6A extends Problem6 {
    
    public Problem6A(String input) {
        super(input);
    }

    @Override
    public int solve() {
        int[] inputData = input.stream().mapToInt(i -> i.intValue()).toArray();
        MemoryCycleFinder mcf = new MemoryCycleFinder(inputData);
        int cycleLength = mcf.findCycleLength();
        int cycleStart = mcf.findCycleStart();
        return cycleStart + cycleLength;
    }

}
