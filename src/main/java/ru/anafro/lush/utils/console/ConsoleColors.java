package ru.anafro.lush.utils.console;

public class ConsoleColors {
    private ConsoleColors() {
        throw new UnsupportedOperationException("ConsoleColor's class constructor must not be called. Use static color constants instead");
    }

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static String colorize(String text, String color, int from, int to) {
        return text.substring(0, from) + color + text.substring(from, to) + RESET + text.substring(to);
    }

    public static String colorize(String text, String color, int charIndex) {
        return colorize(text, color, charIndex, charIndex + 1);
    }

    public static String colorize(String text, String color) {
        return colorize(text, color, 0, text.length());
    }

    public static String reset(String text) {
        return colorize(text, RESET);
    }

    public static String black(String text) {
        return colorize(text, BLACK);
    }

    public static String red(String text) {
        return colorize(text, RED);
    }

    public static String green(String text) {
        return colorize(text, GREEN);
    }

    public static String yellow(String text) {
        return colorize(text, YELLOW);
    }

    public static String blue(String text) {
        return colorize(text, BLUE);
    }

    public static String purple(String text) {
        return colorize(text, PURPLE);
    }

    public static String cyan(String text) {
        return colorize(text, CYAN);
    }

    public static String white(String text) {
        return colorize(text, WHITE);
    }

    public static String reset(String text, int from, int to) {
        return colorize(text, RESET, from, to);
    }

    public static String black(String text, int from, int to) {
        return colorize(text, BLACK, from, to);
    }

    public static String red(String text, int from, int to) {
        return colorize(text, RED, from, to);
    }

    public static String green(String text, int from, int to) {
        return colorize(text, GREEN, from, to);
    }

    public static String yellow(String text, int from, int to) {
        return colorize(text, YELLOW, from, to);
    }

    public static String blue(String text, int from, int to) {
        return colorize(text, BLUE, from, to);
    }

    public static String purple(String text, int from, int to) {
        return colorize(text, PURPLE, from, to);
    }

    public static String cyan(String text, int from, int to) {
        return colorize(text, CYAN, from, to);
    }

    public static String white(String text, int from, int to) {
        return colorize(text, WHITE, from, to);
    }

    public static String reset(String text, int charIndex) {
        return colorize(text, RESET);
    }

    public static String black(String text, int charIndex) {
        return colorize(text, BLACK);
    }

    public static String red(String text, int charIndex) {
        return colorize(text, RED);
    }

    public static String green(String text, int charIndex) {
        return colorize(text, GREEN);
    }

    public static String yellow(String text, int charIndex) {
        return colorize(text, YELLOW);
    }

    public static String blue(String text, int charIndex) {
        return colorize(text, BLUE);
    }

    public static String purple(String text, int charIndex) {
        return colorize(text, PURPLE);
    }

    public static String cyan(String text, int charIndex) {
        return colorize(text, CYAN);
    }

    public static String white(String text, int charIndex) {
        return colorize(text, WHITE);
    }
}
