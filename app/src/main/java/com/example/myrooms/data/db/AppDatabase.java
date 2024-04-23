package com.example.myrooms.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ShoplistemEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShoplistDao getDao();
}
