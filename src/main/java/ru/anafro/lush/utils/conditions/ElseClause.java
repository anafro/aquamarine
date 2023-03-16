package ru.anafro.lush.utils.conditions;

import java.util.function.Supplier;

public class ElseClause<T> {
    protected boolean conditionInsideIf;
    protected T valueReturnedFromIfClause;

    public ElseClause(boolean conditionInsideIf) {
        this.conditionInsideIf = conditionInsideIf;
    }

    public ElseClause(boolean conditionInsideIf, T valueReturnedFromIfClause) {
        this.conditionInsideIf = conditionInsideIf;
        this.valueReturnedFromIfClause = valueReturnedFromIfClause;
    }

    public void orElse(Runnable body) {
        if(!conditionInsideIf) {
            body.run();
        }
    }

    public T orElseReturn(Supplier<T> body) {
        if(conditionInsideIf) {
            return valueReturnedFromIfClause;
        } else {
            return body.get();
        }
    }

    public T orElseReturn(T value) {
        return orElseReturn(() -> value);
    }
}
