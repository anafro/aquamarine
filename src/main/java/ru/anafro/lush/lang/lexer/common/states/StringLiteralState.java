package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.StringLiteralToken;

public class StringLiteralState extends LexerState {
    private final char wrapperCharacter;

    public StringLiteralState(Lexer lexer, char wrapperCharacter) {
        super(lexer, false);
        this.wrapperCharacter = wrapperCharacter;
    }

    @Override
    public void handleNextCharacter(char character) {
        if(StringLiteralToken.isWrapper(character)) {
            lexer.addToken(new StringLiteralToken(lexer.getBufferContent()));
            lexer.switchStateDroppingCurrentCharacter(new TokenGuessState(lexer));
        }

        lexer.appendCurrentCharacterToBuffer();
    }

    public char getWrapperCharacter() {
        return wrapperCharacter;
    }

    @Override
    public void afterCodeLexing() {
        lexer.addError("String is not closed. Please, add another %c to close the string.".formatted(wrapperCharacter));
    }
}
