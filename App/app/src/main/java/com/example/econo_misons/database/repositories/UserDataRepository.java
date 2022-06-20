package com.example.econo_misons.database.repositories;

import androidx.lifecycle.LiveData;

import com.example.econo_misons.database.dao.userDAO;
import com.example.econo_misons.database.models.User;

import java.util.List;

//The functions called by the DBViewModel on a new thread.
//The comments for the functions are in the DBViewModel file if they aren't here
public class UserDataRepository {

    private final userDAO userDao;

    public UserDataRepository(userDAO userDao) {this.userDao = userDao;}

    public LiveData<List<User>> getAllUsers() {return this.userDao.getAllUsers();}

    public long newUser(User user) {return this.userDao.newUser(user);}

    public void updateUser(User user) {  this.userDao.updateUser(user);}

    public void deleteUser(User user) {  this.userDao.deleteUser(user);}

    public LiveData<List<User>> getUser(int id) {
        return this.userDao.getUser(id);
    }
}
