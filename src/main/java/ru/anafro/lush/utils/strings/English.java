package ru.anafro.lush.utils.strings;

import ru.anafro.lush.utils.conditions.CheckString;

import java.util.Objects;

public class English {
    private English() {
        throw new UnsupportedOperationException("English's constructor must not be called. Use static methods instead.");
    }

    public static String isOrAre(int countOfItems, boolean shorted) {
        if(Math.abs(countOfItems) < 2) {
            return shorted ? "s" : "is";
        }

        return shorted ? "re" : "are";
    }

    public static String isOrAre(int countOfItems) {
        return isOrAre(countOfItems, false);
    }

    public static String pluralize(String noun) {
        Objects.requireNonNull(noun, "English.pluralize(String): noun must not be null.");

        return CheckString
                .when(noun.toLowerCase())
                .endsWith("s", "ss", "sh", "ch", "x")
                .thenReturn(noun + "es")
                .orElseReturn(noun + "s");
    }

    public static String titleForManyItems(int countOfItems, String singularItemName, boolean excludeCount) {
        if(Math.abs(countOfItems) == 1) {
            return excludeCount ? singularItemName : "%d %s".formatted(countOfItems, singularItemName);
        } else {
            return excludeCount ? pluralize(singularItemName) : "%d %s".formatted(countOfItems, pluralize(singularItemName));
        }
    }

    public static String titleForManyItems(int countOfItems, String singularItemName) {
        return titleForManyItems(countOfItems, singularItemName, false);
    }

    public static String connectWithAnds(String... elements) {
        if(elements.length == 0) {
            return "";
        }

        if(elements.length == 1) {
            return elements[0];
        }

        TextBuffer buffer = new TextBuffer();

        for (int i = 0; i < elements.length; i++) {
            var element = elements[i];

            if(i == elements.length - 1) {
                buffer.append(element);
            } else if(i == elements.length - 2) {
                buffer.append(element);
                buffer.append(", and ");
            } else {
                buffer.append(element);
                buffer.append(", ");
            }
        }

        return buffer.toString();
    }
}
