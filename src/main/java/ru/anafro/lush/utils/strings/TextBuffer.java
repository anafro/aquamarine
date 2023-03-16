package ru.anafro.lush.utils.strings;

public class TextBuffer implements CharSequence {
    private final StringBuilder builder = new StringBuilder();

    public TextBuffer(String initialText) {
        append(initialText);
    }

    public TextBuffer() {
        //
    }

    public void clear() {
        builder.setLength(0);
    }

    public TextBuffer append(Object... objects) {
        for(var object : objects) {
            builder.append(object);
        }

        return this;
    }

    public TextBuffer appendLine(Object... objects) {
        for(var object : objects) {
            append(object);
        }

        return append('\n');
    }

    public TextBuffer appendLines(Object... objects) {
        for(var object : objects) {
            appendLine(object);
        }

        return this;
    }

    public String getContent() {
        return builder.toString();
    }

    public String getContentAndClear() {
        var content = getContent();
        clear();

        return content;
    }

    @Override
    public int length() {
        return builder.length();
    }

    @Override
    public char charAt(int index) {
        return builder.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return builder.subSequence(start, end);
    }

    @Override
    public String toString() {
        return getContent();
    }

    public StringBuilder getBuilder() {
        return builder;
    }
}
