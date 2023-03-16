package ru.anafro.lush.lang.lexer.common.tokens;

public class PassToken extends ConstantToken {
    public static final char CHARACTER = ':';
    public static final String FORM = String.valueOf(CHARACTER);

    public PassToken() {
        super("Pass operator", FORM);
    }
}
