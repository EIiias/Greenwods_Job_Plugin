package Math;

public class CustomMath {

    //Truncates Double with defined decimalPlaces
    public static double truncate(double value, int decimalPlaces) {
        double multiplier = Math.pow(10, decimalPlaces);
        return Math.floor(value * multiplier) / multiplier;
    }

    //Generate random Number between two ints
    public static double randomNumber(int max, int min) {
        return Math.random() * (max - min + 1) + min;
    }
}
