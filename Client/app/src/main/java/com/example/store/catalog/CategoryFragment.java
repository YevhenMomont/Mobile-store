package com.example.store.catalog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.store.R;
import com.example.store.product.ProductFragment;
import com.example.store.web.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<Category> categories;

    SwipeRefreshLayout refreshLayout;
    RecyclerView recyclerView;

    FragmentTransaction fragmentTransaction;

    CategoryAdapter categoryAdapter;

    CategoryAdapter.onStateClickListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);


        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        recyclerView = view.findViewById(R.id.list_of_catalog);

        refreshLayout = view.findViewById(R.id.refresh_category);
        refreshLayout.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) this);
        refreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        initializeData();

        listener = (catalog, position) -> {
            Bundle args = new Bundle();
            args.putSerializable("category", catalog);
            ProductFragment productFragment = new ProductFragment();

            productFragment.setArguments(args);

            fragmentTransaction.replace(getId(), productFragment);
            fragmentTransaction.commit();
        };

        return view;
    }

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    private void initializeData() {
        WebService.getInstance().getCategoryApi().getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categories = response.body();
                categoryAdapter = new CategoryAdapter(listener, getLayoutInflater(), categories, getContext());
                recyclerView.setAdapter(categoryAdapter);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
            }
        });

        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        initializeData();
    }
}