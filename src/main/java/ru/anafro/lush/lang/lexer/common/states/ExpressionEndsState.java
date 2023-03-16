package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.ExpressionEndsToken;

public class ExpressionEndsState extends IntermediateLexerState {
    public ExpressionEndsState(Lexer lexer) {
        super(lexer, true);
    }

    @Override
    public void performAction() {
        lexer.addToken(new ExpressionEndsToken());
    }
}
