package com.example.codesave.codeRoom;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Code.class}, version = 1, exportSchema = false)
public abstract class CodeRoomDB extends RoomDatabase {

    public abstract CodeDAO codeDAO();

    private static volatile CodeRoomDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CodeRoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CodeRoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CodeRoomDB.class, "code")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    };

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                CodeDAO dao = INSTANCE.codeDAO();
                dao.deleteAll();

                Code code;
                code = new Code("1111,1112,1113", 0, "0.000,0.000,0.000", "test", 1);	dao.insert(code);
                code = new Code("2221,2222,2223", 1, "0.111,0.111,0.111", "test", 1);	dao.insert(code);
                code = new Code("3331,3332,3333", 2, "0.222,0.222,0.222", "test", 1);	dao.insert(code);
                code = new Code("4441,4442,4443", 3, "0.333,0.333,0.333", "test", 1);	dao.insert(code);
                code = new Code("5551,5552,5553", 4, "0.444,0.444,0.444", "test", 1);	dao.insert(code);
                code = new Code("6661,6662,6663", 5, "0.555,0.555,0.555", "test", 1);	dao.insert(code);
                code = new Code("7771,7772,7773", 6, "0.666,0.666,0.666", "test", 1);	dao.insert(code);
                code = new Code("8881,8882,8883", 7, "0.777,0.777,0.777", "test", 1);	dao.insert(code);
                code = new Code("9991,9992,9993", 8, "0.888,0.888,0.888", "test", 1);	dao.insert(code);
                code = new Code("0001,0002,0003", 9, "1.000,1.000,1.000", "test", 1);	dao.insert(code);

                Log.d("codeSave", "onCreate: created");
            });
        }
    };
}
