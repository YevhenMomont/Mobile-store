package com.example.store.product;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.store.MainActivity;
import com.example.store.R;
import com.example.store.order.Record;
import com.example.store.user.User;
import com.example.store.web.WebService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailFragment extends Fragment {

    private Product product;

    ImageView imageView;

    TextView name, desc, price;

    ActionBar actionBar;

    BottomNavigationView bottomNavigationView;

    FragmentTransaction fragmentTransaction;

    RatingBar ratingBar;

    Button purchaseButton, editButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        actionBar = ((MainActivity) getActivity()).getSupportBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);

        bottomNavigationView.setVisibility(View.GONE);

        imageView = view.findViewById(R.id.product_image);

        name = view.findViewById(R.id.product_name);
        desc = view.findViewById(R.id.product_description);
        price = view.findViewById(R.id.product_price);
        ratingBar = view.findViewById(R.id.product_rating);

        purchaseButton = view.findViewById(R.id.purchaseButton);

        editButton = view.findViewById(R.id.go_to_edit_product);

        product = (Product) getArguments().getSerializable("product");

        name.setText(product.getTitle());
        desc.setText(product.getDescription());
        price.setText(String.valueOf(product.getPrice()));

        User user = ((MainActivity) getActivity()).getUser();

        if (user == null) {
            purchaseButton.setVisibility(View.INVISIBLE);
        } else {
            if (product.getCreatedBy().equals(user.getId()) || user.isAdmin()) {
                editButton.setVisibility(View.VISIBLE);
            }
        }


        Glide.with(view.getContext())
                .load(product.getImageUrl())
                .into(imageView)
        ;
        ;

        purchaseButton.setOnClickListener(v -> purchaseEvent(user));

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putSerializable("product", product);
                ProductEditingFragment productEditingFragment = new ProductEditingFragment();

                productEditingFragment.setArguments(args);

                fragmentTransaction.replace(getId(), productEditingFragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    private void purchaseEvent(User user) {
        user.getPurchasedProducts().add(product.getUuid());

        WebService.getInstance().getUserApi().updateUser(user.getUserToken(), user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(getContext(), "Success purchased product", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();

            }
        });

        Record record = new Record();

        record.setProductId(product.getUuid());
        record.setUserId(user.getId());
        record.setRatingByUser(ratingBar.getRating());

        WebService.getInstance().getRecordApi().postRecord(user.getUserToken(), record).enqueue(new Callback<Record>() {
            @Override
            public void onResponse(Call<Record> call, Response<Record> response) {

            }

            @Override
            public void onFailure(Call<Record> call, Throwable t) {

            }
        });
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

    public static ProductDetailFragment newInstance() {
        return new ProductDetailFragment();
    }
}