package com.example.nomoola.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.nomoola.database.entity.InOutCome;
import java.util.List;

@Dao
public interface InOutComeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInOutCome(InOutCome...inOutComes);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateInOutCome(InOutCome...inOutComes);

    @Delete
    void deleteInOutCome(InOutCome inOutCome);

    @Query("SELECT * FROM T_INOUTCOME")
    LiveData<List<InOutCome>> getALlInOutComes();

}
