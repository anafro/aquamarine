package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;

public class EqualsStartingState extends LexerState {
    public EqualsStartingState(Lexer lexer) {
        super(lexer, true);
    }

    @Override
    public void handleNextCharacter(char character) {
        if(character == '=') {
            lexer.switchStateDroppingCurrentCharacter(new CommentLineStartState(lexer));
            return;
        }

        lexer.switchStateKeepingCurrentCharacter(new TokenGuessState(lexer));
    }
}
