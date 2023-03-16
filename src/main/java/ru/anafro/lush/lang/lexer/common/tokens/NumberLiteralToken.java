package ru.anafro.lush.lang.lexer.common.tokens;

public class NumberLiteralToken extends Token {
    public static final char FLOATING_PART_SEPARATOR = '.';

    public NumberLiteralToken(String value) {
        super("Number literal", value);
    }

    public double getDoubleValue() {
        return Double.parseDouble(value);
    }

    @Override
    public String toCode() {
        return value;
    }
}
