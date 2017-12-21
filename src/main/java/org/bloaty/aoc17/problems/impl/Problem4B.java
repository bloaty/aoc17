package org.bloaty.aoc17.problems.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bloaty.aoc17.problems.Problem4;

public class Problem4B extends Problem4 {

    public Problem4B(String input) {
        super(input);
    }
    
    @Override
    public int solve() {
        return (int) input.stream()
                          .filter(passphrase -> isValidPassphrase(passphrase))
                          .count();
    }
    
    private boolean isValidPassphrase(List<String> passphrase) {
        if (passphrase.size() == 1) return true;
        passphrase = passphrase.stream().map(s -> sortString(s)).collect(Collectors.toList());
        passphrase.sort(null);
        for (int i = 1; i < passphrase.size(); i++) {
            if (passphrase.get(i).equals(passphrase.get(i - 1))) {
                return false;
            }
        }
        return true;
    }
    
    private String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return (new StringBuilder()).append(chars).toString();
    }

}
