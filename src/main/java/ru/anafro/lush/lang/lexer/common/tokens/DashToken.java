package ru.anafro.lush.lang.lexer.common.tokens;

public class DashToken extends ConstantToken {
    public static final char CHARACTER = '-';
    public static final String FORM = String.valueOf(CHARACTER);

    public DashToken() {
        super("Dash operator", FORM);
    }
}
