package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.OpeningCurlyBracketToken;
import ru.anafro.lush.lang.lexer.common.tokens.OpeningParenthesisToken;

public class OpeningCurlyBracketState extends IntermediateLexerState {
    public OpeningCurlyBracketState(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void performAction() {
        lexer.addToken(new OpeningCurlyBracketToken());
    }
}
