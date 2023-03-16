package ru.anafro.lush.lang.lexer.common.exceptions;

import ru.anafro.lush.utils.console.ConsoleColors;

public enum LexerProblemType {
    COSMETIC("This error can be ignored with no risk.", ConsoleColors.CYAN),
    WARNING("This error can be probably harmful to your program, but sometimes it's OK to ignore it if you are sure that you know what you are doing.", ConsoleColors.YELLOW),
    ERROR("This error doesn't let the lexer continue lexing the code.", ConsoleColors.RED);

    private final String description;
    private final String consoleColor;

    LexerProblemType(String description, String consoleColor) {
        this.description = description;
        this.consoleColor = consoleColor;
    }

    public String getDescription() {
        return description;
    }

    public String getConsoleColor() {
        return consoleColor;
    }
}
