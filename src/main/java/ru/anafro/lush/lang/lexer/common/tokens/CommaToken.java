package ru.anafro.lush.lang.lexer.common.tokens;

public class CommaToken extends ConstantToken {
    public static final char CHARACTER = ',';
    public static final String FORM = String.valueOf(CHARACTER);

    public CommaToken() {
        super("Comma", FORM);
    }
}
