package ru.anafro.lush.utils.strings;

import java.util.ArrayList;

import static ru.anafro.lush.utils.integers.Ints.ensurePositive;

public class Strings {
    public static final String ELLIPSIS = "…";

    private Strings() {
        throw new UnsupportedOperationException("Strings' class constructor must not be called. Use static methods instead.");
    }

    public static String getCharacterDescription(String string) {
        ArrayList<String> characterDescriptions = new ArrayList<>();

        for(var character : string.toCharArray()) {
            characterDescriptions.add("(%s '%s': code %d)".formatted(
                    Character.getName(character),
                    SpecialChars.backslash(character),
                    Character.getNumericValue(character)
            ));
        }

        return String.join(", ", characterDescriptions);
    }

    public static String paddingRight(String string, int padding) {
        ensurePositive(padding, "Strings.paddingRight: padding must be positive, not %d.".formatted(padding));

        if(string.length() >= padding) {
            return string.substring(0, padding - 1) + ELLIPSIS;
        }

        return ("%-" + padding + "s").formatted(string);
    }

    public static String keepIf(String string, boolean condition) {
        return condition ? string : "";
    }
}
