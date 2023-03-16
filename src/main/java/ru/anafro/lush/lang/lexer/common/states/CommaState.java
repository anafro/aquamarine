package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.AsteriskToken;
import ru.anafro.lush.lang.lexer.common.tokens.CommaToken;

public class CommaState extends IntermediateLexerState {
    public CommaState(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void performAction() {
        lexer.addToken(new CommaToken());
    }
}
