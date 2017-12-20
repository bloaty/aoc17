package org.bloaty.aoc17.problems;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.bloaty.aoc17.problems.impl.Problem3A;
import org.junit.Test;

public class Problem3ATests {
    
    @Test
    public void test1() {
        Problem<Integer> p = new Problem3A("1");
        assertThat(p.solve(), is(0));
    }
    
    @Test
    public void test2() {
        Problem<Integer> p = new Problem3A("12");
        assertThat(p.solve(), is(3));
    }
    
    @Test
    public void test3() {
        Problem<Integer> p = new Problem3A("23");
        assertThat(p.solve(), is(2));
    }
    
    @Test
    public void test4() {
        Problem<Integer> p = new Problem3A("1024");
        assertThat(p.solve(), is(31));
    }

}
