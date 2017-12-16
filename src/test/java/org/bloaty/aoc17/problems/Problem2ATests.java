package org.bloaty.aoc17.problems;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.bloaty.aoc17.problems.impl.Problem2A;
import org.junit.Test;

public class Problem2ATests {
    
    @Test
    public void test1() {
        Problem<List<Integer>> p = new Problem2A("5 1 9 5\n7 5 3\n2 4 6 8");
        assertThat(p.solve(), is(18));
    }

}
