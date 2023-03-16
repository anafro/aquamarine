package ru.anafro.lush.lang.lexer.common.tokens;

public class GreaterEqualsToken extends ConstantToken {
    public static final String FORM = ">=";

    public GreaterEqualsToken() {
        super("Greater or equals operator", FORM);
    }
}
