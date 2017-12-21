package org.bloaty.aoc17.problems.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.bloaty.aoc17.problems.Problem3;
import org.bloaty.aoc17.utils.Point2D;

public class Problem3B extends Problem3 {
    
    private static Map<Integer, Map<Integer, Integer>> MEMOIZE;
    
    static {
        MEMOIZE = new HashMap<>();
        Map<Integer, Integer> origin = new HashMap<>();
        origin.put(0, 1);
        MEMOIZE.put(0, origin);
    }

    public Problem3B(String input) {
        super(input);
    }

    @Override
    public int solve() {
        return Stream.iterate(new Point2D(0, 0), p -> p.next())
                     .mapToInt(p -> getValueAtCoordinates(p))
                     .filter(i -> i > input.get(0))
                     .findFirst()
                     .getAsInt();
    }
    
    private int getValueAtCoordinates(Point2D point) {
        if (!MEMOIZE.containsKey(point.x) || !MEMOIZE.get(point.x).containsKey(point.y)) {
            MEMOIZE.putIfAbsent(point.x, new HashMap<>());
            int valueAtPoint = point.getOlderNeighbors()
                                    .parallelStream()
                                    .map(p -> getValueAtCoordinates(p))
                                    .reduce((a,b) -> a + b)
                                    .get();
            MEMOIZE.get(point.x).putIfAbsent(point.y, valueAtPoint);
        }
        return MEMOIZE.get(point.x).get(point.y);
    }

}
