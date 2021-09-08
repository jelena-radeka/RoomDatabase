package com.sams3p10l.roomapptest;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ItemDao {

    @Insert(onConflict = REPLACE)
    void insert(ItemData data);

    @Query("DELETE FROM table_name")
    void delete();

    @Query("SELECT*FROM table_name")
    LiveData<List<ItemData>> getALL();
}
