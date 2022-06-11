package com.example.econo_misons.database;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final DataRepository dataSource;

    private final Executor executor;

    private static ViewModelFactory factory;

    public static ViewModelFactory getInstance(Context context){
        if (factory == null){
            synchronized (ViewModelFactory.class){
                if (factory == null){
                    factory = new ViewModelFactory(context);
                }
            }
        }
        return factory;
    }

    private ViewModelFactory(Context context) {

        MoneyDB database = MoneyDB.getInstance(context);

        this.dataSource = new DataRepository(database.dao());

        this.executor = Executors.newSingleThreadExecutor();

    }

    @Override
    @NotNull
    public <T extends ViewModel>  T create(Class<T> modelClass) {

        if (modelClass.isAssignableFrom(DBViewModel.class)) {

            return (T) new DBViewModel(dataSource, executor);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }


}
