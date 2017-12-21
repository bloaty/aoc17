package org.bloaty.aoc17.problems;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Problem4 extends Problem<List<String>> {

    public Problem4(String input) {
        super(input);
    }

    @Override
    List<List<String>> processInput(String input) {
        String[] lines = input.split("\\n");
        List<List<String>> data =
                Arrays.stream(lines)
                      .map(line -> Arrays.stream(line.split("\\s+"))
                                         .collect(Collectors.toList()))
                      .collect(Collectors.toList());
        return data;
    }

}
