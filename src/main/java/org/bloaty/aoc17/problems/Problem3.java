package org.bloaty.aoc17.problems;

import java.util.Arrays;
import java.util.List;

public abstract class Problem3 extends Problem<Integer> {
    
    public Problem3(String input) {
        super(input);
    }
    
    @Override
    List<Integer> processInput(String input) {
        return Arrays.asList(Integer.parseInt(input));
    }

}
