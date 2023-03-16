package ru.anafro.lush.lang.lexer.common.tokens;

public class CommentBlockToken extends Token {
    public static final int MINIMAL_RECOMMENDED_SEPARATOR_LENGTH = 8;
    public static final int MAXIMAL_RECOMMENDED_SEPARATOR_LENGTH = 50;
    private static final int DEFAULT_SEPARATOR_LINE_LENGTH = 20;
    public static final char SEPARATOR_LINE_EDGE = '+';
    public static final char SEPARATOR_LINE_ELEMENT = '-';
    public static final String SEPARATOR_LINE = SEPARATOR_LINE_EDGE + String.valueOf(SEPARATOR_LINE_ELEMENT).repeat(DEFAULT_SEPARATOR_LINE_LENGTH) + SEPARATOR_LINE_EDGE;


    public CommentBlockToken(String commentText) {
        super("Comment block", commentText);
    }

    public String getCommentText() {
        return value;
    }

    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append(SEPARATOR_LINE);
        code.append('\n');

        code.append(getCommentText());

        code.append(SEPARATOR_LINE);
        code.append('\n');

        return code.toString();
    }
}
