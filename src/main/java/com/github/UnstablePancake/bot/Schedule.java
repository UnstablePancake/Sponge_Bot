package com.github.UnstablePancake.bot;

import com.github.UnstablePancake.modules.Utility.Ansi;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.*;

public class Schedule {

    public static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void updateUserData(){
        final Runnable userData = () -> {
            try {
                System.out.println("[Data] Writing userDat.json");
                JSONHandler.createJSON();
            } catch (IOException e) {
                System.out.println(Ansi.color("[Data] Error: userData.json could not be updated", Ansi.GREEN));
            }
        };

        final ScheduledFuture<?> updateHandle =
                scheduler.scheduleAtFixedRate(userData, 10, 10, MINUTES);
        scheduler.schedule((Runnable) () -> updateHandle.cancel(true), Long.MAX_VALUE, SECONDS);
    }
}
