package org.bloaty.aoc17.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Problem5 extends Problem<Integer> {

    public Problem5(String input) {
        super(input);
    }

    @Override
    List<Integer> processInput(String input) {
        String[] lines = input.split("\\n");
        List<Integer> instructionList =
                Arrays.stream(lines)
                      .map(s -> Integer.parseInt(s))
                      .collect(Collectors.toCollection(ArrayList::new));
        return instructionList;
        
    }

}
