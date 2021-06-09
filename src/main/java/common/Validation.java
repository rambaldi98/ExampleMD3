package common;

public class Validation {
    public static boolean regexQuantity(String number) {
        return !number.matches("\\d+") || Integer.parseInt(number) <= 0;
    }

    public static boolean regexPrice(String number) {
        return !number.matches("\\d+([.]\\d+)?") || !(Float.parseFloat(number) > 10000000);
    }
}
