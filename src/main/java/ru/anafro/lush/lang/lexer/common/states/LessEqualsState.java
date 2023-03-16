package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.LessEqualsToken;

public class LessEqualsState extends IntermediateLexerState {
    public LessEqualsState(Lexer lexer) {
        super(lexer, true);
    }

    @Override
    public void performAction() {
        lexer.addToken(new LessEqualsToken());
    }
}
