package ru.anafro.lush.lang.lexer.common.tokens;

public class OpeningParenthesisToken extends ConstantToken {
    public static final char CHARACTER = '(';
    public static final String FORM = String.valueOf(CHARACTER);

    public OpeningParenthesisToken() {
        super("Opening parenthesis", FORM);
    }
}
