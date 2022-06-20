package com.sucelloztm.sucelloz.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Class for the home view model
 */
public class HomeViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    /**
     * Default constructor
     */
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    /**
     * Get text
     * @return text
     */
    public LiveData<String> getText() {
        return mText;
    }
}