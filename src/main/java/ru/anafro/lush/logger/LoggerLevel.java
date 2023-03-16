package ru.anafro.lush.logger;

import ru.anafro.lush.utils.console.ConsoleColors;

public enum LoggerLevel {
    MESSAGE(ConsoleColors.WHITE),
    INFO(ConsoleColors.CYAN),
    WARNING(ConsoleColors.YELLOW),
    ERROR(ConsoleColors.RED);

    private final String consoleColor;

    LoggerLevel(String consoleColor) {
        this.consoleColor = consoleColor;
    }

    public String getConsoleColor() {
        return consoleColor;
    }
}
