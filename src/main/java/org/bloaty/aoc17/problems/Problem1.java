package org.bloaty.aoc17.problems;

import java.util.List;

import org.bloaty.aoc17.utils.IntegerUtils;

public abstract class Problem1 extends Problem<Integer> {
    
    public Problem1(String input) {
        super(input);
    }
    
    @Override
    List<Integer> processInput(String input) {
        return IntegerUtils.digitStringToIntegerList(input);
    }

}
