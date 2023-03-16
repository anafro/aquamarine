package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.NotEqualsToken;

public class NotEqualsState extends IntermediateLexerState {
    public NotEqualsState(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void performAction() {
        lexer.addToken(new NotEqualsToken());
    }
}
