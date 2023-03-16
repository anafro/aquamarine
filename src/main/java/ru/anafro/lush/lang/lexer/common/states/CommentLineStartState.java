package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.exceptions.LexerProblem;
import ru.anafro.lush.lang.lexer.common.exceptions.LexerProblemType;

public class CommentLineStartState extends LexerState {
    public CommentLineStartState(Lexer lexer) {
        super(lexer, false);
    }

    @Override
    public void handleNextCharacter(char character) {
        if(character != '=') {
            lexer.addError("We found a token '==', and we are not sure what you meant. If you meant the equals sign, so use just one '=' instead of two '=='. If you wanted to start a comment, use three '=' in a row: ===");
            return;
        }

        lexer.switchStateDroppingCurrentCharacter(new CommentLineState(lexer));
    }
}
