package com.example.sy43.db.mainDB;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class DBexec implements Executor {

    private static volatile DBexec INSTANCE;
    private DBexec(){}

    public static DBexec getExecutor(){
        if (INSTANCE == null){
            synchronized(DBexec.class){
                if (INSTANCE == null){
                    INSTANCE = new DBexec();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void execute(Runnable runnable) {
        runnable.run();
    }
}

