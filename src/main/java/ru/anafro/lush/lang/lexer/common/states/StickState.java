package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.PassToken;
import ru.anafro.lush.lang.lexer.common.tokens.StickToken;

public class StickState extends IntermediateLexerState {
    public StickState(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void performAction() {
        lexer.addToken(new StickToken());
    }
}
