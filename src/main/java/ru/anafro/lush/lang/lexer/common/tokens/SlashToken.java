package ru.anafro.lush.lang.lexer.common.tokens;

public class SlashToken extends ConstantToken {
    public static final char CHARACTER = '/';
    public static final String FORM = String.valueOf(CHARACTER);

    public SlashToken() {
        super("Slash operator", FORM);
    }
}
