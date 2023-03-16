package ru.anafro.lush.utils.conditions;

import java.util.function.Supplier;

public class IfClause {
    protected boolean conditionInsideIf;

    public IfClause(boolean conditionInsideIf) {
        this.conditionInsideIf = conditionInsideIf;
    }

    public ElseClause<Void> then(Runnable body) {
        if(conditionInsideIf) {
            body.run();
        }

        return new ElseClause<>(conditionInsideIf);
    }

    public <T> ElseClause<T> thenReturn(Supplier<T> body) {
        if(conditionInsideIf) {
            return new ElseClause<>(true, body.get());
        }

        return new ElseClause<>(false, null);
    }

    public <T> ElseClause<T> thenReturn(T value) {
        return thenReturn(() -> value);
    }
}
