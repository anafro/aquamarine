package ru.anafro.lush.lang.lexer.common.tokens;

public class GreaterToken extends ConstantToken {
    public static final char CHARACTER = '>';
    public static final String FORM = String.valueOf(CHARACTER);

    public GreaterToken() {
        super("Greater operator", FORM);
    }
}
