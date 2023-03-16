package ru.anafro.lush.cli;

import ru.anafro.lush.api.Lush;
import ru.anafro.lush.utils.collections.NamedList;

import java.util.ArrayList;
import java.util.List;

public class CommandArgsParser {
    public final String[] args;
    private final String commandName;
    public final NamedList<CommandArgument> arguments = new NamedList<>();
    public final List<String> flags = new ArrayList<>();

    public CommandArgsParser(String[] args) {
        if(args.length == 0) {
            throw new CommandArgsParsingException("Please, provide the command name like so: %s <command-name>.".formatted(
                    Lush.NAME.toLowerCase()
            ));
        }

        this.commandName = args[0];
        this.args = args;
    }

    public void parse() {
        String lastArgumentName = null;

        for (int i = 1; i < args.length; i++) {
            String arg = args[i];

            if (lastArgumentName != null) {
                arguments.add(new CommandArgument(lastArgumentName, arg));
                lastArgumentName = null;
            } else if (arg.startsWith(CommandFlag.PREFIX)) {
                flags.add(arg.substring(CommandFlag.PREFIX.length()));
            } else {
                lastArgumentName = arg;
            }
        }
    }

    public List<String> getFlags() {
        return flags;
    }

    public NamedList<CommandArgument> getArguments() {
        return arguments;
    }

    public String[] getCommandLineArgs() {
        return args;
    }

    public String getCommandName() {
        return commandName;
    }
}
