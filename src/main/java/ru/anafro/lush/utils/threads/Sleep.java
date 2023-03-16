package ru.anafro.lush.utils.threads;

import java.util.concurrent.TimeUnit;

public class Sleep {
    public static void during(long count, TimeUnit unit) {
        try {
            unit.sleep(count);
        } catch(InterruptedException ignored) {
            //
        }
    }
}
