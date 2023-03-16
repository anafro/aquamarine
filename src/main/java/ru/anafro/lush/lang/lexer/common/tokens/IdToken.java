package ru.anafro.lush.lang.lexer.common.tokens;

public class IdToken extends Token {

    public IdToken(String name) {
        super("Identifier", name);
    }

    public String getIdentifierName() {
        return value;
    }

    @Override
    public String toCode() {
        return value;
    }
}
