package ru.anafro.lush.cli;

import ru.anafro.lush.utils.collections.Nameable;

public abstract class CommandPart implements Nameable {
    private final String name;
    private final String description;
    private final boolean required;

    public CommandPart(String name, String description, boolean required) {
        this.name = name;
        this.description = description;
        this.required = required;
    }

    public CommandPart(String name, String description) {
        this(name, description, true);
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isRequired() {
        return required;
    }

    public String getHelpingString() {
        StringBuilder helpingString = new StringBuilder();

        helpingString.append(name);
        helpingString.append(": ");

        if(required) {
            helpingString.append("[required]");
        } else {
            helpingString.append("(optional)");
        }

        helpingString.append(" ");
        helpingString.append(description);

        return helpingString.toString();
    }
}
