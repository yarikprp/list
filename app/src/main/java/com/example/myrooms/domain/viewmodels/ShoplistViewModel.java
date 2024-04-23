package com.example.myrooms.domain.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myrooms.ShoplistApplication;
import com.example.myrooms.data.db.ShoplistDao;
import com.example.myrooms.data.db.ShoplistemEntity;

import java.util.List;

public class ShoplistViewModel extends ViewModel {
    private ShoplistDao dao = ShoplistApplication.getInstance().getDb().getDao();

    public LiveData<List<ShoplistemEntity>> shopListItems = dao.getAllShopListItems();

    public  void updateItems(ShoplistemEntity... items){
        dao.updateItems(items);
    }
    public  void deleteItems(ShoplistemEntity... items) {dao.deleteItems(items);}

    public void insertItems(ShoplistemEntity... items){dao.insertNewItems(items);}
    public ShoplistemEntity getItemById(int id){return dao.getShopListItemById(id);}
}

