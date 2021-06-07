package com.example.store;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.store.authorization.AuthorizationFragment;
import com.example.store.authorization.LogoutFragment;
import com.example.store.catalog.CategoryCreatingFragment;
import com.example.store.catalog.CategoryFragment;
import com.example.store.product.ProductCreatingFragment;
import com.example.store.product.ProductFragment;
import com.example.store.user.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    private static User user = null;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        loadFragment(ProductFragment.newInstance());
    }

    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.action_home:
                loadFragment(ProductFragment.newInstance());
                return true;
            case R.id.action_catalog:
                loadFragment(CategoryFragment.newInstance());
                return true;
            case R.id.action_add:
                if (user != null) {
                    if (user.isAdmin()) {
                        loadFragment(CategoryCreatingFragment.newInstance());
                        return true;
                    }
                    if (user.isUser()) {
                        loadFragment(ProductCreatingFragment.newInstance());
                        return true;
                    }
                }
                loadFragment(AuthorizationFragment.newInstance());
                return true;
            case R.id.action_account:
                if (user != null) {
                    if (user.isUser()) {
                        new LogoutFragment().show(getSupportFragmentManager(), "logout");
                        return true;
                    }
                }
                loadFragment(AuthorizationFragment.newInstance());
                return true;
            default:
                loadFragment(AuthorizationFragment.newInstance());
        }
        return false;
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                loadFragment(ProductFragment.newInstance());
                getSupportActionBar().setHomeButtonEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        MainActivity.user = user;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction
                .setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
                .addToBackStack(null)
                .replace(R.id.fl_content, fragment);
        fragmentTransaction.commit();
    }

    public ActionBar getSupportBar() {
        return getSupportActionBar();
    }


}