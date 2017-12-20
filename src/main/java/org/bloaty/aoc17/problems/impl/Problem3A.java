package org.bloaty.aoc17.problems.impl;

import org.bloaty.aoc17.problems.Problem3;

/*
 *                                         ROW
 * ___________________________________________
 * 65  64  63  62  61  60  59  58  57* 90 |  4
 * 66  37  36  35  34  33  32  31* 56  89 |  3
 * 67  38  17  16  15  14  13* 30  55  88 |  2
 * 68   9  18   5   4   3* 12  29  54  87 |  1
 * 69  40  19   6   1*  2  11  28  53  86 |  0
 * 70  41  20   7*  8   9  10  27  52  85 | -1
 * 71  42  21* 22  23  24  25  26  51  84 | -2
 * 72  43* 44  45  46  47  48  49  50  83 | -3
 * 73* 74  75  76  77  78  79  80  81  82 | -4
 * --------------------------------------------
 * -4  -3  -2  -1  0   1   2   3   4   5  | COL
 * 
 * We focus on spiral indexes that start a "horizontal run" and associate them
 * with row number as follows: (0,1), (1,3), (-1,7), (2,13), (-2,21), etc.
 * We call the spiral indexes that start a horizontal run "row-initial."
 * 
 * These pairs are interpolated by a quadratic: spiral = 4*(row^2) - 2*row + 1.
 * Inverting, row = (1 +- sqrt(4*spiral - 3))/4.
 * 
 * Due to the direction of the spiral, rows are reached in the order 0, 1, -1, 2, -2, etc.
 * To find the row number of the nearest preceding row-initial spiral index I, we round both
 * square roots toward zero (e.g., -3.xxx => -3, 3.xxx => 3) and take the largest one in
 * absolute value, choosing the negative when tied.
 * Example: [-4.xxx, 4.xxx] => -4, [-3.xxx, 4.xxx] => 4.
 * 
 * Given the row number R of the maximum preceding row-initial spiral index I, we compute
 * I using the quadratic polynomial above. We then determine how far past I our given
 * index N is.
 * 
 * The length L of row R is 2R+1 if R > 0 and 2-2R if R <= 0. If N - I < L, N is in the
 * same row as I. Otherwise, N is in a vertical run, offset from the row of I by
 * N - I - L + 1 -- positively if R < 0 and negatively if R > 0.
 * 
 * Now for C, the column of N. Note that the column of R is R. If N - I >= L, N is in a
 * vertical run with C = R + L - 1 for R <= 0 and C = R - L + 1 otherwise. If N is in a
 * horizontal run, C = R + N - I for R <= 0 and C = R - (N - I) otherwise.
 * 
 * Finally, the Manhattan distance of a point (x, y) from origin (0, 0) is just |x| + |y|.
 */

public class Problem3A extends Problem3 {

    public Problem3A(String input) {
        super(input);
    }
    
    @Override
    public int solve() {
        Point2D origin = new Point2D(0, 0);
        return input.stream()
                    .map(n -> spiralToXY(n))
                    .map(p -> p.manhattanDistance(origin))
                    .findFirst().get();
    }
    
    private Point2D spiralToXY(int N) {
        double sqrt = Math.sqrt(4 * N - 3);
        
        double posRoot = (1 + sqrt) / 4;
        int posRowNum = (int) Math.floor(posRoot);
        
        double negRoot = (1 - sqrt) / 4;
        int negRowNum = (int) Math.ceil(negRoot);
        
        int R = -1 * negRowNum >= posRowNum ? negRowNum : posRowNum;
        int I = 1 + R * (4 * R - 2);
        int L = R > 0 ? (2 * R + 1) : (2 - 2 * R);

        int rowOfN;
        if (N - I < L) {
            rowOfN = R;
        } else {
            rowOfN = R < 0 ? (R + N - I - L + 1) : (R - (N - I - L + 1));
        }
        
        int colOfN;
        if (N - I >= L) {
            colOfN = R <= 0 ? (R + L - 1) : (R - L + 1);
        } else {
            colOfN = R <= 0 ? (R + N - I) : (R - (N - I));
        }
        
        return new Point2D(rowOfN, colOfN);
    }
    
//    public static void main(String[] args) {
//        Stream.of(67).map(n -> spiralToXY(n)).forEach(x -> System.out.println(x));
//    }
    
    private static class Point2D {
        private final int x;
        private final int y;
        public Point2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }
        public int manhattanDistance(Point2D other) {
            return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
        }
    }

}
