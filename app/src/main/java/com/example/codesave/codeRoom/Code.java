package com.example.codesave.codeRoom;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;


@Entity(tableName = "code", primaryKeys = {"value", "referer"})
public class Code {

    @NonNull
    @ColumnInfo(name="value")
    private String _code;

    @NonNull
    @ColumnInfo(name="position")
    private int _position;

    @NonNull
    @ColumnInfo(name="color")
    private String _color;

    @NonNull
    @ColumnInfo(name="referer")
    private String _referer;

    @NonNull
    @ColumnInfo(name="date")
    private long _creationDate;


    public Code(@NonNull String code, @NonNull int position, @NonNull String color, @NonNull String referer, @NonNull long creationDate) {
        this._code = code;
        this._color = color;
        this._position = position;
        this._creationDate = creationDate;
        this._referer = referer;
    }

    public String getCode() { return this._code; }

    public int getPosition() { return this._position; }

    public String getColor() { return this._color; }


    public long getCreationDate() {
        return this._creationDate;
    }

    public String getReferer() {
        return this._referer;
    }

    @NonNull
    @Override
    public String toString() {
        return "Code element value: " + this._code
                + "color: " + this._color
                + "position: " + this._position
                + "stamp: " + this._creationDate
                + "referer: " + this._referer;
    }


}

