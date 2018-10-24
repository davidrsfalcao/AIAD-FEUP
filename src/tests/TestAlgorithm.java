package tests;

import elements.Order;
import elements.Point;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAlgorithm {

    @Test
    public void testSortOrders(){

        Point position = new Point(0,0);

        Point p1= new Point(10,10);
        Point p2 = new Point(2,2);
        Point p3 = new Point(10,11);
        Order o1 = new Order(p1,10);
        Order o2 = new Order(p2,20);
        Order o3 = new Order(p3,30);

        ArrayList<Order> orders = new ArrayList<>();

        orders.add(o1);
        orders.add(o2);
        orders.add(o3);
        Utils.sortOrders(orders, position,0, orders.size());

        assertEquals(o2, orders.get(0));
        assertEquals(o1, orders.get(1));
        assertEquals(o3, orders.get(2));
    }
}
