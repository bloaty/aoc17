package org.bloaty.aoc17.problems.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bloaty.aoc17.problems.Problem2;
import org.bloaty.aoc17.utils.IntegerUtils;

/*
 * Observations:
 * - If a < b and b % a == 0, then the prime factors p_1, ..., p_n of b
 *   are a superset of the prime factors of a.
 * - Suppose we build a suffix tree (a trie) of prime factors of candidates for a,
 *   such that every path from the root to a leaf in the trie is the set of prime
 *   factors of some such candidate.
 * - We can now probe the trie with the prime factors of b.
 * - If we insert factors in descending order and also probe in descending order, the
 *   branch to probe is uniquely determined.
 * - If we insert and probe in ascending order, we would need to check multiple branches.
 */
public class Problem2B extends Problem2 {

    public Problem2B(String input) {
        super(input);
    }

    @Override
    public int solve() {
        return input.stream()
                    .map(list -> findRatioOfTwoNumbersThatDivideEvenly(list))
                    .reduce((i, j) -> i + j)
                    .get();
    }
    
    private int bruteForceSolution(List<Integer> list) {
        int ratio = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int ii = list.get(i);
                int jj = list.get(j);
                if (ii % jj == 0) {
                    ratio = ii / jj;
                }
                if (jj % ii == 0) {
                    ratio = jj / ii;
                }
            }
        }
        if (ratio == 0) {
            throw new RuntimeException("Did not find two numbers that divide each other evenly in: " + list.toString());
        }
        return ratio;
    }
    
    private int findRatioOfTwoNumbersThatDivideEvenly(List<Integer> list) {
        if (list.isEmpty()) {
            throw new RuntimeException("Tried to operate on empty row!");
        }

        list.sort((a, b) -> a.compareTo(b));
        
        // Short-circuit: every number is multiple of 1, so if 1 is on the list,
        // just return the next smallest number.
        if (list.get(0) == 1) {
            return list.get(1);
        }
        
        ReallyBadIntegerListTrie trie = new ReallyBadIntegerListTrie();
        int ratio = 0;
        for (Integer i: list) {
            List<Integer> primeFactors = IntegerUtils.primeFactors(i);
            int divisor = trie.previouslyInsertedDivisorOf(primeFactors);
            if (divisor > 1) {
                ratio = i / divisor;
                break;
            }
            trie.insert(primeFactors);
        }
        if (ratio == 0) {
            throw new RuntimeException("Did not find two numbers that divide each other evenly in: " + list.toString());
        }
        return ratio;
    }
    
    private static final class ReallyBadIntegerListTrie {
        
        private Map<Integer, ReallyBadIntegerListTrie> trie;
        
        public ReallyBadIntegerListTrie() {
            trie = new HashMap<>();
        }
        
        public void insert(List<Integer> list) {
            // sorted in descending order
            list.sort((a, b) -> b.compareTo(a));
            doInsert(list);
        }
        
        private void doInsert(List<Integer> list) {
            // Every trie trivially contains the empty list, so inserting it is a no-op.
            if (!list.isEmpty()) {
                Integer head = list.remove(0);
                if (!trie.containsKey(head)) {
                    trie.put(head, new ReallyBadIntegerListTrie());
                }
                trie.get(head).doInsert(list);
            }
        }
        
        public int previouslyInsertedDivisorOf(List<Integer> list) {
            List<Integer> listCopy = new ArrayList<>();
            listCopy.addAll(list);
            // sorted in descending order
            listCopy.sort((a, b) -> b.compareTo(a));
            return doPreviouslyInsertedDivisorOf(listCopy, 1);
        }
        
        private int doPreviouslyInsertedDivisorOf(List<Integer> list, int acc) {
            // We have iterated through the list of prime factors we are probing for
            // and reached a leaf node. Therefore, a number that divides the probed-for
            // number has already been inserted.
            if (list.isEmpty() && trie.isEmpty()) {
                return acc;        
            }
            
            // We have run out of prime factors to verify, but did not reach a leaf node.
            // No number that divides the probed-for number has been inserted.
            if (list.isEmpty() && !trie.isEmpty()) {
                return 1;        
            }
            
            // Discard prime factors off the list. When the head of the list matches
            // an existing value, go one level deeper.
            Integer head = list.remove(0);
            if (trie.containsKey(head)) {
                return trie.get(head).doPreviouslyInsertedDivisorOf(list, head * acc);
            }
            
            return doPreviouslyInsertedDivisorOf(list, acc);
        }
        
    }

}
