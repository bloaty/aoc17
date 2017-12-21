package org.bloaty.aoc17.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

public final class Point2D {
    
    static enum Position {
        ORIGIN,
        TWO,
        LOWER_LEFT_CORNER,
        LOWER_RIGHT_CORNER,
        TOP_LEFT_CORNER,
        TOP_RIGHT_CORNER,
        BOTTOM,
        TOP,
        LEFT,
        RIGHT
    }

    public final int x;
    public final int y;
    
    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public static Point2D fromSpiralIndex(int N) {
        double sqrt = Math.sqrt(4 * N - 3);
        
        double posRoot = (1 + sqrt) / 4;
        int posRowNum = (int) Math.floor(posRoot);
        
        double negRoot = (1 - sqrt) / 4;
        int negRowNum = (int) Math.ceil(negRoot);
        
        int R = -1 * negRowNum >= posRowNum ? negRowNum : posRowNum;
        int I = 1 + R * (4 * R - 2);
        int L = R > 0 ? (2 * R + 1) : (2 - 2 * R);

        int rowOfN;
        int colOfN;
        
        if (N - I < L) {
            rowOfN = R;
            colOfN = R <= 0 ? (R + N - I) : (R - (N - I));
        } else {
            rowOfN = R < 0 ? (R + N - I - L + 1) : (R - (N - I - L + 1));
            colOfN = R <= 0 ? (R + L - 1) : (R - L + 1);
        }
        
        return new Point2D(colOfN, rowOfN);
    }
    
    public int index() {
        Point2D p = this;
        int distanceToLowerLeftCorner = 0;
        while (p.getPosition() != Position.LOWER_LEFT_CORNER && p.getPosition() != Position.ORIGIN) {
            p = p.previous();
            distanceToLowerLeftCorner++;
        }
        int index = 1 + p.x * (4 * p.x - 2) + distanceToLowerLeftCorner;
        return index;
    }
    
    public int manhattanDistanceTo(Point2D other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }
    
    public Position getPosition() {
        if (x == 0 && y == 0) {
            return Position.ORIGIN;
        }
        if (x == 1 && y == 0) {
            return Position.TWO;
        }
        if (x < 0 && x == y) {
            return Position.LOWER_LEFT_CORNER;
        }
        if (x < 0 && x == -1 * y) {
            return Position.TOP_LEFT_CORNER;
        }
        if (x > 0 && x == y) {
            return Position.TOP_RIGHT_CORNER;
        }
        if (x > 0 && y == 1 - x) {
            return Position.LOWER_RIGHT_CORNER;
        }
        if (y < 0 && x > y && x * x <= y * y) {
            return Position.BOTTOM;
        }
        if (y > 0 && x * x < y * y) {
            return Position.TOP;
        }
        if (x > 0 && x * x > y * y && y > 1 - x) {
            return Position.RIGHT;
        }
        if (x < 0 && x * x > y * y) {
            return Position.LEFT;
        }
        return null;
    }
    
    public Point2D next() {
        switch (getPosition()) {
        case ORIGIN:
            return new Point2D(1, 0);
        
        case TWO:
            return new Point2D(1, 1);
        
        case LOWER_LEFT_CORNER:
        case BOTTOM:
            return new Point2D(x + 1, y);
        
        case TOP_RIGHT_CORNER:
        case TOP:
            return new Point2D(x - 1, y);
        
        case TOP_LEFT_CORNER:
        case LEFT:
            return new Point2D(x, y - 1);
        
        case LOWER_RIGHT_CORNER:
        case RIGHT:
            return new Point2D(x, y + 1);
        
        default:
            return null;
        }
    }
    
    public Point2D previous() {
        switch (getPosition()) {
        case ORIGIN:
            return null;
        
        case TWO:
            return new Point2D(0, 0);
        
        case LOWER_LEFT_CORNER:
        case LEFT:
            return new Point2D(x, y + 1);
            
        case LOWER_RIGHT_CORNER:
        case BOTTOM:
            return new Point2D(x - 1, y);
        
        case TOP_RIGHT_CORNER:
        case RIGHT:
            return new Point2D(x, y - 1);
            
        case TOP_LEFT_CORNER:
        case TOP:
            return new Point2D(x + 1, y);
        
        default:
            return null;
        }
    }
    
    public List<Point2D> getOlderNeighbors() {
        return Arrays.asList(
                        new Point2D(x, y + 1),
                        new Point2D(x - 1, y + 1),
                        new Point2D(x - 1, y),
                        new Point2D(x - 1, y - 1),
                        new Point2D(x, y - 1),
                        new Point2D(x + 1, y - 1),
                        new Point2D(x + 1, y),
                        new Point2D(x + 1, y + 1))
                     .stream()
                     .filter(p -> p.index() < this.index())
                     .collect(Collectors.toList());
    }
    
    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point2D)) return false;
        
        Point2D other = (Point2D) obj;
        if (x != other.x) return false;
        if (y != other.y) return false;
        return true;
    }

}