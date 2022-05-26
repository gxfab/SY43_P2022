package com.example.sy43.db.DAO;


import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.sy43.Category;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import androidx.room.Dao;

@Dao
public interface TransactionDAO{

    @Query("SELECT * FROM `transaction` WHERE TransactionID LIKE :transactionID")
    public ListenableFuture<List<com.example.sy43.db.entity.Transaction>> findByID(int transactionID);

    @Query("SELECT Value FROM `transaction`")
    public ListenableFuture<List<Float>> findValues();

    @Query("SELECT * FROM `transaction` WHERE Category LIKE :category")
    public ListenableFuture<List<com.example.sy43.db.entity.Transaction>> findByCategorie(int category);

    @Query("SELECT * FROM `transaction` WHERE SubCategory LIKE :subcategory")
    public ListenableFuture<List<com.example.sy43.db.entity.Transaction>> findBySubCategorie(int subcategory);

    @Delete
    void deleteTransaction(com.example.sy43.db.entity.Transaction transaction);
}