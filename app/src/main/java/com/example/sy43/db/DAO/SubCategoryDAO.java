package com.example.sy43.db.DAO;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.sy43.db.entity.SubCategory;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import androidx.room.Dao;

@Dao
public interface SubCategoryDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public ListenableFuture<Void> insert(SubCategory... subCategory);

    @Query("SELECT * FROM SubCategory WHERE SubCatID LIKE :SubCatID")
    public ListenableFuture<List<SubCategory>> findByID(int SubCatID);

    @Query("SELECT CurrentValue FROM SubCategory")
    public ListenableFuture<List<Float>> findValues();

    @Query("SELECT MaxValue FROM SubCategory")
    public ListenableFuture<List<Float>> findMaxValues();

    @Query("SELECT * FROM SubCategory WHERE Category LIKE :category")
    public ListenableFuture<List<SubCategory>> findByCategoryID(int category);

    @Query("DELETE FROM subcategory WHERE SubCatID = :id")
    public ListenableFuture<Void> delSubByID(int id);

    @Delete
    void deleteSubCategory(SubCategory Subcategory);
}
