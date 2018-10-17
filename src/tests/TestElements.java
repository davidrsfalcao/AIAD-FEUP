package tests;

import elements.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestElements {

    @Test
    public void testPosition(){

        Point p1 = new Point(0,2);
        Point p2 = new Point(2, 2);
        Point p3 = new Point(0,0);


        assertEquals(2, p1.getDistance(p2));
        assertEquals(Math.sqrt(8), p2.getDistance(p3));

    }
}
