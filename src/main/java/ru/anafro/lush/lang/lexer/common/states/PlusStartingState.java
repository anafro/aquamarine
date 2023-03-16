package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.CommentBlockToken;
import ru.anafro.lush.lang.lexer.common.tokens.PlusToken;

public class PlusStartingState extends LexerState {
    public PlusStartingState(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void handleNextCharacter(char character) {
        if(character == CommentBlockToken.SEPARATOR_LINE_ELEMENT) {
            lexer.switchState(new CommentBlockSeparatorState(lexer, CommentBlockSeparatorType.OPENING));
            return;
        }

        lexer.addToken(new PlusToken());
        lexer.switchStateKeepingCurrentCharacter(new TokenGuessState(lexer));
    }
}
