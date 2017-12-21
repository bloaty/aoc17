package org.bloaty.aoc17.problems.impl;

import org.bloaty.aoc17.problems.Problem3;
import org.bloaty.aoc17.utils.Point2D;

public class Problem3A extends Problem3 {

    public Problem3A(String input) {
        super(input);
    }
    
    @Override
    public int solve() {
        Point2D origin = new Point2D(0, 0);
        return input.stream()
                    .map(n -> Point2D.fromSpiralIndex(n))
                    .map(p -> p.manhattanDistanceTo(origin))
                    .findFirst().get();
    }

}
