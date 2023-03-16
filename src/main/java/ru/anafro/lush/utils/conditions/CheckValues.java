package ru.anafro.lush.utils.conditions;

import java.util.List;

public class CheckValues<E> {
    private final List<E> values;

    @SafeVarargs
    protected CheckValues(E... values) {
        this.values = List.of(values);
    }

    @SafeVarargs
    public static <E> CheckValues<E> when(E... values) {
        return new CheckValues<>(values);
    }

    public boolean contains(E value) {
        return values.contains(value);
    }
}
