package ru.anafro.lush.lang.lexer.common.tokens;

public class ExclamationMarkToken extends ConstantToken {
    public static final char CHARACTER = '!';
    public static final String FORM = String.valueOf(CHARACTER);

    public ExclamationMarkToken() {
        super("Exclamation mark operator", FORM);
    }
}
