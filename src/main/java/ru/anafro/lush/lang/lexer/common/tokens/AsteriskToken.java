package ru.anafro.lush.lang.lexer.common.tokens;

public class AsteriskToken extends ConstantToken {
    public static final char CHARACTER = '*';
    public static final String FORM = String.valueOf(CHARACTER);

    public AsteriskToken() {
        super("Asterisk operator", FORM);
    }
}
