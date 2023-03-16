package ru.anafro.lush.lang.lexer.common.exceptions;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.LexerHotFix;
import ru.anafro.lush.utils.collections.NamedList;
import ru.anafro.lush.utils.console.ConsoleColors;
import ru.anafro.lush.utils.strings.Code;
import ru.anafro.lush.utils.strings.TextBuffer;

public class LexerProblem {
    private final LexerProblemType type;
    private final Lexer lexer;
    private final int charIndex;
    private final String description;
    private final NamedList<LexerHotFix> hotFixes;

    public LexerProblem(Lexer lexer, LexerProblemType type, String description, NamedList<LexerHotFix> hotFixes) {
        this.type = type;
        this.charIndex = lexer.getCurrentCharacterIndex();
        this.lexer = lexer;
        this.description = description;
        this.hotFixes = hotFixes;
    }

    public LexerProblem(Lexer lexer, LexerProblemType type, String description) {
        this(lexer, type, description, new NamedList<>());
    }

    public LexerProblemType getType() {
        return type;
    }

    public Lexer getLexer() {
        return lexer;
    }

    public String getDescription() {
        return description;
    }

    public NamedList<LexerHotFix> getHotFixes() {
        return hotFixes;
    }

    public Code getCode() {
        return lexer.getCode();
    }

    public int getCharIndex() {
        return charIndex;
    }

    @Override
    public String toString() {
        TextBuffer text = new TextBuffer();
        text.appendLine(ConsoleColors.colorize(description + " " + type.getDescription(), type.getConsoleColor()));
        text.appendLine(lexer.getCode().charHighlightedSnippet(charIndex, type.getConsoleColor()));

        if(hotFixes.containsAnything()) {
            for(var hotFix : hotFixes) {
                // TODO
            }
        }

        return text.toString();
    }
}
