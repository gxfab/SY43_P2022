package com.sucelloztm.sucelloz.ui.zerobudget;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ZeroBudgetViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ZeroBudgetViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Zero Budget fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

