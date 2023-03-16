package ru.anafro.lush.utils.conditions;

import java.util.Arrays;

public class CheckString {
    private final String string;

    private CheckString(String string) {
        this.string = string;
    }

    public static CheckString when(String string) {
        return new CheckString(string);
    }

    public IfClause endsWith(String... endings) {
        var endsWith = Arrays.stream(endings).map(string::endsWith).reduce(false, Boolean::logicalOr);
        return new IfClause(endsWith);
    }
}
