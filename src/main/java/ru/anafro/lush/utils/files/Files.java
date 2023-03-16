package ru.anafro.lush.utils.files;

import ru.anafro.lush.utils.files.exceptions.FileCannotBeReadException;
import ru.anafro.lush.utils.files.exceptions.FileNotFoundException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class Files {
    private Files() {
        throw new UnsupportedOperationException("Files' constructor must never be called. Use static methods instead.");
    }

    public static boolean exists(String filename) {
        var file = new File(filename);

        return file.exists() && !file.isDirectory();
    }

    public static boolean doesntExist(String fileName) {
        return !exists(fileName);
    }

    public static void ensureExists(String fileName) {
        if(doesntExist(fileName)) {
            throw new FileNotFoundException(fileName);
        }
    }

    public static String read(String fileName) {
        ensureExists(fileName);

        try {
            return java.nio.file.Files.readString(Path.of(fileName));
        } catch (IOException exception) {
            throw new FileCannotBeReadException(fileName, exception);
        }
    }
}
