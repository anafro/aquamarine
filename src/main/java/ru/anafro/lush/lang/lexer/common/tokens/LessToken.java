package ru.anafro.lush.lang.lexer.common.tokens;

public class LessToken extends ConstantToken {
    public static final char CHARACTER = '<';
    public static final String FORM = String.valueOf(CHARACTER);

    public LessToken() {
        super("Less operator", FORM);
    }
}
