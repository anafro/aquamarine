package ru.anafro.lush.cli;

import ru.anafro.lush.api.Lush;
import ru.anafro.lush.utils.collections.NamedList;

import java.util.List;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", "Shows the help info about Lush", List.of(new CommandParameter("command", "Shows help about a specific command", false)), List.of());
    }

    private void printGeneralHelp() {
        System.out.println("Available commands:");

        for(var command : Lush.commands) {
            System.out.println(command.getHelpingString());
            System.out.println();
        }
    }

    private void printHelpForCommand(String commandName) {
        if(Lush.commands.missing(commandName)) {
            System.out.printf("No such command named %s. Did you mean '%s'?".formatted(
                    commandName,
                    Lush.commands.guess(commandName).getName()
            ));
        } else {
            System.out.println(Lush.commands.get(commandName).getHelpingString());
        }
    }

    @Override
    public void performAction(NamedList<CommandArgument> arguments, List<String> flags) {


        if(arguments.has("command")) {
            printHelpForCommand(arguments.get("command").getValue());
        } else {
            printGeneralHelp();
        }
    }
}
