package elements;

import static utils.Headers.electricityPrice;

public class Vehicle {
    private int maximumLoad, currentLoad;
    private int averageConsumption; /* Wh/km (eletric cars) */

    public Vehicle(int maximumLoad, int averageConsumption) {
        this.maximumLoad = maximumLoad;
        this.averageConsumption = averageConsumption;
        currentLoad = 0;
    }

    public double getTravelPrice(double distance){
        return (double)Math.round(distance * averageConsumption * electricityPrice * 1000d) / 1000d;
    }
    
    public int getMaximumLoad() {
    	return maximumLoad;
    }

    public int getCurrentLoad() {
    	return currentLoad;
    }

}
