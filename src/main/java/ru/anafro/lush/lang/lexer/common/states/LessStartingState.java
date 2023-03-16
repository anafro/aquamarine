package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.EqualsToken;
import ru.anafro.lush.lang.lexer.common.tokens.LessToken;

public class LessStartingState extends LexerState {
    public LessStartingState(Lexer lexer) {
        super(lexer, true);
    }

    @Override
    public void handleNextCharacter(char character) {
        if(character == EqualsToken.CHARACTER) {
            lexer.switchStateKeepingCurrentCharacter(new LessEqualsState(lexer));
            return;
        }

        lexer.addToken(new LessToken());
        lexer.switchStateDroppingCurrentCharacter(new TokenGuessState(lexer));
    }
}
