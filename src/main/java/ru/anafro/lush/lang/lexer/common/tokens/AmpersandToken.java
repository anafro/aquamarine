package ru.anafro.lush.lang.lexer.common.tokens;

public class AmpersandToken extends ConstantToken {
    public static final char CHARACTER = '&';
    public static final String FORM = String.valueOf(CHARACTER);

    public AmpersandToken() {
        super("Ampersand", FORM);
    }
}
