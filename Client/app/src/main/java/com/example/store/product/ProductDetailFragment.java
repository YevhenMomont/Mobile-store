package com.example.store.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.store.MainActivity;
import com.example.store.R;

public class ProductDetailFragment extends Fragment {

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    private Product product;

    ImageView imageView, basket;

    TextView name, desc, price;

    private ActionBar actionBar;

    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        actionBar = ((MainActivity) getActivity()).getSupportBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        imageView = view.findViewById(R.id.product_image);

        name = view.findViewById(R.id.product_name);
        desc = view.findViewById(R.id.product_description);
        price = view.findViewById(R.id.product_price);

        basket = view.findViewById(R.id.basket);

        product = (Product) getArguments().getSerializable("product");

        name.setText(product.getName());
        desc.setText(product.getDescription());
        price.setText(String.valueOf(product.getPrice()));

        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        basket.setOnClickListener(v -> Toast.makeText(getContext(), "ssss", Toast.LENGTH_LONG).show());

        /*ActionBar actionBar = getActivity().getActionBar();

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
*/


        /*imageView = view.findViewById(R.id.image_view);
        button = view.findViewById(R.id.select_image);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        pickImageFromGallery();
//                        requestPermissions(permissions, PERMISSION_CODE);
                    } else {
                        Toast.makeText(v.getContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });*/


        return view;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                fragmentTransaction.replace(getId(), ProductFragment.newInstance());
                fragmentTransaction.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    /*private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == IMAGE_PICK_CODE) {
            imageView.setImageURI(data.getData());

        }
    }*/

    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();
                }
                else {
                    Toast.makeText(getActivity(), "Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }*/

    public static ProductDetailFragment newInstance() {
        return new ProductDetailFragment();
    }
}