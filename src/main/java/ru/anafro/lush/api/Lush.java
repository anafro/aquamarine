package ru.anafro.lush.api;

import ru.anafro.lush.cli.*;
import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.LexerMode;
import ru.anafro.lush.logger.ConsoleLogger;
import ru.anafro.lush.logger.Logger;
import ru.anafro.lush.utils.console.Console;
import ru.anafro.lush.utils.console.ConsoleColors;
import ru.anafro.lush.utils.exceptions.DefaultExceptionHandler;
import ru.anafro.lush.utils.release.Version;

public class Lush {
    public static final String NAME = "Lush";
    public static final String AUTHOR = "Anatoly Frolov";
    public static final Version VERSION = new Version(0, 0, 0);
    public static final Commands commands = new Commands(
            new HelpCommand(),
            new LexCommand()
    );
    public static final Logger logger = new ConsoleLogger();

    private Lush() {
        throw new UnsupportedOperationException("Lush class' constructor must not be called. Use static methods in Lush class instead.");
    }

    public static void init(String[] args) {
        try {
            Thread.currentThread().setUncaughtExceptionHandler(new DefaultExceptionHandler());

            CommandArgsParser parser = new CommandArgsParser(args);
            parser.parse();

            var commandName = parser.getCommandName();
            var arguments = parser.getArguments();
            var flags = parser.getFlags();

            Console.println(getLogo());
            Console.println(getInfo());

            commands.run(commandName, arguments, flags);
        } catch(CommandException | CommandArgsParsingException exception) {
            logger.error(exception.getMessage());
        }
    }

    public static void exit() {
        System.exit(0);
    }

    public static String getLogo() {
        return ConsoleColors.cyan("""
                           .                                                     .
                                                        +
                        .           x                                   .
                                                                    
                         .        :::          :::    :::     ::::::::     :::    :::\s
                                 :+:          :+:    :+:    :+:    :+:    :+:    :+: \s
                                +:+          +:+    +:+    +:+           +:+    +:+  \s
                               +#+          +#+    +:+    +#++:++#++    +#++:++#++   \s
                              +#+          +#+    +#+           +#+    +#+    +#+    \s
                             #+#          #+#    #+#    #+#    #+#    #+#    #+#     \s
                            ##########    ########       #######     ###    ###     v%s\s
                      +                                         -
                                                    .
                                    *                                    +
                                
                """).formatted(VERSION);
    }

    public static String getInfo() {
        return "%s v%s. Crafted with <3 by %s.".formatted(Lush.NAME, Lush.VERSION, Lush.AUTHOR);
    }
}
