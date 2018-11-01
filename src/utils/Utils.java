package utils;

import agents.PostMan;
import elements.Order;
import elements.Point;
import java.util.ArrayList;

/**
 * Class with random useful function
 */
public class Utils {

    /**
     * Algorithm to sort a vector of orders in function of the distance  to the postman
     *
     * @param postMan - Postman who will have the ordered orders
     */
    public static void sortOrders(PostMan postMan){
        sortOrders(postMan.getOrders(), postMan.getPosition(), 0, postMan.getOrders().size());
    }

    /**
     * Algorithm to sort a vector of orders in function of the distance  to the postman
     *
     * @param orders - ArrayList of orders
     * @param position - Position of the postMan
     * @param start - algorithm's start index of ArrayList
     * @param end - algorithm's end index of ArrayList
     */
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
