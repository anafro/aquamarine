package ru.anafro.lush.utils.files;

import java.util.Optional;

public class Content {
    private Content() {
        throw new UnsupportedOperationException("Content's constructor must never be called. Use static methods instead.");
    }

    public static Optional<String> of(String fileName) {
        if(Files.doesntExist(fileName)) {
            return Optional.empty();
        }

        return Optional.of(Files.read(fileName));
    }
}
