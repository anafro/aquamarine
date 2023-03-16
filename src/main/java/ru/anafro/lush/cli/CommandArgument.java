package ru.anafro.lush.cli;

import ru.anafro.lush.utils.collections.Nameable;

public class CommandArgument implements Nameable {
    private final String name;
    private final String value;

    public CommandArgument(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }
}
