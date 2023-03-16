package ru.anafro.lush.utils.collections;

import ru.anafro.lush.utils.integers.Ints;

public class Arrays {
    private Arrays() {
        throw new UnsupportedOperationException("Arrays' class constructor must not be called. Use static methods instead.");
    }

    public static <T> int indexLimit(int index, T[] array) {
        return Ints.limit(index, 0, array.length - 1);
    }
}
