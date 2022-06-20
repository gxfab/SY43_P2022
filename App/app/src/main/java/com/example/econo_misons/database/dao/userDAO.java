package com.example.econo_misons.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.econo_misons.database.models.User;

import java.util.List;

//Data access object with all the SQL Query for the user functions
@Dao
public interface userDAO {
    @Insert
    long newUser(User user);
    @Update
    void updateUser(User user);
    @Delete
    void deleteUser(User user);
    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUsers();
    @Query("SELECT * FROM User WHERE ID = :id")
    LiveData<List<User>> getUser(int id);
}
