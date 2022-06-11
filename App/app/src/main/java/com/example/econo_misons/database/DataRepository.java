package com.example.econo_misons.database;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DataRepository {

    private final DAO dao;

    public DataRepository(DAO dao) {this.dao = dao;}

    public LiveData<List<User>> getAllUsers() {return this.dao.getAllUsers();}

    public void newUser(User user) {this.dao.newUser(user);}

    public void updateUser(User user) {  this.dao.updateUser(user);}

    public void deleteUser(User user) {  this.dao.deleteUser(user);}
}
