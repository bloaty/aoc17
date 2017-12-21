package org.bloaty.aoc17.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bloaty.aoc17.utils.Point2D.Position;
import org.junit.Test;

public class Point2DTests {
    
    @Test
    public void testFromSpiralIndex() {
        List<Point2D> points =
                Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
                      .map(n -> Point2D.fromSpiralIndex(n))
                      .collect(Collectors.toList());
        List<Point2D> expectedPoints = Arrays.asList(
                new Point2D( 0,  0),  //1
                new Point2D( 1,  0),  //2
                new Point2D( 1,  1),  //3
                new Point2D( 0,  1),  //4
                new Point2D(-1,  1),  //5
                new Point2D(-1,  0),  //6
                new Point2D(-1, -1),  //7
                new Point2D( 0, -1),  //8
                new Point2D( 1, -1),  //9
                new Point2D( 2, -1),  //10
                new Point2D( 2,  0),  //11
                new Point2D( 2,  1),  //12
                new Point2D( 2,  2),  //13
                new Point2D( 1,  2),  //14
                new Point2D( 0,  2),  //15
                new Point2D(-1,  2)); //16
        assertThat(points, equalTo(expectedPoints));
    }
    
    @Test
    public void testGetPosition() {
        List<String> positions =
                Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
                      .map(n -> Point2D.fromSpiralIndex(n).getPosition().name())
                      .collect(Collectors.toList());
        List<String> expectedPositions =
                Arrays.asList(
                        Position.ORIGIN.name(),
                        Position.TWO.name(),
                        Position.TOP_RIGHT_CORNER.name(),
                        Position.TOP.name(),
                        Position.TOP_LEFT_CORNER.name(),
                        Position.LEFT.name(),
                        Position.LOWER_LEFT_CORNER.name(),
                        Position.BOTTOM.name(),
                        Position.BOTTOM.name(),
                        Position.LOWER_RIGHT_CORNER.name(),
                        Position.RIGHT.name(),
                        Position.RIGHT.name(),
                        Position.TOP_RIGHT_CORNER.name(),
                        Position.TOP.name(),
                        Position.TOP.name(),
                        Position.TOP.name());
        assertThat(positions, equalTo(expectedPositions));
    }
    
    @Test
    public void testNext() {
        List<Point2D> nextPoints =
                Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
                      .map(n -> Point2D.fromSpiralIndex(n).next())
                      .collect(Collectors.toList());
        List<Point2D> expectedNextPoints =
                Stream.of(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17)
                      .map(n -> Point2D.fromSpiralIndex(n))
                      .collect(Collectors.toList());
        assertThat(nextPoints, equalTo(expectedNextPoints));
    }
    
    @Test
    public void testPrevious() {
        List<Point2D> previousPoints =
                Stream.of(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17)
                      .map(n -> Point2D.fromSpiralIndex(n).previous())
                      .collect(Collectors.toList());
        List<Point2D> expectedPreviousPoints =
                Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
                      .map(n -> Point2D.fromSpiralIndex(n))
                      .collect(Collectors.toList());
        assertThat(previousPoints, equalTo(expectedPreviousPoints));
    }
    
    @Test
    public void testIndex() {
        List<Integer> indexes =
                Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
                      .map(n -> Point2D.fromSpiralIndex(n).index())
                      .collect(Collectors.toList());
        List<Integer> expectedIndexes =
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        assertThat(indexes, equalTo(expectedIndexes));
    }
    
    @Test
    public void testOlderNeighbors() {
        assertThat(
                Point2D.fromSpiralIndex(1).getOlderNeighbors(),
                is(new ArrayList<>()));
        assertThat(
                Point2D.fromSpiralIndex(2).getOlderNeighbors(),
                containsInAnyOrder(Point2D.fromSpiralIndex(1)));
        assertThat(
                Point2D.fromSpiralIndex(3).getOlderNeighbors(),
                containsInAnyOrder(Point2D.fromSpiralIndex(1),
                                   Point2D.fromSpiralIndex(2)));
        assertThat(
                Point2D.fromSpiralIndex(4).getOlderNeighbors(),
                containsInAnyOrder(Point2D.fromSpiralIndex(1),
                                   Point2D.fromSpiralIndex(2),
                                   Point2D.fromSpiralIndex(3)));
        assertThat(
                Point2D.fromSpiralIndex(5).getOlderNeighbors(),
                containsInAnyOrder(Point2D.fromSpiralIndex(1),
                                   Point2D.fromSpiralIndex(4)));
        assertThat(
                Point2D.fromSpiralIndex(6).getOlderNeighbors(),
                containsInAnyOrder(Point2D.fromSpiralIndex(1),
                                   Point2D.fromSpiralIndex(4),
                                   Point2D.fromSpiralIndex(5)));
        assertThat(
                Point2D.fromSpiralIndex(7).getOlderNeighbors(),
                containsInAnyOrder(Point2D.fromSpiralIndex(1),
                                   Point2D.fromSpiralIndex(6)));
        assertThat(
                Point2D.fromSpiralIndex(8).getOlderNeighbors(),
                containsInAnyOrder(Point2D.fromSpiralIndex(1),
                                   Point2D.fromSpiralIndex(2),
                                   Point2D.fromSpiralIndex(6),
                                   Point2D.fromSpiralIndex(7)));
        assertThat(
                Point2D.fromSpiralIndex(9).getOlderNeighbors(),
                containsInAnyOrder(Point2D.fromSpiralIndex(1),
                                   Point2D.fromSpiralIndex(2),
                                   Point2D.fromSpiralIndex(8)));
        assertThat(
                Point2D.fromSpiralIndex(10).getOlderNeighbors(),
                containsInAnyOrder(Point2D.fromSpiralIndex(2),
                                   Point2D.fromSpiralIndex(9)));
    }

}
