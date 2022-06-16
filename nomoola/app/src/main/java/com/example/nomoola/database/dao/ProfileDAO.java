package com.example.nomoola.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.nomoola.database.entity.Profile;

import java.util.List;

@Dao
public interface ProfileDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProfile(Profile...profiles);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateProfile(Profile...profiles);

    @Delete
    void deleteProfile(Profile profile);

    @Query("SELECT * " +
            "FROM T_PROFILE")
    LiveData<Profile> getProfile();

    @Query("SELECT * " +
            "FROM T_PROFILE " +
            "WHERE USER_ID=:userID")
    LiveData<Profile> getProfileOf(int userID);

    @Query("UPDATE T_PROFILE SET USERNAME=:userName, CURRENCY=:currency, LANGUAGE=:language WHERE USER_ID=:userID ")
    void updateProfile(int userID, String userName, Profile.userLanguage language, Profile.userCurrency currency);

    @Query("UPDATE T_PROFILE SET USERNAME=:userName WHERE USER_ID=:userID")
    void setUserName(int userID, String userName);

    @Query("UPDATE T_PROFILE SET LANGUAGE=:language WHERE USER_ID=:userID")
    void setLanguage(int userID, Profile.userLanguage language);

    @Query("UPDATE T_PROFILE SET CURRENCY=:currency WHERE USER_ID=:userID")
    void setCurrency(int userID, Profile.userCurrency currency);

    @Query("SELECT USERNAME " + "FROM T_PROFILE " + "WHERE USER_ID=:userID")
    LiveData<String> getUserName(int userID);

    @Query("SELECT USER_ID " +
            "FROM T_PROFILE")
    int getUserID();
}
