package ru.anafro.lush.lang.lexer.common.tokens;

import ru.anafro.lush.utils.strings.SpecialChars;

public abstract class Token {
    protected final String name;
    protected final String value;

    public Token(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public abstract String toCode();

    @Override
    public String toString() {
        return "%s = '%s'".formatted(name, SpecialChars.replaceWithBackslashes(value));
    }
}
