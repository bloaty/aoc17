package org.bloaty.aoc17.problems;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

import org.bloaty.aoc17.problems.impl.Problem1B;

public class Problem1BTests {
    
    @Test
    public void test1() {
        Problem<Integer> p = new Problem1B("1212");
        assertThat(p.solve(), is(6));
    }
    
    @Test
    public void test2() {
        Problem<Integer> p = new Problem1B("1221");
        assertThat(p.solve(), is(0));
    }
    
    @Test
    public void test3() {
        Problem<Integer> p = new Problem1B("123425");
        assertThat(p.solve(), is(4));
    }
    
    @Test
    public void test4() {
        Problem<Integer> p = new Problem1B("123123");
        assertThat(p.solve(), is(12));
    }
    
    @Test
    public void test5() {
        Problem<Integer> p = new Problem1B("12131415");
        assertThat(p.solve(), is(4));
    }

}
