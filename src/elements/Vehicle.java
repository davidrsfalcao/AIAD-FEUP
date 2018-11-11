package elements;

import static utils.Constants.electricityPrice;
import static utils.Constants.vehicleVelocity;

/**
 * Class that represents a vehicle
 */
public class Vehicle {
    private int maximumLoad, currentLoad;
    private int averageConsumption; /* Wh/km (eletric cars) */

    /**
     * Vehicle constructor
     *
     * @param maximumLoad
     * @param averageConsumption
     */
    public Vehicle(int maximumLoad, int averageConsumption) {
        this.maximumLoad = maximumLoad;
        this.averageConsumption = averageConsumption;
        currentLoad = 0;
    }

    /**
     * Returns the price of the travel in function of the distance. The cost is only in order of the vehicle
     * consumption and the electricity price
     *
     * @param distance
     * @return
     */
    public double getTravelPrice(double distance){
        return (double)Math.round(distance * averageConsumption * electricityPrice * 1000d) / 1000d;
    }

    /**
     * Returns the load capacity of the vehicle
     *
     * @return maximumLoad - load capacity of the vehicle
     */
    public int getMaximumLoad() {
    	return maximumLoad;
    }

    /**
     * Returns the actual load of the vehicle
     *
     * @return currentLoad - the number of order actually on the vehicle
     */
    public int getCurrentLoad() {
    	return currentLoad;
    }

    public double getTravelTime(Point pos1, Point pos2){

        double distance = pos1.getDistance(pos2);

        return distance/vehicleVelocity;
    }

    public void addOrder(){
        currentLoad++;
    }

    public void removeOrder(){
        currentLoad--;
    }

}
