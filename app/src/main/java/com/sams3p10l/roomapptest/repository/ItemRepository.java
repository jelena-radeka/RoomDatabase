package com.sams3p10l.roomapptest.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sams3p10l.roomapptest.AppExecutors;
import com.sams3p10l.roomapptest.db.ItemDao;
import com.sams3p10l.roomapptest.db.RoomDB;
import com.sams3p10l.roomapptest.model.ItemData;

import java.util.List;

public class ItemRepository {

    private ItemDao itemDao;
    private LiveData<List<ItemData>> list;
    private RoomDB database;

    public ItemRepository(Application application) {
        database = RoomDB.getInstance(application);
        itemDao = database.itemDao();
        list = itemDao.getALL();
    }

    public void insert(ItemData itemData) {
        AppExecutors.getInstance().diskIO().execute(() -> database.itemDao().insert(itemData));
    }

    public void deleteAllItems() {
        AppExecutors.getInstance().diskIO().execute(() -> database.itemDao().delete());
    }

    public LiveData<List<ItemData>> getAll() {
        return list;
    }
}
