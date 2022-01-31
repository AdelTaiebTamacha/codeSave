package com.example.codesave;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CodeDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Code code);

    @Query("DELETE FROM code")
    void deleteAll();

    @Query("SELECT * FROM code ORDER BY position ASC")
    LiveData<List<Code>> getAllCodes();

    @Query("SELECT * FROM code WHERE referer == :ref")
    LiveData<List<Code>> getCodeFromReferer(String ref);


}
