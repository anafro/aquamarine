package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.ClosingCurlyBracketToken;
import ru.anafro.lush.lang.lexer.common.tokens.OpeningCurlyBracketToken;

public class ClosingCurlyBracketState extends IntermediateLexerState {
    public ClosingCurlyBracketState(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void performAction() {
        lexer.addToken(new ClosingCurlyBracketToken());
    }
}
