package es.adevinta.spain.friends.utils;

public final class AlphanumericUtils {

    private AlphanumericUtils() {
    }

    public static boolean isAlphanumeric(String input) {
        if (input == null || input.isEmpty()) return false;
        return input.matches("[A-Za-z0-9]+");
    }
}
