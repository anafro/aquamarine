package ru.anafro.lush.lang.lexer.common.tokens;

public abstract class ConstantToken extends Token {
    public ConstantToken(String name, String form) {
        super(name, form);
    }

    @Override
    public String toCode() {
        return value;
    }
}
