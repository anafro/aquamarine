package ru.anafro.lush.utils.classes;

public class Classes {
    private Classes() {
        throw new UnsupportedOperationException("Classes' constructor must not be called. Use static methods instead.");
    }

    public static String getReadableName(Class<?> clazz) {
        StringBuilder readableName = new StringBuilder();

        char[] classNameChars = clazz.getSimpleName().toCharArray();

        for (int index = 0; index < classNameChars.length; index++) {
            char character = classNameChars[index];

            if (Character.isUpperCase(character) && index != 0) {
                readableName.append(' ');
            }

            if(index == 0) {
                readableName.append(character);
            } else {
                readableName.append(Character.toLowerCase(character));
            }
        }

        return readableName.toString();
    }
}
