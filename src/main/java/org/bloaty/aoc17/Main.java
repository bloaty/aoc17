package org.bloaty.aoc17;

import org.apache.commons.cli.ParseException;
import org.bloaty.aoc17.cli.CommandLineManager;
import org.bloaty.aoc17.problems.impl.Problem1A;
import org.bloaty.aoc17.problems.impl.Problem1B;
import org.bloaty.aoc17.problems.impl.Problem2A;
import org.bloaty.aoc17.problems.impl.Problem2B;
import org.bloaty.aoc17.problems.impl.Problem3A;
import org.bloaty.aoc17.problems.impl.Problem3B;
import org.bloaty.aoc17.problems.impl.Problem4A;
import org.bloaty.aoc17.problems.impl.Problem4B;
import org.bloaty.aoc17.problems.impl.Problem5A;
import org.bloaty.aoc17.problems.impl.Problem5B;
import org.bloaty.aoc17.problems.impl.Problem6A;

public class Main {
    
    private static final String PROJECT_NAME = "Advent of Code 2017";
    
    public static void main(String[] args) {
        try {
            final CommandLineManager mgr = CommandLineManager.with(PROJECT_NAME, args);
            mgr.printHelpAndExitIfNeeded();
            final String input = mgr.getInput();
            final String problem = mgr.getProblem();
            final int solution = doProblem(problem, input);
            System.out.println("Solution: " + solution);
        } catch (ParseException e) {
            RuntimeException e2 = new RuntimeException("Failed to parse command line.");
            throw e2;
        }
    }
    
    private static final int doProblem(final String problem, final String input) {
        switch (problem.toLowerCase()) {
        case "1a":
            return (new Problem1A(input)).solve();
        case "1b":
            return (new Problem1B(input)).solve();
        case "2a":
            return (new Problem2A(input)).solve();
        case "2b":
            return (new Problem2B(input)).solve();
        case "3a":
            return (new Problem3A(input)).solve();
        case "3b":
            return (new Problem3B(input)).solve();
        case "4a":
            return (new Problem4A(input)).solve();
        case "4b":
            return (new Problem4B(input)).solve();
        case "5a":
            return (new Problem5A(input)).solve();
        case "5b":
            return (new Problem5B(input)).solve();
        case "6a":
            return (new Problem6A(input)).solve();
        default:
            return 0;
        }
    }

}
