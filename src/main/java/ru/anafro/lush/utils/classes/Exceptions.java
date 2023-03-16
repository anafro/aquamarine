package ru.anafro.lush.utils.classes;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Exceptions {
    private Exceptions() {
        throw new UnsupportedOperationException("Exceptions' constructor must not be called. Use static methods instead.");
    }

    public static String getStackTraceAsString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);

        return stringWriter.toString();
    }
}
