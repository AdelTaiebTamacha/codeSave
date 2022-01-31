package com.example.codesave;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;
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
                code = new Code("1111", 0, "test", 1);	dao.insert(code);
                code = new Code("2222", 1, "test", 1);	dao.insert(code);
                code = new Code("3333", 2, "test", 1);	dao.insert(code);
                code = new Code("4444", 3, "test", 1);	dao.insert(code);
                code = new Code("5555", 4, "test", 1);	dao.insert(code);
                code = new Code("6666", 5, "test", 1);	dao.insert(code);
                code = new Code("7777", 6, "test", 1);	dao.insert(code);
                code = new Code("8888", 7, "test", 1);	dao.insert(code);
                code = new Code("9999", 8, "test", 1);	dao.insert(code);
                code = new Code("0000", 9, "test", 1);	dao.insert(code);
//                LiveData<List<Code>> codes = dao.getAllCodes();
//                codes.getValue().forEach( c -> {
//                    Log.d("codeSave", "Code :"+String.valueOf(c.getCode()));
//                });

                Log.d("codeSave", "onCreate: created");
            });
        }
    };
}
