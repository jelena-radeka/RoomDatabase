package com.sams3p10l.roomapptest;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemRepository {

    private ItemDao itemDao;
    private LiveData<List<ItemData>> list;

    public ItemRepository(Application application) {
        RoomDB database = RoomDB.getInstance(application);
        itemDao = database.itemDao();
        list = itemDao.getALL();
    }

    public void insert(ItemData itemData) {
        new InsertItemAsyncTask(itemDao).execute(itemData);
    }


    public void deleteAllItems() {
        new DeleteAllItemsAsyncTask(itemDao).execute();
    }

    public LiveData<List<ItemData>> getAll() {
        return list;
    }

    private static class InsertItemAsyncTask extends AsyncTask<ItemData, Void, Void> {
        private ItemDao itemDao;

        private InsertItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(ItemData... itemData) {
            itemDao.insert(itemData[0]);
            return null;
        }
    }

    private static class DeleteAllItemsAsyncTask extends AsyncTask<Void, Void, Void> {
        private ItemDao itemDao;

        private DeleteAllItemsAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            itemDao.delete();
            return null;
        }
    }
}
