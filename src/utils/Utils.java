package utils;

import agents.PostMan;
import elements.Order;
import elements.Point;

import java.util.ArrayList;

public class Utils {

    public static void sortOrders(PostMan postMan){
        sortOrders(postMan.getOrders(), postMan.getPosition(), 0, postMan.getOrders().size());
    }

    public static void sortOrders(ArrayList<Order> orders, Point position, int start, int end){
        Order aux;
        int i=start,j = end-1;
        Point pivot =  orders.get(((start+end)/2)).getDestiny();
        while(i <= j) {
            while( (orders.get(i).getDestiny().getDistance(position)) < pivot.getDistance(position) && i < end ) {
                i++;
            }
            while( (orders.get(j).getDestiny().getDistance(position)) > pivot.getDistance(position) && j > start) {
                j--;
            }
            if(i <= j) {
                aux = orders.get(i);
                orders.set(i, orders.get(j));
                orders.set(j,aux);
                i++;
                j--;
            }

        }

        if(j > start) {
            sortOrders(orders, position, start,j+1);
        }
        if(i < end) {
            sortOrders(orders, position, i,end);
        }
    }

}
