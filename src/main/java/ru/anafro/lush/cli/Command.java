package ru.anafro.lush.cli;

import ru.anafro.lush.logger.ConsoleLogger;
import ru.anafro.lush.logger.Logger;
import ru.anafro.lush.utils.collections.Nameable;
import ru.anafro.lush.utils.collections.NamedList;

import java.util.List;

public abstract class Command implements Nameable {
    private final String name;
    private final String description;
    private final NamedList<CommandParameter> parameters;
    private final NamedList<CommandFlag> flags;
    protected final Logger logger = new ConsoleLogger();

    public Command(String name, String description, List<CommandParameter> parameters, List<CommandFlag> flags) {
        this.name = name;
        this.description = description;
        this.parameters = new NamedList<>(parameters);
        this.flags = new NamedList<>(flags);
    }

    public abstract void performAction(NamedList<CommandArgument> arguments, List<String> flags);

    public void ensureCommandPartsAreCorrect(NamedList<CommandArgument> arguments, List<String> flags) {
        for(var argument : arguments) {
            if(parameters.missing(argument.getName())) {
                throw new CommandException(CommandException.Type.NO_SUCH_PARAMETER, "Parameter with name '%s' does not exist. Did you mean %s?".formatted(
                        argument.getName(),
                        parameters.guess(argument.getName())
                ));
            }
        }

        for(var parameter : parameters) {
            if(arguments.missing(parameter.getName()) && parameter.isRequired()) {
                throw new CommandException(CommandException.Type.MISSING_REQUIRED_ARGUMENT, "The required parameter '%s' is not provided. Please, add it to the command like so: %s %s <value>".formatted(
                        parameter.getName(),
                        this.getName(),
                        parameter.getName()
                ));
            }
        }

        for(var flag : this.flags) {
            if(!flags.contains(flag.getName()) && flag.isRequired()) {
                throw new CommandException(CommandException.Type.MISSING_REQUIRED_FLAG, "The required flag '%s' is not provided. Please, add it like so: %s %s%s.".formatted(
                        flag.getName(),
                        this.getName(),
                        CommandFlag.PREFIX,
                        flag.getName()
                ));
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public NamedList<CommandFlag> getFlags() {
        return flags;
    }

    public NamedList<CommandParameter> getParameters() {
        return parameters;
    }

    public String getHelpingString() {
        StringBuilder helpingString = new StringBuilder();

        helpingString.append(name);
        helpingString.append(": ");
        helpingString.append(description);


        if(parameters.containsAnything()) {
            helpingString.append('\n');

            for (var parameter : parameters) {
                helpingString.append("\t");
                helpingString.append(parameter.getHelpingString());
            }
        }

        if(flags.containsAnything()) {
            helpingString.append('\n');

            for(var flag : flags) {
                helpingString.append("\t");
                helpingString.append(flag.getHelpingString());
            }
        }

        return helpingString.toString();
    }

    @Override
    public String toString() {
        return "Command{" +
                "name='" + name + '\'' +
                ", parameters=" + parameters +
                ", flags=" + flags +
                '}';
    }
}
