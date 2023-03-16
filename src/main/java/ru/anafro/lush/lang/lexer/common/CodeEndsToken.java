package ru.anafro.lush.lang.lexer.common;

import ru.anafro.lush.lang.lexer.common.tokens.Token;

public class CodeEndsToken extends Token {
    public CodeEndsToken() {
        super("Code ends", "");
    }

    @Override
    public String toCode() {
        return value;
    }
}
