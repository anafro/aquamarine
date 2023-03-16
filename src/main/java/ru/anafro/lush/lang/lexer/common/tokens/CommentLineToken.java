package ru.anafro.lush.lang.lexer.common.tokens;

public class CommentLineToken extends Token {
    public CommentLineToken(String commentLine) {
        super("Comment line", commentLine);
    }

    public String getCommentLine() {
        return value;
    }

    @Override
    public String toCode() {
        return null;
    }
}
