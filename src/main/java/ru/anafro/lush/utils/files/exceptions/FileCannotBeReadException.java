package ru.anafro.lush.utils.files.exceptions;

import ru.anafro.lush.utils.classes.Classes;

public class FileCannotBeReadException extends RuntimeException {
    public FileCannotBeReadException(String fileName, Throwable cause) {
        super("The file '%s' cannot be read, because of %s: %s".formatted(
                fileName,
                Classes.getReadableName(cause.getClass()),
                cause.getMessage()
        ));
        initCause(cause);
    }
}
