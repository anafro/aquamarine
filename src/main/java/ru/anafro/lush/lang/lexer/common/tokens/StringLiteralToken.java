package ru.anafro.lush.lang.lexer.common.tokens;

import java.util.List;

public class StringLiteralToken extends Token {
    private static final List<Character> WRAPPERS = List.of('\'', '"');
    private static final char RECOMMENDED_WRAPPER = WRAPPERS.get(1);

    public StringLiteralToken(String stringContent) {
        super("String literal", stringContent);
    }

    public String getStringContent() {
        return value;
    }

    @Override
    public String toCode() {
        return RECOMMENDED_WRAPPER + getStringContent() + RECOMMENDED_WRAPPER;
    }

    public static boolean isWrapper(char character) {
        return WRAPPERS.contains(character);
    }
}
