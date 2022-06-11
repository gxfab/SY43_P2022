package com.example.econo_misons.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAO {
    @Insert
    void newUser(User user);
    @Update
    void updateUser(User user);
    @Delete
    void deleteUser(User user);
    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUsers();
}
