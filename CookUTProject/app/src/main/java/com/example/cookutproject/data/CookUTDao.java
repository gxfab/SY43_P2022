package com.example.cookutproject.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cookutproject.models.Semestre;

import java.util.List;

@Dao
public interface CookUTDao {
    @Insert
    void addSemestre(Semestre s);

    @Query("SELECT * FROM Semestre ORDER BY id ASC")
    LiveData<List<Semestre>> readAllSemestre();
}
