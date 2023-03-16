package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.ExtractToken;

public class ExtractState extends IntermediateLexerState {
    public ExtractState(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void performAction() {
        lexer.addToken(new ExtractToken());
    }
}
