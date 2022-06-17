package com.example.nomoola.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.nomoola.database.entity.InOutCome;

import java.time.LocalDate;
import java.util.List;

@Dao
public interface InOutComeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInOutCome(InOutCome...inOutComes);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateInOutCome(InOutCome...inOutComes);

    @Delete
    void deleteInOutCome(InOutCome inOutCome);

    @Query("SELECT * " +
            "FROM T_INOUTCOME")
    LiveData<List<InOutCome>> getALlInOutComes();

    @Query("SELECT * " +
            "FROM T_INOUTCOME " +
            "WHERE SUBCAT_ID=:subCategoryID")
    LiveData<List<InOutCome>> getInOutComesOf(int subCategoryID);

    @Query("UPDATE T_INOUTCOME " +
            "SET INOUTCOME_NAME=:name, INOUTCOME_DATE=:date, INOUTCOME_AMOUNT=:amount " +
            "WHERE CAT_ID=:catID " +
            "AND SUBCAT_ID=:subCatID " +
            "AND INOUTCOME_ID=:id")
    void updateInOutCome(int catID, int subCatID, String name, LocalDate date, double amount, int id);

    @Query("SELECT (100*SUM(INOUTCOME_AMOUNT))/CAT_BUDGET_AMOUNT " +
            "FROM T_INOUTCOME INNER JOIN T_CATEGORY" +
            " ON T_INOUTCOME.CAT_ID = T_CATEGORY.CAT_ID " +
            "WHERE T_INOUTCOME.SUBCAT_ID=:subCategoryID")
    LiveData<Integer> getPercentUsedOf(int subCategoryID);

    @Query("SELECT CAT_BUDGET_AMOUNT-SUM(INOUTCOME_AMOUNT) " +
            "FROM T_CATEGORY INNER JOIN T_INOUTCOME" +
            " ON T_CATEGORY.CAT_ID = T_INOUTCOME.CAT_ID " +
            "WHERE T_CATEGORY.CAT_ID=:categoryID")
    LiveData<Double> getBudgetLeftOf(int categoryID);

    @Query("SELECT (100*SUM(INOUTCOME_AMOUNT))/CAT_BUDGET_AMOUNT " +
            "FROM T_INOUTCOME INNER JOIN T_CATEGORY" +
            " ON T_INOUTCOME.CAT_ID = T_CATEGORY.CAT_ID " +
            "WHERE T_INOUTCOME.CAT_ID=:categoryID")
    LiveData<Integer> getPercentUsedOfCategory(int categoryID);

    @Query("SELECT SUM(INOUTCOME_AMOUNT) " +
            "FROM T_INOUTCOME " +
            "WHERE SUBCAT_ID=:m_subcat_id")
    LiveData<Double> getAmountUsedBySubcategory(int m_subcat_id);
}
