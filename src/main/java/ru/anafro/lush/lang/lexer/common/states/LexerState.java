package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.utils.classes.Classes;
import ru.anafro.lush.utils.collections.Nameable;

public abstract class LexerState implements Nameable {
    protected final Lexer lexer;
    protected boolean ignoreEmptyChars;

    public LexerState(Lexer lexer, boolean ignoreEmptyChars) {
        this.lexer = lexer;
        this.ignoreEmptyChars = ignoreEmptyChars;
    }

    public LexerState(Lexer lexer) {
        this(lexer, true);
    }

    public abstract void handleNextCharacter(char character);

    public boolean wantsEmptyCharsToBeIgnored() {
        return ignoreEmptyChars;
    }

    public Lexer getLexer() {
        return lexer;
    }

    @Override
    public String getName() {
        var fullName = Classes.getReadableName(getClass());

        return fullName.substring(0, fullName.lastIndexOf(" state"));
    }

    public void beforeBeingSwitched() {
        // Override this method in lexer states.
    }

    public void afterCodeLexing() {
        // Override this method in lexer states.
    }
}
