package ru.anafro.lush.lang.lexer.common.tokens;

public class LessEqualsToken extends ConstantToken {
    public static final String FORM = "<=";

    public LessEqualsToken() {
        super("Less or equals operator", FORM);
    }
}
