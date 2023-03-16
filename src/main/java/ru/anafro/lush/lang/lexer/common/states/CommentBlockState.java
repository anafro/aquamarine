package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.LexerSwitchFlag;
import ru.anafro.lush.lang.lexer.common.tokens.CommentBlockToken;

import static ru.anafro.lush.lang.lexer.common.LexerSwitchFlag.KEEP_BUFFER_CONTENT;

public class CommentBlockState extends LexerState {
    boolean atBeginningOfLine = true;

    public CommentBlockState(Lexer lexer) {
        super(lexer, true);
    }

    @Override
    public void handleNextCharacter(char character) {
        if(atBeginningOfLine && character == CommentBlockToken.SEPARATOR_LINE_EDGE) {
            lexer.switchState(new PlusInsideCommentBlockState(lexer, this), KEEP_BUFFER_CONTENT);
            return;
        }

        this.ignoreEmptyChars = false;

        if(character == '\n') {
            atBeginningOfLine = true;
        }

        lexer.appendCurrentCharacterToBuffer();
    }
}
