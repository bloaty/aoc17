package org.bloaty.aoc17.utils;

import java.util.List;
import java.util.OptionalInt;

public class MemoryCycleFinder {
    
    private final int[] initialData;
    private OptionalInt cycleLength = OptionalInt.empty();
    private OptionalInt cycleStart = OptionalInt.empty();
    
    public MemoryCycleFinder(int[] initialData) {
        this.initialData = initialData;
    }
    
    public int findCycleLength() {
        
        if (cycleLength.isPresent()) return cycleLength.getAsInt();
        
        Memory tortoise = new Memory(initialData);
        Memory hare = tortoise.iterate(1);
        
        int cycle = 1;
        while (!tortoise.equals(hare)) {
            tortoise = tortoise.iterate(1);
            hare = hare.iterate(2);
            cycle++;
        }
        
        List<Integer> cycleCandidates = IntegerUtils.divisorsOf(cycle);
        int prevCycle = 1;
        Memory newHare = tortoise.iterate(prevCycle);
        for (int cycleCandidate: cycleCandidates) {
            newHare = newHare.iterate(cycleCandidate - prevCycle);
            prevCycle = cycleCandidate;
            if (tortoise.equals(newHare)) {
                cycleLength = OptionalInt.of(cycleCandidate);
                return cycleCandidate;
            }
        }
        throw new RuntimeException("Iterated through all possible cycle lengths unsuccessfully.");
    }
    
    public int findCycleStart() {
        
        if (cycleStart.isPresent()) return cycleStart.getAsInt();
        
        Memory tortoise = new Memory(initialData);
        Memory hare = tortoise.iterate(findCycleLength());
        int start = 0;
        while (!tortoise.equals(hare)) {
            tortoise = tortoise.iterate(1);
            hare = hare.iterate(1);
            start++;
        }
        cycleStart = OptionalInt.of(start);
        return start;
    }

}
