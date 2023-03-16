package ru.anafro.lush.lang.lexer.common;

public enum LexerMode {
    FORGIVING("Lexer will try to repair the code when the lexer come across the error when it's possible or ignore the error at all."),
    STRICT("Lexer will shut the lexing down if the lexer came across the error.");

    private final String description;

    LexerMode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
