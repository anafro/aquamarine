package ru.anafro.lush.cli;

import ru.anafro.lush.utils.collections.NamedList;

import java.util.List;

public class Commands extends NamedList<Command> {

    public Commands(Command... commands) {
        super(commands);
    }

    public void run(String commandName, NamedList<CommandArgument> arguments, List<String> flags) {
        ensureHas(commandName, suggestion -> "There's no such command called %s. Did you mean %s?".formatted(commandName, suggestion.getName()));

        Command command = get(commandName);
        command.ensureCommandPartsAreCorrect(arguments, flags);
        command.performAction(arguments, flags);
    }
}
