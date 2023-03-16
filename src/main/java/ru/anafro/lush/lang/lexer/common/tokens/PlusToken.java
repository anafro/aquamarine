package ru.anafro.lush.lang.lexer.common.tokens;

public class PlusToken extends ConstantToken {
    public static final char CHARACTER = '+';
    public static final String FORM = String.valueOf(CHARACTER);

    public PlusToken() {
        super("Plus operator", FORM);
    }
}
