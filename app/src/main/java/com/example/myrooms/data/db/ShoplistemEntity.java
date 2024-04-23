package com.example.myrooms.data.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shoplist")
public class ShoplistemEntity {
    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "name")
    private String Name;

    @ColumnInfo(name = "completed")
    private boolean Completed = false;

    public ShoplistemEntity(int ID, String name, boolean completed){
        this.ID = ID;
        Name = name;
        Completed = completed;
    }

    public ShoplistemEntity() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isCompleted() {
        return Completed;
    }

    public void setCompleted(boolean completed) {
        Completed = completed;
    }
}
