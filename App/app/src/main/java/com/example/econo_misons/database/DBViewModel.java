package com.example.econo_misons.database;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.Executor;

public class DBViewModel extends ViewModel {

    private final DataRepository dataSource;

    private final Executor executor;

    @Nullable
    private LiveData<User> currentUser;

    public  DBViewModel(DataRepository dataSource, Executor executor){
        this.dataSource = dataSource;
        this.executor = executor;
    }

    public LiveData<List<User>> getAllUsers() {return dataSource.getAllUsers();}

    public void newUser(User user) {
        executor.execute(() -> dataSource.newUser(user));
    }

    public void updateUser(User user) {
        executor.execute(() -> dataSource.updateUser(user));
    }

    public void deleteUser(User user) {
        executor.execute(() -> dataSource.deleteUser(user));
    }
}
