package ru.anafro.lush.lang.lexer.common.tokens;

public class OpeningCurlyBracketToken extends ConstantToken {
    public static final char CHARACTER = '{';
    public static final String FORM = String.valueOf(CHARACTER);

    public OpeningCurlyBracketToken() {
        super("Opening curly bracket", FORM);
    }
}
