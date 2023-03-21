package ru.anafro.lush.lang.lexer.common;

import ru.anafro.lush.api.Lush;
import ru.anafro.lush.lang.lexer.common.events.LexerEventListener;
import ru.anafro.lush.lang.lexer.common.exceptions.LexerProblem;
import ru.anafro.lush.lang.lexer.common.exceptions.LexerProblemType;
import ru.anafro.lush.lang.lexer.common.states.LexerState;
import ru.anafro.lush.lang.lexer.common.states.TokenGuessState;
import ru.anafro.lush.lang.lexer.common.tokens.Token;
import ru.anafro.lush.logger.ConsoleLogger;
import ru.anafro.lush.logger.Logger;
import ru.anafro.lush.utils.console.Console;
import ru.anafro.lush.utils.console.ConsoleColors;
import ru.anafro.lush.utils.strings.Code;
import ru.anafro.lush.utils.strings.English;
import ru.anafro.lush.utils.strings.Strings;
import ru.anafro.lush.utils.strings.TextBuffer;

import java.util.*;
import java.util.function.Consumer;

public class Lexer implements Iterator<Token> {
    private final LexerMode mode;
    private final Code code;
    private LexerState state;
    private final TextBuffer buffer = new TextBuffer();

    private int currentCharacterIndex = 0;
    private final List<Character> emptyCharacters = List.of(' ', '\t', '\r');
    private final ArrayList<Token> tokens = new ArrayList<>();
    private final ArrayList<LexerProblem> problems = new ArrayList<>();
    private final ArrayList<LexerEventListener> listeners = new ArrayList<>();
    private final Logger logger = new ConsoleLogger();
    private boolean statePushedNewToken = false;

    public Lexer(String code, LexerMode mode) {
        this.code = new Code(prepareCode(code), true);
        this.state = new TokenGuessState(this);
        this.mode = mode;
    }

    private Lexer(String code) {
        this(code, LexerMode.STRICT);
    }


    public void lex() {
        resetCharacterIndex();
        clearProblems();

        while(hasNext()) {
            next();
        }

        state.afterCodeLexing();
        addToken(new CodeEndsToken());

        forEachEventListener(LexerEventListener::onLexingCompletedEvent);
        printProblems();
    }

    @Override
    public boolean hasNext() {
        return currentCharacterIndex < code.length() || (!tokens.isEmpty() && lastToken().getName().equals("Code ends"));
    }

    @Override
    public Token next() {
        while(hasNext()) {
            char currentCharacter = code.charAt(currentCharacterIndex);

            if(isCharEmpty(currentCharacter) && state.wantsEmptyCharsToBeIgnored()) {
                forEachEventListener(LexerEventListener::onIgnoredCharacterDetectionEvent);
                moveToNextCharacter();
                continue;
            }

            forEachEventListener(LexerEventListener::onHandlingCharacterEvent);
            state.handleNextCharacter(currentCharacter);
            moveToNextCharacter();

            if(statePushedNewToken) {
                break;
            }
        }

        statePushedNewToken = false;
        return lastToken();
    }

    protected Token lastToken() {
        return tokens.get(tokens.size() - 1);
    }

    public void addProblem(LexerProblem problem) {
        problems.add(problem);

        if(isStrict() || problem.getType() == LexerProblemType.ERROR) {
            haltLexing();
        }
    }
    public void addError(String message) {
        addProblem(new LexerProblem(this, LexerProblemType.ERROR, message));
    }

    public void addWarning(String message) {
        addProblem(new LexerProblem(this, LexerProblemType.WARNING, message));
    }

    public void addCosmeticProblem(String message) {
        addProblem(new LexerProblem(this, LexerProblemType.COSMETIC, message));
    }

    private void haltLexing() {
        Console.breakLine();
        logger.error(ConsoleColors.red("The lexing process has been stopped, because we ran into a %s, which is inappropriate for the %s lexing mode".formatted(
                English.titleForManyItems(problems.size(), "problem", true),
                mode.toString().toLowerCase()
        )));

        logger.error(ConsoleColors.red("The lexer state that found the problem: %s.".formatted(state.getName().toLowerCase())));

        printProblems();
        Lush.exit();
    }

