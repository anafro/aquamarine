package ru.anafro.lush.lang.lexer.common.tokens;

public class ClosingParenthesisToken extends ConstantToken {
    public static final char CHARACTER = ')';
    public static final String FORM = String.valueOf(CHARACTER);

    public ClosingParenthesisToken() {
        super("Closing parenthesis", FORM);
    }
}
