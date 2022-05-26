package com.example.sy43.db.DAO;

import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Dao;

import com.example.sy43.Category;
import com.example.sy43.db.entity.Categorydb;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;


@Dao
public interface CategoryDAO{

    @Query("SELECT * FROM Categorydb")
    public ListenableFuture<List<Categorydb>> findAll();

    @Query("SELECT * FROM Categorydb WHERE CatID LIKE :CatID")
    public ListenableFuture<Categorydb> findByID(int CatID);

    @Query("SELECT CurrentValue FROM Categorydb")
    public ListenableFuture<List<Float>> findValue();

    @Query("SELECT MaxValue FROM Categorydb")
    public ListenableFuture<List<Float>> findMaxValue();

    @Delete
    public ListenableFuture<Integer>
    deleteCategory(List<Categorydb> category);
}