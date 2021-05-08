package com.example.store.product;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.store.MainActivity;
import com.example.store.R;
import com.example.store.catalog.Catalog;
import com.example.store.web.WebService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductCreatingFragment extends Fragment {

    private static final int IMAGE_PICK_CODE = 1000;

    Spinner spinner;

    ImageView imageView;

    EditText title, description;

    Button create;

//    private List<Catalog> catalogs;

    List<String> catalogs = new ArrayList<>(List.of("1", "2", "3", "4"));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_creating, container, false);

        spinner = view.findViewById(R.id.spinner);
        spinnerInitialize();

        imageView = view.findViewById(R.id.imageViewProductCreating);

        title = view.findViewById(R.id.editTextProductTitle);
        description = view.findViewById(R.id.editTextProductDesc);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
//                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        pickImageFromGallery();
//                        requestPermissions(permissions, PERMISSION_CODE);
                    } else {
                        Toast.makeText(v.getContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        create = view.findViewById(R.id.creatingButton);

        create.setOnClickListener(v -> createProduct());

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createProduct() {
        Product product = new Product();

        product.setName(title.getText().toString());
        product.setDescription(description.getText().toString());
        product.setPrice(Math.random());
//        product.setCategoryId(catalogs.stream().filter(catalog -> catalog.getName().equals(spinner.getTransitionName())).map(catalog -> catalog.getId()).findFirst().orElse(null));
        product.setCreatedBy(((MainActivity) getActivity()).getUser().getId());

        WebService.getInstance().getProductApi().postProduct(product);
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    private void spinnerInitialize() {
        /*WebService.getInstance().getCatalogApi().getCatalogs().enqueue(new Callback<List<Catalog>>() {
            @Override
            public void onResponse(Call<List<Catalog>> call, Response<List<Catalog>> response) {
                catalogs = response.body();
            }

            @Override
            public void onFailure(Call<List<Catalog>> call, Throwable t) {

            }
        });*/

//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, catalogs.stream().map(Catalog::getName).collect(Collectors.toList()));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, catalogs);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
    }

    public static ProductCreatingFragment newInstance() {
        return new ProductCreatingFragment();
    }
}