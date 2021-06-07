package com.example.store.catalog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.store.MainActivity;
import com.example.store.R;
import com.example.store.product.ProductCreatingFragment;
import com.example.store.product.ProductFragment;
import com.example.store.web.WebService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class CategoryCreatingFragment extends Fragment {

    EditText title, desc, image;

    TextView goToProductCreating;

    Button createCategory;

    ActionBar actionBar;

    BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_creating, container, false);

        actionBar = ((MainActivity) getActivity()).getSupportBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);

        bottomNavigationView.setVisibility(View.GONE);

        title = view.findViewById(R.id.editTextCategoryTitle);
        desc = view.findViewById(R.id.editTextCategoryDesc);
        image = view.findViewById(R.id.imageViewCategoryCreating);

        createCategory = view.findViewById(R.id.creatingCategoryButton);

        goToProductCreating = view.findViewById(R.id.goToCreateProduct);

        goToProductCreating.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(getId(), ProductCreatingFragment.newInstance());
            fragmentTransaction.commit();
        });

        createCategory.setOnClickListener(v -> {
            Category category = new Category();

            category.setTitle(title.getText().toString());
            category.setDescription(desc.getText().toString());
            category.setImageUrl(image.getText().toString());

            WebService.getInstance()
                    .getCategoryApi()
                    .postCategory(((MainActivity) getActivity()).getUser().getUserToken(), category).enqueue(new Callback<Category>() {
                @Override
                public void onResponse(Call<Category> call, Response<Category> response) {
                    Log.d(TAG, "onResponse: " + response);
                    Toast.makeText(getContext(), "Success created category", Toast.LENGTH_SHORT).show();

                    title.setText("");
                    desc.setText("");
                }

                @Override
                public void onFailure(Call<Category> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.fillInStackTrace());
                }
            });
            ;
        });

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.action_home:
                fragmentTransaction.replace(getId(), ProductFragment.newInstance());
                fragmentTransaction.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public static CategoryCreatingFragment newInstance() {
        return new CategoryCreatingFragment();
    }
}