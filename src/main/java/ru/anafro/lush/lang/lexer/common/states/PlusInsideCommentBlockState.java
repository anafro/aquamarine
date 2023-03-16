package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.LexerSwitchFlag;
import ru.anafro.lush.lang.lexer.common.tokens.CommentBlockToken;
import ru.anafro.lush.lang.lexer.common.tokens.PlusToken;

import static ru.anafro.lush.lang.lexer.common.LexerSwitchFlag.KEEP_BUFFER_CONTENT;
import static ru.anafro.lush.lang.lexer.common.LexerSwitchFlag.KEEP_CURRENT_CHARACTER;

public class PlusInsideCommentBlockState extends LexerState {
    private final CommentBlockState commentBlockState;

    public PlusInsideCommentBlockState(Lexer lexer, CommentBlockState commentBlockState) {
        super(lexer, false);
        this.commentBlockState = commentBlockState;
    }

    @Override
    public void handleNextCharacter(char character) {
        if(character == CommentBlockToken.SEPARATOR_LINE_ELEMENT) {
            lexer.addToken(new CommentBlockToken(lexer.getBufferContent()));
            lexer.switchStateKeepingCurrentCharacter(new CommentBlockSeparatorState(lexer, CommentBlockSeparatorType.CLOSING));
        } else {
            lexer.appendToBuffer(PlusToken.CHARACTER);
            lexer.switchState(commentBlockState, KEEP_CURRENT_CHARACTER, KEEP_BUFFER_CONTENT);
        }
    }
}
