package com.example.econo_misons.database.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Model of the User Table
@Entity(tableName = "User")
public class User {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID")
    public int id;

    @ColumnInfo(name = "NAME_USER")
    public String username;

    public User(String username){
        this.username = username;
    }

    public String toString(){
        return "ID: " + this.id + ", name: " + this.username;
    }
}
