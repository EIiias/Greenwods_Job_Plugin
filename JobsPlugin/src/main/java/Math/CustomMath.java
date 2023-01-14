package Math;

public class CustomMath {

    public static double truncate(double value, int decimalPlaces) {
        double multiplier = Math.pow(10, decimalPlaces);
        return Math.floor(value * multiplier) / multiplier;
    }

    public static double randomNumber(int max, int min) {
        return Math.random() * (max - min + 1) + min;
    }
}
