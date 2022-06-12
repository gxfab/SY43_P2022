package com.example.econo_misons.database;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.econo_misons.database.repositories.BudgetDataRepository;
import com.example.econo_misons.database.repositories.UserDataRepository;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final UserDataRepository userDataSource;

    private final BudgetDataRepository budgetDataSource;

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

        this.userDataSource = new UserDataRepository(database.userdao());

        this.budgetDataSource = new BudgetDataRepository(database.budgetdao());

        this.executor = Executors.newSingleThreadExecutor();

    }

    @Override
    @NotNull
    public <T extends ViewModel>  T create(Class<T> modelClass) {

        if (modelClass.isAssignableFrom(DBViewModel.class)) {

            return (T) new DBViewModel(userDataSource, budgetDataSource, executor);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }


}
