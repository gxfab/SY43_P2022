package com.example.nomoola.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.nomoola.database.entity.Profile;
import com.example.nomoola.database.repository.DataRepository;
import java.util.List;

public class ProfileViewModel extends AndroidViewModel {
    private final DataRepository mRepository;
    private final LiveData<Profile> mProfile;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = new DataRepository(application);
        this.mProfile = mRepository.getmProfile();
    }

    public LiveData<Profile> getProfile(){
        return mProfile;
    }

    public void insert(Profile profile){
        mRepository.insert(profile);
    }

    public void delete(Profile profile){
        mRepository.delete(profile);
    }

    public void update(int userID, String userName, Profile.userLanguage language, Profile.userCurrency currency) {
        mRepository.update(userID, userName, language, currency);
    }

    public void setUsername(int userID, String userName){
        mRepository.setUsername(userID, userName);
    }

    public void setLanguage(int userID, Profile.userLanguage language){
        mRepository.setLanguage(userID, language);
    }

    public void setCurrency(int userID, Profile.userCurrency currency){
        mRepository.setCurrency(userID, currency);
    }

    public LiveData<String> getUserName(Profile profile) {
        return this.mRepository.getUserName(profile.getM_USER_ID());
    }
}
