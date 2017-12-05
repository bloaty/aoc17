package org.bloaty.aoc17.problems;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

import org.bloaty.aoc17.problems.impl.Problem1A;

public class Problem1ATests {
    
    @Test
    public void test1() {
        Problem<Integer> p = new Problem1A("1122");
        assertThat(p.solve(), is(3));
    }
    
    @Test
    public void test2() {
        Problem<Integer> p = new Problem1A("1111");
        assertThat(p.solve(), is(4));
    }
    
    @Test
    public void test3() {
        Problem<Integer> p = new Problem1A("1234");
        assertThat(p.solve(), is(0));
    }
    
    @Test
    public void test4() {
        Problem<Integer> p = new Problem1A("91212129");
        assertThat(p.solve(), is(9));
    }

}
