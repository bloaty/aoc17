package org.bloaty.aoc17.utils;

import java.util.ArrayList;
import java.util.List;

public class IntegerUtils {
    
    public static List<Integer> digitStringToIntegerList(String s) {
        int len = s.length();
        List<Integer> list = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            list.add(s.charAt(i) - '0');
        }
        return list;
    }
    
    public static final int sumOfMatchesAtOffset(final List<Integer> input, final int offset) {
        final int len = input.size();
        int acc = 0;
        for (
                int curr = 0, prev = (curr - offset + len) % len;
                curr < len;
                curr++, prev = (prev + 1) % len
        ) {
            if (input.get(curr) == input.get(prev)) {
                acc += input.get(curr);
            }
        }
        return acc;
    }

}
