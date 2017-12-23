package org.bloaty.aoc17.problems.impl;

import java.util.Arrays;

import org.apache.commons.lang3.Validate;
import org.bloaty.aoc17.problems.Problem6;

public class Problem6A extends Problem6 {
    
    public Problem6A(String input) {
        super(input);
    }

    @Override
    public int solve() {
        int[] inputData = input.stream().mapToInt(i -> i.intValue()).toArray();
        int cycleLength = findCycleLength(inputData);
        int cycleStart = findCycleStart(cycleLength, inputData);
        return cycleStart + cycleLength;
    }
    
    private int findCycleLength(int[] inputData) {
        Memory tortoise = new Memory(inputData);
        Memory hare = tortoise.iterate(1);
        
        int gap = 1;
        while (!tortoise.equals(hare)) {
            tortoise = tortoise.iterate(1);
            hare = hare.iterate(2);
            gap++;
        }
        
        return gap;
    }
    
    private int findCycleStart(int cycleLength, int[] inputData) {
        Memory tortoise = new Memory(inputData);
        Memory hare = tortoise.iterate(cycleLength);
        int start = 0;
        while (!tortoise.equals(hare)) {
            tortoise = tortoise.iterate(1);
            hare = hare.iterate(1);
            start++;
        }
        return start;
    }
    
    private static final class Memory {

        private static final int SIZE = 16;
        
        private final int[] memoryBanks = new int[SIZE];
        
        public Memory(int[] initialData) {
            Validate.isTrue(initialData.length == SIZE);
            for (int i = 0; i < SIZE; i++) {
                memoryBanks[i] = initialData[i];
            }
        }
        
        private Memory iterate() {
            int maxIndex = indexOfMax();
            int blocksToRedistribute = memoryBanks[maxIndex];
            int[] incrementedData = Arrays.copyOf(memoryBanks, SIZE);
            incrementedData[maxIndex] = 0;
            for (int blocks = blocksToRedistribute, i = (maxIndex + 1) % SIZE;
                 blocks > 0;
                 blocks--, i = (i + 1) % SIZE) {
                incrementedData[i] += 1;
            }
            return new Memory(incrementedData);
        }
        
        public Memory iterate(int times) {
            Memory result = this;
            while (times > 0) {
                result = result.iterate();
                times--;
            }
            return result;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (!(obj instanceof Memory))
                return false;
            Memory other = (Memory) obj;
            if (!Arrays.equals(memoryBanks, other.memoryBanks))
                return false;
            return true;
        }
        
        private int indexOfMax() {
            int maxInd = 0;
            for (int i = 0; i <  SIZE; i++) {
                if (memoryBanks[i] > memoryBanks[maxInd]) {
                    maxInd = i;
                }
            }
            return maxInd;
        }
        
    }

}
