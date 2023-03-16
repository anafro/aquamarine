package ru.anafro.lush.utils.exceptions;

import ru.anafro.lush.logger.ConsoleLogger;
import ru.anafro.lush.logger.Logger;
import ru.anafro.lush.utils.classes.Classes;
import ru.anafro.lush.utils.classes.Exceptions;

public class DefaultExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final Logger logger = new ConsoleLogger();

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        logger.error("An unexpected error called '%s' occurred, because: %s.".formatted(
                Classes.getReadableName(throwable.getClass()),
                throwable.getMessage()
        ));

        logger.error("If you are an experienced developer, reading the stack trace may help you to figure out the problem.");
        logger.error("But it looks like it is our fault. We are sorry.");

        logger.log(Exceptions.getStackTraceAsString(throwable));
    }
}
