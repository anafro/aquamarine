package ru.anafro.lush.lang.lexer.common.events;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.states.LexerState;
import ru.anafro.lush.lang.lexer.common.tokens.Token;

public abstract class LexerEventListener {
    protected final Lexer lexer;

    protected LexerEventListener(Lexer lexer) {
        this.lexer = lexer;
    }

    public abstract void onHandlingCharacterEvent();
    public abstract void onIgnoredCharacterDetectionEvent();
    public abstract void onLexingCompletedEvent();
    public abstract void onAddingTokenEvent(Token token);
    public abstract void onStateSwitchEvent(LexerState oldState, LexerState newState);
}
