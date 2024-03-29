package utils;

import java.io.File;

/**
 * Interface with all constants
 */
public interface Constants {

    /**
     * Price of electricity/Wh. Used to calculate the cost of postmen's travel
     */
    double electricityPrice = 0.0002; // for Wh

    int vehicleVelocity = 60; //km/h
    double ratioTime = 3600/100;

    String db_path = "src" + File.separator + "database" + File.separator + "database.db";

}
