package cc.worldmandia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Utils {

    static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public static void scheduleWithFixedDelay(Runnable task, long initialDelay, long delay, TimeUnit unit) {
        executor.scheduleWithFixedDelay(task, initialDelay, delay, unit);
    }

    public static Logger getLogger(Object loggerClass) {
        return LoggerFactory.getLogger(loggerClass.getClass());
    }

    public static String getRandomCityFromDataBase(String input, List<String> cities) {
        // TODO Logic

        return "";
    }
}
