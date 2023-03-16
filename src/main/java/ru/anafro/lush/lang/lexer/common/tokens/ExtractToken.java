package ru.anafro.lush.lang.lexer.common.tokens;

public class ExtractToken extends ConstantToken {
    public static final String FORM = "->";

    public ExtractToken() {
        super("Extract operator", FORM);
    }
}
