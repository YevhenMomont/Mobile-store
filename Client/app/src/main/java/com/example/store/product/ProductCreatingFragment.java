package com.example.store.product;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.store.MainActivity;
import com.example.store.R;
import com.example.store.catalog.Category;
import com.example.store.catalog.CategoryCreatingFragment;
import com.example.store.web.WebService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


public class ProductCreatingFragment extends Fragment {

    private static final int IMAGE_PICK_CODE = 1000;

    Spinner spinner;

    TextView goToCategoryCreating;

//    ImageView imageView;

    EditText title, description, imageView;

    Button create;

    ActionBar actionBar;

    BottomNavigationView bottomNavigationView;

    private List<Category> categories = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_creating, container, false);

        actionBar = ((MainActivity) getActivity()).getSupportBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);

        bottomNavigationView.setVisibility(View.GONE);

        spinner = view.findViewById(R.id.spinner);

        spinnerInitialize();

        goToCategoryCreating = view.findViewById(R.id.goToCreateCatalog);

        title = view.findViewById(R.id.editTextProductTitle);
        description = view.findViewById(R.id.editTextProductDesc);
        imageView = view.findViewById(R.id.imageViewProductCreating);

/*
        imageView.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
//                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                    pickImageFromGallery();
//                        requestPermissions(permissions, PERMISSION_CODE);
                } else {
                    Toast.makeText(v.getContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
*/

        create = view.findViewById(R.id.creatingProductButton);

        create.setOnClickListener(v -> createProduct());

        if (((MainActivity) getActivity()).getUser().isAdmin()) {
            goToCategoryCreating.setVisibility(View.VISIBLE);
            goToCategoryCreating.setOnClickListener(v -> {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                fragmentTransaction.replace(getId(), CategoryCreatingFragment.newInstance());
                fragmentTransaction.commit();
            });
        }

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createProduct() {
        Product product = new Product();

        product.setTitle(title.getText().toString());
        product.setDescription(description.getText().toString());
        product.setPrice(Math.random());
        product.setCategoryId(categories.stream().filter(catalog -> catalog.getTitle().equals(spinner.getSelectedItem().toString())).map(Category::getId).findFirst().orElse(null));
        product.setCreatedBy(((MainActivity) getActivity()).getUser().getId());
        product.setImageUrl(imageView.getText().toString());

        WebService.getInstance().getProductApi().postProduct(((MainActivity) getActivity()).getUser().getUserToken(), product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Log.d(TAG, "onResponse: " + response);
                Toast.makeText(getContext(), "Success created", Toast.LENGTH_SHORT).show();

                title.setText("");
                description.setText("");
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.fillInStackTrace());
            }
        });
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    private void spinnerInitialize() {
        WebService.getInstance()
                .getCategoryApi()
                .getCategories()
                .enqueue(new Callback<List<Category>>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                        if (response.body() != null) {
                            categories = response.body();

                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categories.stream().map(Category::getTitle).collect(Collectors.toList()));
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            spinner.setAdapter(arrayAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t) {

                    }
                });


    }

    public static ProductCreatingFragment newInstance() {
        return new ProductCreatingFragment();
    }
}