package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.GreaterEqualsToken;

public class GreaterEqualsState extends IntermediateLexerState {
    public GreaterEqualsState(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void performAction() {
        lexer.addToken(new GreaterEqualsToken());
    }
}
