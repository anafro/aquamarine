package ru.anafro.lush.utils.files.exceptions;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String filename) {
        super("The files '%s' does not exist. Did you provide a wrong name? Or deleted that file? Probably, it can be located in another directory.");
    }
}
