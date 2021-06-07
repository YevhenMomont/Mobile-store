package com.example.store.authorization;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.store.MainActivity;
import com.example.store.R;
import com.example.store.product.ProductFragment;

public class LogoutFragment extends DialogFragment {

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireActivity())
                .setTitle("Logout?")
                .setPositiveButton("Yes", this::onClick)
                .setNegativeButton("No", this::onClick)
                .create();
    }

    public void onClick(DialogInterface dialogInterface, int which) {

        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                ((MainActivity) requireActivity()).setUser(null);

                /*FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out);
                fragmentTransaction.replace(R.id.authorization, ProductFragment.newInstance());
                fragmentTransaction.commit();*/
                break;
            case Dialog.BUTTON_NEGATIVE:
                break;
        }
    }

}
