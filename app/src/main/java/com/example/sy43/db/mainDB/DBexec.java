package com.example.sy43.db.mainDB;

import java.util.concurrent.Executor;

public class DBexec implements Executor {

    private static volatile DBexec INSTANCE;
    private DBexec(){}


    /**
     * Renvoi l'instance de la DBExec
     * cette instance est utilisée pour ne pas passer par le main thread.
     *
     * @return l'instance de la DBExec
     */
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

    /**
     * Execute la runnable passée en paramètre

     * @param runnable
     * @return l'instance de la DBExec
     */
    @Override
    public void execute(Runnable runnable) {
        runnable.run();
    }
}

