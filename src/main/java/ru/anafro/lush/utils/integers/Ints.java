package ru.anafro.lush.utils.integers;

public class Ints {
    private Ints() {
        throw new UnsupportedOperationException("Ints' class constructor must not be called. Use static methods instead.");
    }

    public static int countDigits(int integer) {
        int digitCount = 0;

        do {
            digitCount += 1;
            integer /= 10;
        } while(integer != 0);

        return digitCount;
    }

    public static int limit(int integer, int min, int max) {
        return Math.min(Math.max(integer, min), max);
    }

    public static int bottomLimit(int integer, int min) {
        return Math.max(integer, min);
    }

    public static int topLimit(int integer, int max) {
        return Math.min(integer, max);
    }

    public static void ensurePositive(int integer, String exceptionMessage) {
        if(integer <= 0) {
            throw new UnsupportedOperationException(exceptionMessage);
        }
    }
}
