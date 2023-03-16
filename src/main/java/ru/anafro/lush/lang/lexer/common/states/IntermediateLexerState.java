package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;

public abstract class IntermediateLexerState extends LexerState {
    private final LexerState nextState;

    public IntermediateLexerState(Lexer lexer, LexerState nextState, boolean ignoreEmptyChars) {
        super(lexer, ignoreEmptyChars);
        this.nextState = nextState;
    }

    public IntermediateLexerState(Lexer lexer, LexerState nextState) {
        super(lexer);
        this.nextState = nextState;
    }

    public IntermediateLexerState(Lexer lexer, boolean ignoreEmptyChars) {
        this(lexer, new TokenGuessState(lexer), ignoreEmptyChars);
    }

    public IntermediateLexerState(Lexer lexer) {
        this(lexer, new TokenGuessState(lexer));
    }

    public abstract void performAction();

    @Override
    public final void handleNextCharacter(char character) {
        performAction();
        lexer.switchStateDroppingCurrentCharacter(nextState);
    }
}
