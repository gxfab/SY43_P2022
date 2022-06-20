package com.example.econo_misons.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.econo_misons.database.models.Envelope;
import com.example.econo_misons.database.models.PrevisionalBudget;

import java.util.List;

//Data access object with all the SQL Query for the prevBudget and envelopes functions
@Dao
public interface prevBudgetDAO {
    @Insert
    void addPrevBudget(PrevisionalBudget prevBud);
    @Delete
    void deletePrevBudget(PrevisionalBudget prevBud);
    @Query("SELECT * FROM PrevisionalBudget")
    LiveData<List<PrevisionalBudget>> getAllPrevBudgets();
    @Query("SELECT PrevisionalBudget.BUD_ID,PrevisionalBudget.YEAR_MONTH FROM PrevisionalBudget " +
            "INNER JOIN Budget_User " +
            "ON PrevisionalBudget.BUD_ID = Budget_User.BUD_ID " +
            "WHERE USER_ID = :userID")
    LiveData<List<PrevisionalBudget>> getUserPrevBudgets(int userID);
    @Query("SELECT * FROM PrevisionalBudget WHERE BUD_ID = :budgetID")
    LiveData<List<PrevisionalBudget>> getPrevBudgetsByBudget(int budgetID);
    @Query("SELECT SUM(AM_TRANS) FROM `Transaction` WHERE BUD_ID = :budgetID AND PREV_DATE = :prevDate")
    LiveData<Float> getCurrentBudgetSum(int budgetID, String prevDate);


    @Insert
    void addEnvelope(Envelope env);
    @Update
    void updateEnvelope(Envelope env);
    @Delete
    void deleteEnvelope(Envelope env);
    @Query("SELECT * FROM ENVELOPE")
    LiveData<List<Envelope>> getALlEnvelopes();
    @Query("SELECT * FROM ENVELOPE WHERE BUD_ID = :bugdetID AND PREV_DATE = :prevDate")
    LiveData<List<Envelope>> getPrevBudgetEnvelopes(int bugdetID, String prevDate);

}