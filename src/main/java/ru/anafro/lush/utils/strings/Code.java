package ru.anafro.lush.utils.strings;

import ru.anafro.lush.api.Lush;
import ru.anafro.lush.utils.collections.Arrays;
import ru.anafro.lush.utils.console.Console;
import ru.anafro.lush.utils.console.ConsoleColors;
import ru.anafro.lush.utils.integers.Ints;

import java.util.Objects;

public final class Code implements CharSequence {
    private static final int LEFT_SNIPPET_PADDING = 3;
    private static final int ERROR_SNIPPET_DEFAULT_LINE_COUNT = 5;
    private static final String CHAR_HIGHLIGHTED_SNIPPET_DEFAULT_CONSOLE_COLOR = ConsoleColors.RED;
    private static final String SNIPPET_PANEL_DELIMITER = "|";
    private static final String DEFAULT_LINE_SEPARATOR = "\n";
    private boolean includeLineSeparators;
    private final String string;


    public Code(String string, boolean includeLineSeparators) {
        this.string = string;
        this.includeLineSeparators = includeLineSeparators;
    }

    public Code(String string) {
        this(string, false);
    }

    @Override
    public int length() {
        return string.length();
    }

    public char charAt(int index) {
        return string.charAt(index);
    }

    public void includeLineSeparators() {
        this.includeLineSeparators = true;
    }

    public void excludeLineSeparators() {
        this.includeLineSeparators = false;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return string.substring(start, end);
    }

    public Character[] characters() {
        return string.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
    }

    public String[] lines() {
        return java.util.Arrays
                .stream(string.split(DEFAULT_LINE_SEPARATOR))
                .map(line -> line + Strings.keepIf(SpecialChars.replaceWithUnicode(DEFAULT_LINE_SEPARATOR), includeLineSeparators))
                .toArray(String[]::new);
    }

    public String lineAt(int index, boolean removeLeadingTabs) {
        var line = lines()[index];

        if (removeLeadingTabs) {
            line = line.stripLeading();
        }

        return line;
    }

    public String lineAt(int index) {
        return lineAt(index, false);
    }

    public int countLines() {
        return lines().length;
    }

    public String snippetLine(int index) {
        return makeSnippetLineOf(index, lineAt(index));
    }

    public int getLineIndexByCharIndex(int charIndex) {
        charIndex = Arrays.indexLimit(charIndex, characters());

        var lines = lines();

        for (int lineIndex = 0; lineIndex < lines.length; lineIndex += 1) {
            var line = lines[lineIndex];

            if (charIndex < line.length()) {
                return lineIndex;
            } else {
                charIndex -= line.length();
            }
        }

        return lines.length - 1;
    }

    public int getLineRelativeIndexByCharIndex(int charIndex) {
        var lines = lines();
        var lineRelativeIndex = charIndex;

        for (String line : lines) {
            if (lineRelativeIndex < line.length()) {
                return lineRelativeIndex;
            } else {
                lineRelativeIndex -= line.length();
            }
        }

        return lineRelativeIndex;
    }

    public String charHighlightedSnippet(int characterIndex, int linesInSnippet, String consoleColor) {
        characterIndex = Arrays.indexLimit(characterIndex, characters());
        linesInSnippet = Arrays.indexLimit(linesInSnippet, lines());

        int highlightedLineIndex = getLineIndexByCharIndex(characterIndex);
        int errorCharIndexInsideLine = getLineRelativeIndexByCharIndex(characterIndex);

        int fromLine = highlightedLineIndex - linesInSnippet / 2;
        int toLine = highlightedLineIndex + Math.ceilDiv(linesInSnippet - 1, 2);

        if (fromLine < 0) {
            toLine += Math.abs(fromLine);
        }

        if (toLine >= countLines()) {
            fromLine -= countLines() - toLine;
        }

        fromLine = Arrays.indexLimit(fromLine, lines());
        toLine = Arrays.indexLimit(toLine, lines());

        StringBuilder errorSnippet = new StringBuilder();
        for (int lineIndex = fromLine; lineIndex <= toLine; lineIndex += 1) {
            var line = lineAt(lineIndex);

            if (lineIndex == highlightedLineIndex) {
                errorSnippet.append(makeSnippetLineOf(lineIndex, ConsoleColors.colorize(line, consoleColor, errorCharIndexInsideLine)));
            } else {
                errorSnippet.append(snippetLine(lineIndex));
            }

            if (lineIndex != toLine) {
                errorSnippet.append('\n');
            }
        }

        return errorSnippet.toString();
    }

    public String charHighlightedSnippet(int characterIndex, String consoleColor) {
        return charHighlightedSnippet(characterIndex, ERROR_SNIPPET_DEFAULT_LINE_COUNT, consoleColor);
    }

    public String charHighlightedSnippet(int characterIndex, int linesInSnippet) {
        return charHighlightedSnippet(characterIndex, linesInSnippet, CHAR_HIGHLIGHTED_SNIPPET_DEFAULT_CONSOLE_COLOR);
    }

    public String charHighlightedSnippet(int characterIndex) {
        return charHighlightedSnippet(characterIndex, ERROR_SNIPPET_DEFAULT_LINE_COUNT);
    }

    public String snippet(int from, int to) {
        StringBuilder snippet = new StringBuilder();

        for (int index = from; index <= to; index += 1) {
            snippet.append(snippetLine(index));
            snippet.append('\n');
        }

        return snippet.toString();
    }

    public String snippet() {
        return snippet(0, countLines() - 1);
    }

    @Override
    public String toString() {
        return string;
    }

    private String makeSnippetLineOf(int index, String line) {
        return " " + ConsoleColors.RESET + ("%" + (Ints.countDigits(countLines()) + LEFT_SNIPPET_PADDING) + "d").formatted(index + 1) + " " + SNIPPET_PANEL_DELIMITER + " " + line;
    }

    public String string() {
        return string;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Code) obj;
        return Objects.equals(this.string, that.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }

}
