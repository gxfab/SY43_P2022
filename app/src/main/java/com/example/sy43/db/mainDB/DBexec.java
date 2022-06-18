package com.example.sy43.db.mainDB;

import java.util.concurrent.Executor;

public class DBexec implements Executor {

    private static volatile DBexec INSTANCE;
    private DBexec(){}

    /**
     * création d'un nouvel executor afin de ne pas passer dnas le mainThread
     * cela évite des freeze ou des "app is not responding" dans le cas de grosses requetes
     * on y retrouve a nouveau un pattern singleton car il ne sert à rien de créer plusieurs executor pour nos requetes
     *
     * @return l'instance de l'executor
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

    @Override
    public void execute(Runnable runnable) {
        runnable.run();
    }
}

