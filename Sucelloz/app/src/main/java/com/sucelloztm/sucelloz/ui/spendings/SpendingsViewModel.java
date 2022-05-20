package com.sucelloztm.sucelloz.ui.spendings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SpendingsViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public SpendingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is spendings fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}