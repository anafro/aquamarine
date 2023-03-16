package ru.anafro.lush.lang.lexer.common.tokens;

public class IntegerLiteralToken extends Token {

    public IntegerLiteralToken(String value) {
        super("Integer", value);
    }

    public int getIntegerValue() {
        return Integer.parseInt(value);
    }

    @Override
    public String toCode() {
        return value;
    }
}
