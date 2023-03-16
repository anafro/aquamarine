package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.*;
import ru.anafro.lush.utils.strings.SpecialChars;

public class TokenGuessState extends LexerState {
    public TokenGuessState(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void handleNextCharacter(char character) {
        if(IdState.isFirstCharCorrect(character)) {
            lexer.switchStateKeepingCurrentCharacter(new IdState(lexer));

        } else if(NumericState.isCharCorrect(character)) {
            lexer.switchStateKeepingCurrentCharacter(new NumericState(lexer));

        } else if(StringLiteralToken.isWrapper(character)) {
            lexer.switchStateDroppingCurrentCharacter(new StringLiteralState(lexer, character));

        } else switch(character) {
            case PlusToken.CHARACTER -> {
                lexer.switchStateDroppingCurrentCharacter(new PlusStartingState(lexer));
            }

            case EqualsToken.CHARACTER -> {
                lexer.switchStateDroppingCurrentCharacter(new EqualsStartingState(lexer));
            }

            case PassToken.CHARACTER -> {
                lexer.switchStateKeepingCurrentCharacter(new PassState(lexer));
            }

            case DashToken.CHARACTER -> {
                lexer.switchStateDroppingCurrentCharacter(new DashStartingState(lexer));
            }

            case AsteriskToken.CHARACTER -> {
                lexer.switchStateKeepingCurrentCharacter(new AsteriskState(lexer));
            }

            case ExclamationMarkToken.CHARACTER -> {
                lexer.switchStateKeepingCurrentCharacter(new ExclamationMarkStartingState(lexer));
            }

            case LessToken.CHARACTER -> {
                lexer.switchStateDroppingCurrentCharacter(new LessStartingState(lexer));
            }

            case GreaterToken.CHARACTER -> {
                lexer.switchStateDroppingCurrentCharacter(new GreaterStartingState(lexer));
            }

            case ExpressionEndsToken.CHARACTER -> {
                lexer.switchStateKeepingCurrentCharacter(new ExpressionEndsState(lexer));
            }

            case NumberLiteralToken.FLOATING_PART_SEPARATOR -> {
                lexer.appendCurrentCharacterToBuffer();
                lexer.switchStateDroppingCurrentCharacter(new NumberState(lexer));
            }

            case OpeningParenthesisToken.CHARACTER -> {
                lexer.switchStateKeepingCurrentCharacter(new OpeningParenthesisState(lexer));
            }

            case ClosingParenthesisToken.CHARACTER -> {
                lexer.switchStateKeepingCurrentCharacter(new ClosingParenthesisState(lexer));
            }

            case OpeningCurlyBracketToken.CHARACTER -> {
                lexer.switchStateKeepingCurrentCharacter(new OpeningCurlyBracketState(lexer));
            }

            case ClosingCurlyBracketToken.CHARACTER -> {
                lexer.switchStateKeepingCurrentCharacter(new ClosingCurlyBracketState(lexer));
            }

            case StickToken.CHARACTER -> {
                lexer.switchStateKeepingCurrentCharacter(new StickState(lexer));
            }

            case AmpersandToken.CHARACTER -> {
                lexer.switchStateKeepingCurrentCharacter(new AmpersandState(lexer));
            }

            case CommaToken.CHARACTER -> {
                lexer.switchStateKeepingCurrentCharacter(new CommaState(lexer));
            }

            case SlashToken.CHARACTER -> {
                lexer.switchStateKeepingCurrentCharacter(new SlashState(lexer));
            }

            default -> {
                lexer.addError("We found a character '%s', which was sadly not expected.".formatted(SpecialChars.backslash(character)));
            }
        }
    }
}
