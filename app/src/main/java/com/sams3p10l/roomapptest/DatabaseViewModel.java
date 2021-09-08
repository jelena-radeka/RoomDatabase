package com.sams3p10l.roomapptest;

import android.app.Application;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class DatabaseViewModel extends AndroidViewModel {

    private ItemRepository repository;
    private LiveData<List<ItemData>> data;
    RoomDB roomDB;
    private ItemDao itemDao;


    public DatabaseViewModel(@NonNull Application application) {
        super(application);
        roomDB=RoomDB.getInstance(application.getApplicationContext());
        itemDao=roomDB.itemDao();
        repository = new ItemRepository(application);
        data=repository.getAll();

    }

    public void insert(ItemData itemData){
        repository.insert(itemData);
    }

    public void deleteAllItems() {
        repository.deleteAllItems();
    }


    public LiveData<List<ItemData>> getAll() {
        return data;
    }

}
