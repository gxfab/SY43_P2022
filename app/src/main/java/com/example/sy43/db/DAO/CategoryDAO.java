package com.example.sy43.db.DAO;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Dao;
import androidx.room.Update;

import com.example.sy43.db.entity.Categorydb;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;


@Dao
public
interface CategoryDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public ListenableFuture<Void> insert(Categorydb... categories);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public ListenableFuture<Void> update(Categorydb... categories);


    @Query("SELECT * FROM Categorydb")
    public ListenableFuture<List<Categorydb>> findAll();

    @Query("SELECT * FROM Categorydb WHERE CatID LIKE :CatID")
    public ListenableFuture<Categorydb> findByID(int CatID);

    @Query("SELECT CurrentValue FROM Categorydb")
    public ListenableFuture<List<Float>> findValue();

    @Query("SELECT MaxValue FROM Categorydb")
    public ListenableFuture<List<Float>> findMaxValue();

    @Query("SELECT * FROM Categorydb WHERE isObjective = 1")
    public ListenableFuture<List<Categorydb>> findObjectives();

    @Query("SELECT * FROM Categorydb WHERE isObjective = 0")
    public ListenableFuture<List<Categorydb>> findCategories();

    @Delete
    public ListenableFuture<Integer>
    deleteCategory(List<Categorydb> category);
}
