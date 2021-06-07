package com.example.store.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.store.MainActivity;
import com.example.store.R;
import com.example.store.catalog.Category;
import com.example.store.web.WebService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<Product> products;

    SwipeRefreshLayout refreshLayout;

    RecyclerView recyclerView;

    BottomNavigationView bottomNavigationView;

    ProgressBar progressBar;

    private Bundle args;

    FragmentTransaction fragmentTransaction;

    Category category;

    ProductAdapter productAdapter;

    ProductAdapter.onStateClickListener listener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        setHasOptionsMenu(true);

        if (getArguments() != null) {
            category = (Category) getArguments().getSerializable("category");
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(category.getTitle());
        } else {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle("Store");
        }

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);

        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        refreshLayout = view.findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) this);
        refreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        refreshLayout.post(() -> {
            refreshLayout.setRefreshing(true);

            initiliazeData(true, false, false, false);
        });

        recyclerView = view.findViewById(R.id.list_of_products);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        progressBar = view.findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        initiliazeData(true, false, false, false);

        progressBar.setVisibility(View.INVISIBLE);

        listener = (product, position) -> {
            args = new Bundle();
            args.putSerializable("product", product);

            ProductDetailFragment fragment = new ProductDetailFragment();

            fragment.setArguments(args);

            fragmentTransaction.replace(getId(), fragment);
            fragmentTransaction.commit();
        };


        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.recommend_items, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.open_cosine_similarity:
                item.setChecked(true);
                initiliazeData(false, true, false, false);
                return true;
            case R.id.open_mse:
                item.setChecked(true);
                initiliazeData(false, false, false, true);
                return true;
            case R.id.open_pearson_correlation:
                item.setChecked(true);
                initiliazeData(false, false, true, false);
                return true;
        }
        return false;
    }

    public static ProductFragment newInstance() {
        return new ProductFragment();
    }

    private void initiliazeData(Boolean allProducts, Boolean recommendByCosineSimilarity, Boolean recommendByPearsonCorrelation, Boolean recommendByStandardError) {
        if (allProducts) {
            if (category != null) {
                WebService.getInstance().getProductApi().getProductsByCategoryId(category.getId()).enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        products = response.body();
                        productAdapter = new ProductAdapter(listener, getContext(), products);
                        recyclerView.setAdapter(productAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        Toast.makeText(getContext(), "Category null", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                WebService.getInstance().getProductApi().getProducts().enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        products = response.body();
                        productAdapter = new ProductAdapter(listener, getContext(), products);
                        recyclerView.setAdapter(productAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {

                    }
                });
            }
        }
        if (recommendByCosineSimilarity) {
            WebService.getInstance().getProductApi().getProductsByCosineSimilarity(((MainActivity) getActivity()).getUser().getUserToken(), ((MainActivity) getActivity()).getUser().getId()).enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    products = response.body();
                    productAdapter = new ProductAdapter(listener, getContext(), products);
                    recyclerView.setAdapter(productAdapter);
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {

                }
            });
        }
        if (recommendByStandardError) {
            WebService.getInstance().getProductApi().getProductsByMSE(((MainActivity) getActivity()).getUser().getUserToken(), ((MainActivity) getActivity()).getUser().getId()).enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    products = response.body();
                    productAdapter = new ProductAdapter(listener, getContext(), products);
                    recyclerView.setAdapter(productAdapter);
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {

                }
            });
        }
        if (recommendByPearsonCorrelation) {
            WebService.getInstance().getProductApi().getProductsByPearsonCorrelation(((MainActivity) getActivity()).getUser().getUserToken(), ((MainActivity) getActivity()).getUser().getId()).enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    products = response.body();
                    productAdapter = new ProductAdapter(listener, getContext(), products);
                    recyclerView.setAdapter(productAdapter);
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {

                }
            });
        }

        refreshLayout.setRefreshing(false);

    }

    @Override
    public void onRefresh() {
        initiliazeData(true, false, false, false);
    }
}
