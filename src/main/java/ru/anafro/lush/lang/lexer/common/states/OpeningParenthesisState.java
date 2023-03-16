package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.OpeningParenthesisToken;

public class OpeningParenthesisState extends IntermediateLexerState {
    public OpeningParenthesisState(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void performAction() {
        lexer.addToken(new OpeningParenthesisToken());
    }
}
