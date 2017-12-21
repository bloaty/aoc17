package org.bloaty.aoc17.problems;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.bloaty.aoc17.problems.impl.Problem3B;
import org.junit.Test;

public class Problem3BTests {
    
    @Test
    public void test1() {
        Problem<Integer> p = new Problem3B("1");
        assertThat(p.solve(), is(2));
    }
    
    @Test
    public void test2() {
        Problem<Integer> p = new Problem3B("2");
        assertThat(p.solve(), is(4));
    }
    
    @Test
    public void test3() {
        Problem<Integer> p = new Problem3B("4");
        assertThat(p.solve(), is(5));
    }
    
    @Test
    public void test4() {
        Problem<Integer> p = new Problem3B("800");
        assertThat(p.solve(), is(806));
    }

}
