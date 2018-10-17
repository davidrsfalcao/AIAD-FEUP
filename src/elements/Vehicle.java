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

        return distance * averageConsumption * electricityPrice;
    }




}
