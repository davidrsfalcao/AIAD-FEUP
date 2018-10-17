package elements;

public class Vehicle {

    private int maximumLoad, currentLoad;
    private int averageConsumption; /* Wh/km (eletric cars) */

    public Vehicle(int maximumLoad, int averageConsumption) {
        this.maximumLoad = maximumLoad;
        this.averageConsumption = averageConsumption;
        currentLoad = 0;
    }
}
