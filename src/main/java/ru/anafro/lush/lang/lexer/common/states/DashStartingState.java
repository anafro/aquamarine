package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.DashToken;
import ru.anafro.lush.lang.lexer.common.tokens.GreaterToken;

public class DashStartingState extends LexerState {
    public DashStartingState(Lexer lexer) {
        super(lexer, true);
    }

    @Override
    public void handleNextCharacter(char character) {
        if(character == GreaterToken.CHARACTER) {
            lexer.switchStateKeepingCurrentCharacter(new ExtractState(lexer));
            return;
        }

        lexer.addToken(new DashToken());
        lexer.switchStateKeepingCurrentCharacter(new TokenGuessState(lexer));
    }
}
