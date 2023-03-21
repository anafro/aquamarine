package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.exceptions.LexerProblem;
import ru.anafro.lush.lang.lexer.common.exceptions.LexerProblemType;
import ru.anafro.lush.lang.lexer.common.tokens.NumberLiteralToken;

public class NumberState extends LexerState {
    public NumberState(Lexer lexer) {
        super(lexer, true);
    }

    @Override
    public void handleNextCharacter(char character) {
        if (NumericState.isCharCorrect(character) || character == NumberLiteralToken.FLOATING_PART_SEPARATOR) {
            lexer.appendCurrentCharacterToBuffer();
        } else {
            if (IdState.isCharCorrect(character)) {
                lexer.addError("We found a letter right after the number. It's probably just a typo, so please remove that symbol '%c'.".formatted(
                        character
                ));
            }

            if (lexer.getBufferContent().endsWith(String.valueOf(NumberLiteralToken.FLOATING_PART_SEPARATOR))) {
                lexer.addCosmeticProblem("A fractional part of the number is empty. You probably made a typo. If you wanted to make a number with a fractional part equals to zero, please, use .0 like so: 14.0");
            }

            lexer.addToken(new NumberLiteralToken(lexer.getBufferContent()));
            lexer.switchStateKeepingCurrentCharacter(new TokenGuessState(lexer));
        }
    }
}
