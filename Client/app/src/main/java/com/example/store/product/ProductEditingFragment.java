package com.example.store.product;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.store.MainActivity;
import com.example.store.R;
import com.example.store.catalog.Category;
import com.example.store.web.WebService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class ProductEditingFragment extends Fragment {

    EditText title, description, image;

    Spinner spinner;

    Button edit;

    private Product product;

    private List<Category> categories = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_editing, container, false);

        if (getArguments() != null) {
            product = (Product) getArguments().getSerializable("product");
        }

        spinner = view.findViewById(R.id.spinner);

        title = view.findViewById(R.id.editTextProductTitle);
        description = view.findViewById(R.id.editTextProductDesc);
        image = view.findViewById(R.id.imageViewProductEditing);

        if (product != null) {
            title.setText(product.getTitle());
            description.setText(product.getDescription());
            image.setText(product.getImageUrl());
        }

        spinnerInitialize();

        edit = view.findViewById(R.id.editingProductButton);
        edit.setOnClickListener(v -> editProduct());

        return view;
    }

    private void editProduct() {
        product.setTitle(title.getText().toString());
        product.setDescription(description.getText().toString());
        product.setCategoryId(categories.stream().filter(catalog -> catalog.getTitle().equals(spinner.getSelectedItem().toString())).map(Category::getId).findFirst().orElse(null));
        product.setImageUrl(image.getText().toString());

        WebService.getInstance().getProductApi().updateProduct(((MainActivity) getActivity()).getUser().getUserToken(), product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Log.d(TAG, "onResponse: " + response);
                Toast.makeText(getContext(), "Success edited", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.fillInStackTrace());
            }
        });
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
}