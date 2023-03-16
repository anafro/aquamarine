package ru.anafro.lush.utils.console;

public class Console {
    private Console() {
        throw new UnsupportedOperationException("Console's class constructor must not be called. Use static methods instead.");
    }

    public static void print(Object... messages) {
        for(var message : messages) {
            System.out.print(message);
        }
    }
    
    public static void breakLine() {
        print('\n');
    }

    public static void println(Object... messages) {
        print(messages);
        breakLine();
    }

    public static void coloredPrint(String consoleColor, Object... messages) {
        print(consoleColor);
        print(messages);
        print(ConsoleColors.RESET);
    }
    
    public static void coloredPrintln(String consoleColor, Object... messages) {
        coloredPrint(consoleColor, messages);
        breakLine();
    }

    public static void red(Object... messages) {
        coloredPrint(ConsoleColors.RED, messages);
    }

    public static void green(Object... messages) {
        coloredPrint(ConsoleColors.GREEN, messages);
    }

    public static void yellow(Object... messages) {
        coloredPrint(ConsoleColors.YELLOW, messages);
    }

    public static void blue(Object... messages) {
        coloredPrint(ConsoleColors.BLUE, messages);
    }

    public static void purple(Object... messages) {
        coloredPrint(ConsoleColors.PURPLE, messages);
    }

    public static void cyan(Object... messages) {
        coloredPrint(ConsoleColors.CYAN, messages);
    }

    public static void white(Object... messages) {
        coloredPrint(ConsoleColors.WHITE, messages);
    }

    public static void redLine(Object... messages) {
        coloredPrintln(ConsoleColors.RED, messages);
    }

    public static void greenLine(Object... messages) {
        coloredPrintln(ConsoleColors.GREEN, messages);
    }

    public static void yellowLine(Object... messages) {
        coloredPrintln(ConsoleColors.YELLOW, messages);
    }

    public static void blueLine(Object... messages) {
        coloredPrintln(ConsoleColors.BLUE, messages);
    }

    public static void purpleLine(Object... messages) {
        coloredPrintln(ConsoleColors.PURPLE, messages);
    }

    public static void cyanLine(Object... messages) {
        coloredPrintln(ConsoleColors.CYAN, messages);
    }

    public static void whiteLine(Object... messages) {
        coloredPrintln(ConsoleColors.WHITE, messages);
    }
}
