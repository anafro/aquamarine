package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.StringLiteralToken;
import ru.anafro.lush.utils.strings.SpecialChars;

public class StringCharacterEscapeState extends LexerState {
    public static final char CHARACTER = '\\';

    private final StringLiteralState stringLiteralState;

    public StringCharacterEscapeState(Lexer lexer, StringLiteralState stringLiteralState) {
        super(lexer, false);
        this.stringLiteralState = stringLiteralState;
    }

    @Override
    public void handleNextCharacter(char character) {
        if(!SpecialChars.isSpecialCharSymbol(character) && character != stringLiteralState.getWrapperCharacter()) {
            lexer.addWarning("We don't know such special character as %c%c. If your lexer mode allows, we will ignore the backslash and pretend that you meant just '%c'".formatted(
                    CHARACTER,
                    character,
                    character
            ));
        }

        if(StringLiteralToken.isWrapper(character) && character != stringLiteralState.getWrapperCharacter()) {
            lexer.addCosmeticProblem("You've tried to escape the string wrapper quote %c%c, but inside this string the quotation mark is different - %c, so you don't have to escape it. If your lexer mode allows, we will ignore the backslash and pretend that you meant just '%c'".formatted(
                    CHARACTER,
                    character,
                    stringLiteralState.getWrapperCharacter(),
                    character
            ));
        }

        lexer.appendCurrentCharacterToBuffer();
        lexer.switchStateDroppingCurrentCharacter(stringLiteralState);
    }

    public StringLiteralState getStringLiteralState() {
        return stringLiteralState;
    }
}
