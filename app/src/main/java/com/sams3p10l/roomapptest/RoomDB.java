package com.sams3p10l.roomapptest;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {ItemData.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB database;

    private static String DATABASE_NAME = "database";

    public static synchronized RoomDB getInstance(Context context) {

        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext()
                    , RoomDB.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();

        }
        return database;
    }

    public abstract ItemDao itemDao();

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new DbAsyncTask(database).execute();
        }
    };

    private static class DbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ItemDao itemDao;

        private DbAsyncTask(RoomDB db) {
            itemDao = db.itemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            itemDao.insert(new ItemData("Title4"));
            itemDao.insert(new ItemData("Title3"));
            itemDao.insert(new ItemData("Title2"));
            itemDao.insert(new ItemData("Title1"));
            return null;
        }
    }
}
