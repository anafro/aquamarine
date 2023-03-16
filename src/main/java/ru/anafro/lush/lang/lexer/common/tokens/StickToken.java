package ru.anafro.lush.lang.lexer.common.tokens;

public class StickToken extends ConstantToken {
    public static final char CHARACTER = '|';
    public static final String FORM = String.valueOf(CHARACTER);

    public StickToken() {
        super("Stick", FORM);
    }
}
