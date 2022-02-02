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

    public void shuffle() {
        Log.d("codeSave", "shuffle: ");

        List<Code> currentCodeList = codeList.getValue();
        ArrayList<String> currentCodes = new ArrayList<String>();
        ArrayList<String> currentColors = new ArrayList<String>();
        ArrayList<String> currentValue = new ArrayList<String>();

        for (Code code: currentCodeList) {
            String[] treeCodes = code.getCode().split(",");
            String[] treeColors = code.getColor().split(",");
            for (int i = 0; i < 3; i++) {
                currentCodes.add(treeCodes[i]);
                currentColors.add(treeColors[i]);
                currentValue.add(treeCodes[i]+","+treeColors[i]);
            }
        }
        Collections.shuffle(currentValue);

        Long ts = System.currentTimeMillis()/1000;
        codeRepository.deleteAll();

        for (int i = 0; i < currentCodeList.size(); i++) {
            StringBuilder newCodes = new StringBuilder();
            StringBuilder newColors = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                newCodes.append(currentValue.get(3 * i + j).split(",")[0] + ",");
                newColors.append(currentValue.get(3 * i + j).split(",")[1] + ",");
            }
            codeRepository.insert(new Code(newCodes.toString(), i, newColors.toString(),"test", ts));
        }
    }

}
