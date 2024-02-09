package org.seydaliev.unitsconverter;

public class UnitsConverter {
    public static double convertMetersToCentimeters(double meters) {
        return meters *  100;
    }

    public static double convertCentimetersToMeters(double centimeters) {
        return centimeters /  100;
    }
}
