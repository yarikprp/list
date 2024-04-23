package com.example.myrooms.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myrooms.R;
import com.example.myrooms.data.db.ShoplistemEntity;
import com.example.myrooms.databinding.FragmentNewRecordDialogBinding;
import com.example.myrooms.domain.viewmodels.ShoplistViewModel;

public class NewRecordDialog extends DialogFragment {
    FragmentNewRecordDialogBinding binding;
    ShoplistViewModel viewModel;
    ShoplistemEntity entity;

    public static String TAG = "NewRecordDialog";

    public NewRecordDialog(ShoplistemEntity entityToEdit) {
        entity = entityToEdit;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ShoplistViewModel.class);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        binding = FragmentNewRecordDialogBinding.inflate(getLayoutInflater());
        String positiveButtonText = "Добавить";
        String dialogTitle = "Новая запись";
        if(entity!=null){
            binding.newRecordText.setText(entity.getName());
            dialogTitle = "Редактирование";
            positiveButtonText = "Изменить";
        }
        return new AlertDialog.Builder(requireContext())
                .setTitle(dialogTitle)
                .setView(binding.getRoot())
                .setPositiveButton(positiveButtonText, (dialog, which) -> {
                    if(entity!=null){
                        entity.setName(binding.newRecordText.getText().toString());
                        viewModel.updateItems(entity);

                    }
                    else{
                        ShoplistemEntity entity = new ShoplistemEntity(0, binding.newRecordText.getText().toString(), false);
                        viewModel.insertItems(entity);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Отмена", (dialog, which) -> {
                    dialog.dismiss();
                })
                .show();
    }
}
