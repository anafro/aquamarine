package ru.anafro.lush.cli;

public class CommandFlag extends CommandPart {
    public static final String PREFIX = "--";

    public CommandFlag(String name, String description, boolean required) {
        super(name, description, required);
    }

    public CommandFlag(String name, String description) {
        super(name, description, false);
    }

    @Override
    public String getHelpingString() {
        return PREFIX + super.getHelpingString();
    }
}
