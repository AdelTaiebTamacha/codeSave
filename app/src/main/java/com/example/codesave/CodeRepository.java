package com.example.codesave;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CodeRepository {

    private CodeDAO codeDAO;
    private LiveData<List<Code>> codeList;

    CodeRepository(Application application){
        CodeRoomDB db = CodeRoomDB.getDatabase(application);
        codeDAO = db.codeDAO();
        codeList = codeDAO.getAllCodes();
    }

    LiveData<List<Code>> getCodeList() {
        return codeList;
    }

    void insert(Code code){
        CodeRoomDB.databaseWriteExecutor.execute(() -> {
            codeDAO.insert(code);
        });
    }

    void deleteAll() {
        CodeRoomDB.databaseWriteExecutor.execute(() -> {
            codeDAO.deleteAll();
        });
    }
}