    private void printProblems() {
        if(problems.isEmpty()) {
            return;
        }

        problems.sort(Collections.reverseOrder(Comparator.comparing(e -> e.getType().ordinal())));

        for(var problem : problems) {
            logger.message("");
            logger.message(problem.toString());
        }

        var mostSevereProblemType = problems.stream().map(LexerProblem::getType).max(Comparator.comparing(Enum::ordinal)).stream().findFirst().orElse(LexerProblemType.ERROR);

        logger.message("");
        logger.message(ConsoleColors.colorize("%s in total.".formatted(
                English.titleForManyItems(problems.size(), "problem")
        ), mostSevereProblemType.getConsoleColor()));

        logger.message(getProblemsSummary());
    }

    private String getProblemsSummary() {
        if(problems.isEmpty()) {
            return ConsoleColors.green("No problems have been detected during the lexing.");
        }

        var errors = getErrorProblems();
        var warnings = getWarningProblems();
        var cosmetics = getCosmeticProblems();

        return ConsoleColors.red(English.connectWithAnds(
                English.titleForManyItems(errors.size(), "error"),
                English.titleForManyItems(warnings.size(), "warning"),
                English.titleForManyItems(cosmetics.size(), "cosmetic")
        ) + ".");
    }

    public List<LexerProblem> getProblemsByType(LexerProblemType type) {
        return problems.stream().filter(problem -> problem.getType() == type).toList();
    }

    public List<LexerProblem> getErrorProblems() {
        return getProblemsByType(LexerProblemType.ERROR);
    }

    public List<LexerProblem> getWarningProblems() {
        return getProblemsByType(LexerProblemType.WARNING);
    }

    public List<LexerProblem> getCosmeticProblems() {
        return getProblemsByType(LexerProblemType.COSMETIC);
    }

    public void addToken(Token token) {
        forEachEventListener(handler -> handler.onAddingTokenEvent(token));
        tokens.add(token);

        statePushedNewToken = true;
    }

    public void moveToNextCharacter() {
        currentCharacterIndex += 1;
    }

    public boolean isCharEmpty(char character) {
        return emptyCharacters.contains(character);
    }

    public LexerState getState() {
        return state;
    }

    public LexerMode getMode() {
        return mode;
    }

    public boolean isForgiving() {
        return mode.equals(LexerMode.FORGIVING);
    }

    public boolean isStrict() {
        return mode.equals(LexerMode.STRICT);
    }

    public int getCurrentCharacterIndex() {
        return currentCharacterIndex;
    }

    public char getCurrentCharacter() {
        return code.charAt(currentCharacterIndex);
    }

    protected void clearProblems() {
        problems.clear();
    }

    protected void resetCharacterIndex() {
        this.currentCharacterIndex = 0;
    }

    public ArrayList<LexerProblem> getProblems() {
        return problems;
    }

    public Code getCode() {
        return code;
    }

    public void switchStateDroppingCurrentCharacter(LexerState newState) {
        switchState(newState);
    }

    public void switchStateKeepingCurrentCharacter(LexerState newState) {
        switchState(newState, LexerSwitchFlag.KEEP_CURRENT_CHARACTER);
    }

    public void switchState(LexerState newState, LexerSwitchFlag... flags) {
        Objects.requireNonNull(newState, "Lexer.switchState(LexerState, LexerSwitchFlag...): new state must not be null.");


        if(! Arrays.asList(flags).contains(LexerSwitchFlag.KEEP_BUFFER_CONTENT)) {
            clearBuffer();
        }

        if(Arrays.asList(flags).contains(LexerSwitchFlag.KEEP_CURRENT_CHARACTER)) {
            moveToPreviousCharacter();
        }

        this.state.beforeBeingSwitched();
        this.state = newState;
    }

    private void moveToPreviousCharacter() {
        currentCharacterIndex -= 1;
    }

    public List<Character> getEmptyCharacters() {
        return emptyCharacters;
    }

    public TextBuffer getBuffer() {
        return buffer;
    }

    public void appendToBuffer(char character) {
        buffer.append(character);
    }

    public void appendCurrentCharacterToBuffer() {
        appendToBuffer(getCurrentCharacter());
    }

    public String getBufferContent() {
        return buffer.toString();
    }

    public String getBufferContentAndClear() {
        return buffer.getContentAndClear();
    }

    public void clearBuffer() {
        buffer.clear();
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public ArrayList<LexerEventListener> getListeners() {
        return listeners;
    }

    public void addEventListener(LexerEventListener listener) {
        listeners.add(listener);
    }

    private void forEachEventListener(Consumer<LexerEventListener> action) {
        listeners.forEach(action);
    }

    private static String prepareCode(String code) {
        code = code.replace(System.lineSeparator(), "\n");
        code += Strings.keepIf("\n", !code.endsWith("\n"));

        return code;
    }
}
