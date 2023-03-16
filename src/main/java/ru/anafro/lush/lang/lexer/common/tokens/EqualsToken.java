package ru.anafro.lush.lang.lexer.common.tokens;

public class EqualsToken extends ConstantToken {
    public static final char CHARACTER = '=';
    public static final String FORM = String.valueOf(CHARACTER);

    public EqualsToken() {
        super("Equals operator", FORM);
    }
}
