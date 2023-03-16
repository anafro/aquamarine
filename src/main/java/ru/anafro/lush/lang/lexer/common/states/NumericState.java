package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.IntegerLiteralToken;
import ru.anafro.lush.lang.lexer.common.tokens.NumberLiteralToken;

import static java.lang.Character.isDigit;
import static ru.anafro.lush.lang.lexer.common.LexerSwitchFlag.KEEP_BUFFER_CONTENT;
import static ru.anafro.lush.lang.lexer.common.LexerSwitchFlag.KEEP_CURRENT_CHARACTER;

public class NumericState extends LexerState {
    public NumericState(Lexer lexer) {
        super(lexer, true);
    }

    @Override
    public void handleNextCharacter(char character) {
        if(character == NumberLiteralToken.FLOATING_PART_SEPARATOR) {
            lexer.switchState(new NumberState(lexer), KEEP_CURRENT_CHARACTER, KEEP_BUFFER_CONTENT);
            return;
        }

        if(!isCharCorrect(character)) {
            lexer.addToken(new IntegerLiteralToken(lexer.getBufferContent()));
            lexer.switchStateKeepingCurrentCharacter(new TokenGuessState(lexer));
            return;
        }

        lexer.appendCurrentCharacterToBuffer();
    }

    public static boolean isCharCorrect(char character) {
        return isDigit(character);
    }
}
