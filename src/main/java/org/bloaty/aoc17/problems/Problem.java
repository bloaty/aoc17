package org.bloaty.aoc17.problems;

import java.util.List;

public abstract class Problem<T> {
    
    protected final List<T> input;
    
    public Problem(String input) {
        this.input = processInput(input);
    }
    
    public abstract int solve();
    
    /* package-private */
    abstract List<T> processInput(String input);

}
