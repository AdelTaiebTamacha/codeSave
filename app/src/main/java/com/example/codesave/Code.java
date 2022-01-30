package com.example.codesave;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity(tableName = "code", primaryKeys = {"value", "referer"})
public class Code {

    @NonNull
    @ColumnInfo(name="value")
    private String _code;

    @NonNull
    @ColumnInfo(name="referer")
    private String _referer;

    @NonNull
    @ColumnInfo(name="date")
    private int _creationDate;


    public Code(@NonNull String code, @NonNull String referer, @NonNull int creationDate) {
        this._code = code;
        this._creationDate = creationDate;
        this._referer = referer;
    }

    public String getCode() { return this._code; }

    public int getCreationDate() {
        return this._creationDate;
    }

    public String getReferer() {
        return this._referer;
    }
}

