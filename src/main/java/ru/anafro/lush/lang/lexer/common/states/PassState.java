package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.PassToken;

public class PassState extends IntermediateLexerState {
    public PassState(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void performAction() {
        lexer.addToken(new PassToken());
    }
}
