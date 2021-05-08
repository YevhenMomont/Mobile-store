package com.example.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationMenu extends Fragment {

    ImageView imgMap;
    ImageView imgDial;
    ImageView imgMail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_nav_menu, container, false);

        BottomNavigationView bottomNavigationView = new BottomNavigationView(view.getContext());

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                imgMap.setVisibility(View.VISIBLE);
                                imgDial.setVisibility(View.GONE);
                                imgMail.setVisibility(View.GONE);
                                break;
                            case R.id.action_favorite:
                                imgMap.setVisibility(View.GONE);
                                imgDial.setVisibility(View.VISIBLE);
                                imgMail.setVisibility(View.GONE);
                                break;
                            case R.id.action_account:
                                imgMap.setVisibility(View.GONE);
                                imgDial.setVisibility(View.GONE);
                                imgMail.setVisibility(View.VISIBLE);
                                break;
                        }
                        return false;
                    }
                });

        return view;
    }
}
