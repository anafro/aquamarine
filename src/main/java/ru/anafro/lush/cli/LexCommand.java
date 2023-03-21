package ru.anafro.lush.cli;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.LexerMode;
import ru.anafro.lush.lang.lexer.common.events.LexerEventListener;
import ru.anafro.lush.lang.lexer.common.states.LexerState;
import ru.anafro.lush.lang.lexer.common.tokens.Token;
import ru.anafro.lush.utils.collections.NamedList;
import ru.anafro.lush.utils.console.Console;
import ru.anafro.lush.utils.console.ConsoleColors;
import ru.anafro.lush.utils.files.Content;
import ru.anafro.lush.utils.strings.English;
import ru.anafro.lush.utils.strings.SpecialChars;
import ru.anafro.lush.utils.strings.Strings;
import ru.anafro.lush.utils.strings.TextBuffer;
import ru.anafro.lush.utils.threads.Sleep;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class LexCommand extends Command {
    private static final int DELAY_BETWEEN_LEXER_VISUALIZER_PRINTS_MILLISECONDS = 0;

    public LexCommand() {
        super("lex", "Performs the lexing of a .lush-file", List.of(new CommandParameter("file", "The file that should be lexed.", true)), List.of(new CommandFlag("forgiving", "Turns on the forgiving mode. " + LexerMode.FORGIVING.getDescription()), new CommandFlag("visualize", "Shows the process of lexing. Useful in lexer debugging")));
    }

    @Override
    public void performAction(NamedList<CommandArgument> arguments, List<String> flags) {
        var filename = arguments.get("file").getValue();
        var code = Content.of(filename);

        if(code.isEmpty()) {
            throw new CommandException("Cannot read file '%s'.".formatted(filename));
        }

        var lexer = new Lexer(code.get(), flags.contains("forgiving") ? LexerMode.FORGIVING : LexerMode.STRICT);

        logger.info("Running the lexer in %s mode. %s".formatted(lexer.getMode().toString().toLowerCase(), lexer.getMode().getDescription()));
        Console.breakLine();

        if(flags.contains("visualize")) {
            lexer.addEventListener(new LexerConsoleVisualizerEventListener(lexer));
        }

        lexer.lex();

        printCode(lexer);
        printTokens(lexer.getTokens());
    }

    private void printCode(Lexer lexer) {
        Console.whiteLine(lexer.getCode().snippet());
    }

    private void printTokens(ArrayList<Token> tokens) {
        if(tokens.isEmpty()) {
            logger.info("No tokens found in this file. Probably, the file is empty.");
            return;
        }

        logger.info("There'%s %s found in total:".formatted(
                English.isOrAre(tokens.size(), true),
                English.titleForManyItems(tokens.size(), "token")
        ));

        for(var token : tokens) {
            logger.info("  • " + token);
        }
    }

    private static class LexerConsoleVisualizerEventListener extends LexerEventListener {

        private LexerConsoleVisualizerEventListener(Lexer lexer) {
            super(lexer);
        }

        @Override
        public void onHandlingCharacterEvent() {
            printMessage(lexer.getState().getName() + ", buffer = " + ConsoleColors.cyan("'" + SpecialChars.replaceWithBackslashes(lexer.getBufferContent()) + "'"), ConsoleColors.CYAN);
        }

        @Override
        public void onIgnoredCharacterDetectionEvent() {
            printMessage(ConsoleColors.yellow("Character '%s' is ignored".formatted(
                    Character.getName(lexer.getCurrentCharacter())
            )), ConsoleColors.YELLOW);
        }

        @Override
        public void onAddingTokenEvent(Token token) {
            printMessage(ConsoleColors.green("Added '%s' = '%s'".formatted(
                    token.getName(),
                    SpecialChars.replaceWithBackslashes(token.getValue())
            )), ConsoleColors.GREEN);
        }

        @Override
        public void onLexingCompletedEvent() {
            Console.println("\n".repeat(5));
        }

        @Override
        public void onStateSwitchEvent(LexerState oldState, LexerState newState) {
            printMessage(ConsoleColors.white(oldState.getName()) + " -> " + ConsoleColors.cyan(newState.getName()), ConsoleColors.CYAN);
        }

        private void printMessage(String title, String highlightedCharConsoleColor) {
            Sleep.during(DELAY_BETWEEN_LEXER_VISUALIZER_PRINTS_MILLISECONDS, MILLISECONDS);

            var message = new TextBuffer();

            message.append(Strings.paddingRight("[%s]: (%d:%d | %d)".formatted(
                    title,
                    lexer.getCode().getLineIndexByCharIndex(lexer.getCurrentCharacterIndex()) + 1,
                    lexer.getCode().getLineRelativeIndexByCharIndex(lexer.getCurrentCharacterIndex()) + 1,
                    lexer.getCurrentCharacterIndex()
            ), 70));

            message.append(lexer.getCode().charHighlightedSnippet(lexer.getCurrentCharacterIndex(), 1, highlightedCharConsoleColor));
            message.append(" ".repeat(30));
            message.appendLine();

            Console.print(message);
        }
    }
}
