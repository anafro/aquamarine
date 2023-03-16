package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.EqualsToken;
import ru.anafro.lush.lang.lexer.common.tokens.GreaterToken;

public class GreaterStartingState extends LexerState {
    public GreaterStartingState(Lexer lexer) {
        super(lexer, false);
    }

    @Override
    public void handleNextCharacter(char character) {
        if(character == EqualsToken.CHARACTER) {
            lexer.switchStateDroppingCurrentCharacter(new GreaterEqualsState(lexer));
            return;
        }

        lexer.addToken(new GreaterToken());
        lexer.switchStateKeepingCurrentCharacter(new TokenGuessState(lexer));
    }
}
