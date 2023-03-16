package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.CommentLineToken;

public class CommentLineState extends LexerState {
    public CommentLineState(Lexer lexer) {
        super(lexer, false);
    }

    @Override
    public void handleNextCharacter(char character) {
        if(character == '\n') {
            lexer.addToken(new CommentLineToken(lexer.getBufferContent()));
            lexer.switchStateDroppingCurrentCharacter(new TokenGuessState(lexer));

            return;
        }

        lexer.appendCurrentCharacterToBuffer();
    }
}
