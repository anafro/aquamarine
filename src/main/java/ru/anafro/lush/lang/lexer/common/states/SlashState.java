package ru.anafro.lush.lang.lexer.common.states;

import ru.anafro.lush.lang.lexer.common.Lexer;
import ru.anafro.lush.lang.lexer.common.tokens.SlashToken;
import ru.anafro.lush.lang.lexer.common.tokens.StickToken;

public class SlashState extends IntermediateLexerState {
    public SlashState(Lexer lexer) {
        super(lexer);
    }

    @Override
    public void performAction() {
        lexer.addToken(new SlashToken());
    }
}
