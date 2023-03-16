package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.EqualsToken;
import ru.anafro.lush.lang.lexer.common.tokens.ExclamationMarkToken;

public class ExclamationMarkStartingState extends LexerState {
    public ExclamationMarkStartingState(Lexer lexer) {
        super(lexer, false);
    }

    @Override
    public void handleNextCharacter(char character) {
        if(character == EqualsToken.CHARACTER) {
            lexer.switchState(new NotEqualsState(lexer));
            return;
        }

        lexer.addToken(new ExclamationMarkToken());
        lexer.switchStateDroppingCurrentCharacter(new TokenGuessState(lexer));
    }
}
