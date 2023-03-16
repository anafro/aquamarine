package ru.anafro.lush.cli;

import ru.anafro.lush.api.Lush;

public class CommandParameterNotFoundException extends RuntimeException {
    public CommandParameterNotFoundException(String parameterName) {
        super(("There is no such parameter called %s. Did you make a typo?" +
               "Please, type '%s help' to get the list of commands and theirs arguments").formatted(parameterName, Lush.NAME.toLowerCase()));
    }
}
