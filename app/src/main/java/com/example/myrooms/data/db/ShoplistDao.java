package com.example.myrooms.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ShoplistDao {
    @Insert
    void insertNewItems(ShoplistemEntity... entities);

    @Update
    void updateItems(ShoplistemEntity... entities);

    @Delete
    void deleteItems(ShoplistemEntity... entities);

    @Query("SELECT * FROM shoplist")
    LiveData<List<ShoplistemEntity>> getAllShopListItems();

    @Query("SELECT * FROM shoplist WHERE ID = :id")
    ShoplistemEntity getShopListItemById(int id);
}

