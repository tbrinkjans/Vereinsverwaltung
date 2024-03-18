package application.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Time {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static String getTime() {
        return LocalTime.now().format(FORMATTER);
    }

}
