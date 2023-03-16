package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.AmpersandToken;
import ru.anafro.lush.lang.lexer.common.tokens.StickToken;

public class AmpersandState extends IntermediateLexerState {
    public AmpersandState(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void performAction() {
        lexer.addToken(new AmpersandToken());
    }
}
