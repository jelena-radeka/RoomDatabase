package com.sams3p10l.roomapptest.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sams3p10l.roomapptest.model.ItemData;
import com.sams3p10l.roomapptest.repository.ItemRepository;

import java.util.List;

public class DatabaseViewModel extends AndroidViewModel {

    private ItemRepository repository;
    private LiveData<List<ItemData>> data;

    public DatabaseViewModel(@NonNull Application application) {
        super(application);
        repository = new ItemRepository(application);
        data = repository.getAll();
    }

    public void insert(ItemData itemData) {
        repository.insert(itemData);
    }

    public void deleteAllItems() {
        repository.deleteAllItems();
    }

    public LiveData<List<ItemData>> getAll() {
        return data;
    }

}
