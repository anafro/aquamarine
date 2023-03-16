package ru.anafro.lush.lang.lexer.common.tokens;

public class ExpressionEndsToken extends Token {
    public static final char CHARACTER = '\n';
    public static final String FORM = String.valueOf(CHARACTER);

    public ExpressionEndsToken() {
        super("Expression ends", FORM);
    }

    @Override
    public String toCode() {
        return value;
    }
}
