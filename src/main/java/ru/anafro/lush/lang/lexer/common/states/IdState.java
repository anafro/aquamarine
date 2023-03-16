package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.api.Lush;
import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.IdToken;
import ru.anafro.lush.utils.strings.English;

import static java.lang.Character.*;

public class IdState extends LexerState {
    public static final int MAX_RECOMMENDED_ID_LENGTH = 60;
    public boolean isCurrentCharacterFirst = true;

    public IdState(Lexer lexer) {
        super(lexer);
        this.ignoreEmptyChars = true;
    }

    @Override
    public void handleNextCharacter(char character) {
        this.ignoreEmptyChars = false;

        if(isCurrentCharacterFirst && !isFirstCharCorrect(character)) {
            lexer.addError("The first character of the identifier '%c' seems not correct. Only the uppercase alphabetic character allowed.".formatted(
                    character
            ));
        }

        if (!isCharCorrect(character)) {
            var id = lexer.getBufferContent().trim().replaceAll(" +", " ");

            if(!isIdCorrect(id)) {
                lexer.addCosmeticProblem("You used an identifier called '%s', which is not suitable for naming guidelines of %s. Your variables, class names, and functions should be started with a capital letter, then you can use any lowercase letter or digit. Separate words in your identifier with spaces. For example, an identifier that fits the rules: User age.".formatted(
                        id,
                        Lush.NAME
                ));
            }

            if(!isIdShortEnough(id)) {
                lexer.addCosmeticProblem("You used an identifier '%s', which is a bit too long. We recommend you to use shorter names, at least less than %d characters long. Your identifier is %s long.".formatted(
                        id,
                        MAX_RECOMMENDED_ID_LENGTH,
                        English.titleForManyItems(id.length(), "character")
                ));
            }

            lexer.addToken(new IdToken(id));
            lexer.switchStateKeepingCurrentCharacter(new TokenGuessState(lexer));
            return;
        }

        lexer.appendCurrentCharacterToBuffer();
        isCurrentCharacterFirst = false;
    }

    public static boolean isFirstCharCorrect(char character) {
        return isAlphabetic(character) && isUpperCase(character);
    }

    public static boolean isCharCorrect(char character) {
        return isAlphabetic(character) ||
               isSpaceChar(character) ||
               isDigit(character);
    }

    public static boolean isIdCorrect(String id) {
        return id.matches("([A-Z][a-z0-9]*)( [a-z0-9]+)*");
    }

    public static boolean isIdShortEnough(String id) {
        return id.length() <= MAX_RECOMMENDED_ID_LENGTH;
    }
}
