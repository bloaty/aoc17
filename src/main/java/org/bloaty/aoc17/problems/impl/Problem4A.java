package org.bloaty.aoc17.problems.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bloaty.aoc17.problems.Problem4;

public class Problem4A extends Problem4 {

    public Problem4A(String input) {
        super(input);
    }
    
    @Override
    public int solve() {
        return (int) input.stream()
                          .filter(passphrase -> isValidPassphrase(passphrase))
                          .count();
    }
    
    private boolean isValidPassphrase(List<String> passphrase) {
        Set<String> words = new HashSet<>();
        for (String word: passphrase) {
            if (words.contains(word)) return false;
            words.add(word);
        };
        return true;
    }

}
