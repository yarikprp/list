package com.example.myrooms.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myrooms.R;
import com.example.myrooms.data.db.ShoplistemEntity;
import com.example.myrooms.databinding.FragmentShoplistBinding;
import com.example.myrooms.domain.adapters.ShoplistAdapter;
import com.example.myrooms.domain.viewmodels.ShoplistViewModel;

import java.util.ArrayList;

public class ShoplistFragment extends Fragment {
    FragmentShoplistBinding binding;
    ShoplistAdapter adapter;


    public static ShoplistFragment newInstance() {
        return new ShoplistFragment();
    }

    ShoplistViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShoplistBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this).get(ShoplistViewModel.class);
        ShoplistAdapter.OnShopListItemClick onItemClick = (position, checked) -> {
            ShoplistemEntity item = adapter.getElementByPosition(position);
            item.setCompleted(checked);
            viewModel.updateItems(item);
        };

        ShoplistAdapter.OnMenuItemSelected onMenuItemSelected = (position, item) -> {
            if(item.getItemId() == R.id.deleteItem){
                ShoplistemEntity entity = adapter.getElementByPosition(position);
                viewModel.deleteItems(entity);
            }
            else if(item.getItemId() == R.id.redactItem){
                ShoplistemEntity entity = adapter.getElementByPosition(position);
                new NewRecordDialog(entity).show(getChildFragmentManager(), NewRecordDialog.TAG);
            }
        };

        ArrayList<ShoplistemEntity> items = (ArrayList<ShoplistemEntity>) viewModel.shopListItems.getValue();
        if(items == null){
            items = new ArrayList<>();
        }

        adapter = new ShoplistAdapter(items, onItemClick, requireContext(), onMenuItemSelected);
        viewModel.shopListItems.observe(getViewLifecycleOwner(), shopListItemEntities -> adapter.updateData((ArrayList<ShoplistemEntity>) shopListItemEntities));
        binding.shoplistView.setAdapter(adapter);

        binding.addItemButton.setOnClickListener(v -> {
            new NewRecordDialog(null).show(getChildFragmentManager(), NewRecordDialog.TAG);
        });
        return binding.getRoot();
    }
}
