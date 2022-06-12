package com.example.econo_misons.database.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.econo_misons.database.dao.userDAO;
import com.example.econo_misons.database.models.User;

import java.util.List;

public class UserDataRepository {

    private final userDAO userDao;

    public UserDataRepository(userDAO userDao) {this.userDao = userDao;}

    public LiveData<List<User>> getAllUsers() {return this.userDao.getAllUsers();}

    public long newUser(User user) {return this.userDao.newUser(user);}

    public void updateUser(User user) {  this.userDao.updateUser(user);}

    public void deleteUser(User user) {  this.userDao.deleteUser(user);}

    public LiveData<List<User>> getUser(int id) {
        Log.d("DB", "id: " + id);
        LiveData<List<User>> user = this.userDao.getUser(id);
        Log.d("DB", "getUser: " + user.getValue());
        return user;
    }
}
