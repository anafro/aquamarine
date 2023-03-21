package ru.anafro.lush.utils.strings;

import java.util.HashMap;
import java.util.Map;

public class SpecialChars {
    private static final Map<String, String> CHARACTERS = new HashMap<>() {{
        put("\n", "\\n");
        put("\t", "\\t");
        put("\b", "\\b");
        put("\r", "\\r");
        put("\f", "\\f");
        put("\\", "\\\\");
        put("'", "\\'");
        put("\"", "\\\"");
    }};

    private static final Map<String, String> UNICODE = new HashMap<>() {{
        put("\n", "↩");
        put("\t", "⭾");
        put("\b", "⌫");
        put("\r", "⇐");
        put("\f", "ƒ");
        put("\\", "\\");
        put("'", "'");
        put("\"", "\"");
    }};

    private SpecialChars() {
        throw new UnsupportedOperationException("SpecialChars' class constructor must not be called. Use static methods instead.");
    }

    public static boolean isSpecialCharSymbol(char character) {
        return CHARACTERS.values().stream().anyMatch(e -> e.endsWith(String.valueOf(character)));
    }

    private static String replaceWithDictionary(Map<String, String> dictionary, String string) {
        for(var specialCharPair : dictionary.entrySet()) {
            var specialChar = specialCharPair.getKey();
            var specialCharWithBackslash = specialCharPair.getValue();

            string = string.replace(specialChar, specialCharWithBackslash);
        }

        return string;
    }

    public static String replaceWithBackslashes(String string) {
        return replaceWithDictionary(CHARACTERS, string);
    }

    public static String replaceWithUnicode(String string) {
        return replaceWithDictionary(UNICODE, string);
    }

    public static String backslash(char character) {
        var characterAsString = String.valueOf(character);

        return CHARACTERS.getOrDefault(characterAsString, characterAsString);
    }
}
