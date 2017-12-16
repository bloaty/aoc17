package org.bloaty.aoc17.problems;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Problem2 extends Problem<List<Integer>> {
    
    public Problem2(String input) {
        super(input);
    }
    
    @Override
    List<List<Integer>> processInput(String input) {
        String[] lines = input.split("\\n");
        List<List<Integer>> data =
                Arrays.stream(lines)
                      .map(line -> Arrays.stream(line.split("[^0-9]"))
                                         .map(Integer::parseInt)
                                         .collect(Collectors.toList()))
                      .collect(Collectors.toList());
        return data;
    }

}