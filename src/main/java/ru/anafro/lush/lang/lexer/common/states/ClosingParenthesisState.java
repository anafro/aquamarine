package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.ClosingParenthesisToken;

public class ClosingParenthesisState extends IntermediateLexerState {
    public ClosingParenthesisState(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void performAction() {
        lexer.addToken(new ClosingParenthesisToken());
    }
}
