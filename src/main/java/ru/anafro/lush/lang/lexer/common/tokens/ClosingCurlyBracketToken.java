package ru.anafro.lush.lang.lexer.common.tokens;

public class ClosingCurlyBracketToken extends ConstantToken {
    public static final char CHARACTER = '}';
    public static final String FORM = String.valueOf(CHARACTER);

    public ClosingCurlyBracketToken() {
        super("Closing curly bracket", FORM);
    }
}
