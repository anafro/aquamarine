package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.exceptions.LexerProblem;
import ru.anafro.lush.lang.lexer.common.exceptions.LexerProblemType;
import ru.anafro.lush.lang.lexer.common.tokens.CommentBlockToken;

public class CommentBlockSeparatorState extends LexerState {
    private final CommentBlockSeparatorType type;
    private int elementsCount = 0;

    public CommentBlockSeparatorState(Lexer lexer, CommentBlockSeparatorType type) {
        super(lexer);
        this.type = type;
    }

    @Override
    public void handleNextCharacter(char character) {
        switch(character) {
            case CommentBlockToken.SEPARATOR_LINE_EDGE -> {
                if (isOpening()) {
                    lexer.switchStateDroppingCurrentCharacter(new CommentBlockState(lexer));
                }

                if(isClosing()) {
                    lexer.switchStateDroppingCurrentCharacter(new TokenGuessState(lexer));
                }
            }

            case CommentBlockToken.SEPARATOR_LINE_ELEMENT -> {
                elementsCount += 1;
            }

            default -> {
                lexer.addCosmeticProblem("We found a an unusual character inside the comment separator line. Remember that the comment block separator should only contains '%c' surrounded by '%c', for example it should look like: %s".formatted(
                        CommentBlockToken.SEPARATOR_LINE_ELEMENT,
                        CommentBlockToken.SEPARATOR_LINE_EDGE,
                        CommentBlockToken.SEPARATOR_LINE
                ));
            }
        }
    }

    @Override
    public void beforeBeingSwitched() {
        if(elementsCount < CommentBlockToken.MINIMAL_RECOMMENDED_SEPARATOR_LENGTH) {
            lexer.addCosmeticProblem("The comment block separator is a bit too short (%d < %d). Please, remember to keep your comment separators long enough".formatted(
                    elementsCount,
                    CommentBlockToken.MINIMAL_RECOMMENDED_SEPARATOR_LENGTH
            ));
        }

        if(elementsCount > CommentBlockToken.MAXIMAL_RECOMMENDED_SEPARATOR_LENGTH) {
            lexer.addCosmeticProblem("The comment block separator is a bit too long (%d > %d). Please, remember to keep your comment separators not that long".formatted(
                    elementsCount,
                    CommentBlockToken.MAXIMAL_RECOMMENDED_SEPARATOR_LENGTH
            ));
        }
    }

    public CommentBlockSeparatorType getType() {
        return type;
    }

    public boolean isOpening() {
        return type == CommentBlockSeparatorType.OPENING;
    }

    public boolean isClosing() {
        return type == CommentBlockSeparatorType.CLOSING;
    }
}
