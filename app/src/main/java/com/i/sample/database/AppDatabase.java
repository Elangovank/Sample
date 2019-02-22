package com.i.sample.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.i.sample.database.dao.QuestionsDao;
import com.i.sample.database.dao.ResultDao;
import com.i.sample.database.dao.UserDao;
import com.i.sample.database.dao.categoryDao;
import com.i.sample.database.models.Category;
import com.i.sample.database.models.Questions;
import com.i.sample.database.models.Results;
import com.i.sample.database.models.User;


@Database(entities = {User.class, Results.class, Questions.class, Category.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {


    private static AppDatabase INSTANCE;

    public abstract categoryDao CategoryDao();

    public abstract QuestionsDao QuestionsDao();

    public abstract ResultDao ResultDao();

    public abstract UserDao UserDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "sample_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static AppDatabase getMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
