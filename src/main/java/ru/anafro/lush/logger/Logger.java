package ru.anafro.lush.logger;

import ru.anafro.lush.api.Lush;
import ru.anafro.lush.utils.console.ConsoleColors;

public abstract class Logger {
    private final String format = "[$program-name / $log-level]: $message";

    public abstract void log(String message);

    public void log(LoggerLevel level, String message) {
        for(var line : message.split("\n")) {
            log(formatMessage(level, line));
        }
    }

    public void message(String message) {
        log(LoggerLevel.MESSAGE, message);
    }

    public void info(String message) {
        log(LoggerLevel.INFO, message);
    }

    public void warning(String message) {
        log(LoggerLevel.WARNING, message);
    }

    public void error(String message) {
        log(LoggerLevel.ERROR, message);
    }

    private String formatMessage(LoggerLevel level, String message) {
        return format
                .replace("$program-name", Lush.NAME)
                .replace("$log-level", ConsoleColors.colorize(level.name(), level.getConsoleColor()))
                .replace("$message", message);
    }

    public String getFormat() {
        return format;
    }
}
