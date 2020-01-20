package com.example.mockup.ui.MinhasDiligências;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MinhasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MinhasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}