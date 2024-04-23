package com.example.myrooms.domain.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrooms.R;
import com.example.myrooms.data.db.ShoplistemEntity;
import com.example.myrooms.databinding.ShoplistItemBinding;

import java.util.ArrayList;

public class ShoplistAdapter extends RecyclerView.Adapter<ShoplistAdapter.ViewHolder> {

    private ArrayList<ShoplistemEntity> shoplistItems;

    private final OnShopListItemClick onShopListItemClick;
    private final Context context;
    private final OnMenuItemSelected оnMenuItemSelected;

    public interface OnShopListItemClick{
        void onItemClicked(int position, boolean checked);
    }

    public  interface OnMenuItemSelected{
        void onShopListMenuItemSelected(int position, MenuItem item);
    }

    public ShoplistAdapter(ArrayList<ShoplistemEntity> shoplistItems, OnShopListItemClick onShopListItemClick, Context context,
                           OnMenuItemSelected оnMenuItemSelected) {
        this.shoplistItems = shoplistItems;
        this.onShopListItemClick = onShopListItemClick;
        this.context = context;
        this.оnMenuItemSelected = оnMenuItemSelected;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ShoplistItemBinding binding;

        public ViewHolder(@NonNull ShoplistItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;

            binding.productChecked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        boolean isChecked = binding.productChecked.isChecked();
                        onShopListItemClick.onItemClicked(position, isChecked);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ShoplistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        ShoplistItemBinding binding = ShoplistItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoplistAdapter.ViewHolder holder, int position){
        holder.binding.productName.setText(shoplistItems.get(position).getName());
        holder.binding.productChecked.setChecked(shoplistItems.get(position).isCompleted());
        holder.binding.getRoot().setOnLongClickListener(view -> {
            PopupMenu menu = new PopupMenu(context, view);
            menu.inflate(R.menu.list_menu);
            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    оnMenuItemSelected.onShopListMenuItemSelected(position, menuItem);
                    return true;
                }
            });
            menu.show();
            return true;
        });
    }

    @Override
    public int getItemCount(){
        return shoplistItems.size();
    }

    public void updateData(ArrayList<ShoplistemEntity> newItems){
        this.shoplistItems = newItems;
        this.notifyDataSetChanged();
    }

    public ShoplistemEntity getElementByPosition(int position){
        return shoplistItems.get(position);
    }
}
