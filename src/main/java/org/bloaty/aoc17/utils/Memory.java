package org.bloaty.aoc17.utils;

import java.util.Arrays;

import org.apache.commons.lang3.Validate;

public final class Memory {

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