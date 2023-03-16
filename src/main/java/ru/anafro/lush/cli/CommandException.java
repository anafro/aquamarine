package ru.anafro.lush.cli;

public class CommandException extends RuntimeException {
    public enum Type {
        NO_SUCH_PARAMETER("There is no such parameter"),
        MISSING_REQUIRED_FLAG("The required flag is missed"),
        MISSING_REQUIRED_ARGUMENT("The required argument is missed"),
        WRONG_ARGUMENT_FORMAT("The argument is malformed"),
        COMMAND_SPECIFIC("Unfortunately, there is an error occurred");

        private final String tag;

        Type(String tag) {
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }
    }

    private final Type type;

    public CommandException(Type type, String message) {
        super(type.getTag() + ". " + message);
        this.type = type;
    }

    public CommandException(String message) {
        this(Type.COMMAND_SPECIFIC, message);
    }

    public Type getType() {
        return type;
    }
}
