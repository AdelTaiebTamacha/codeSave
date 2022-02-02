package com.example.codesave.codeRoom;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CodeViewModel extends AndroidViewModel {

    private CodeRepository codeRepository;
    private final LiveData<List<Code>> codeList;

    public CodeViewModel(Application application){
        super(application);
        codeRepository = new CodeRepository(application);
        codeList = codeRepository.getCodeList();
    }

    public LiveData<List<Code>> getCodeList() {
        return codeList;
    }

    public void insert(Code code) {
        codeRepository.insert(code);
    }

    public void deleteAll() { codeRepository.deleteAll(); }

    public void shuffle() { codeRepository.shuffleAll(); }

}
