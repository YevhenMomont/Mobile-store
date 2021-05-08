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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CatalogFragment extends Fragment {

    private final List<Catalog> catalogs = new ArrayList<>();

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;

    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);


        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        refreshLayout = view.findViewById(R.id.refresh);

        recyclerView = view.findViewById(R.id.list_of_catalog);

        initializeData();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initializeData();
            }
        });

        CatalogAdapter.onStateClickListener listener = new CatalogAdapter.onStateClickListener() {
            @Override
            public void onStateClick(Catalog catalog, int position) {
                fragmentTransaction.replace(getId(), ProductFragment.newInstance());
                fragmentTransaction.commit();
            }
        };


        CatalogAdapter catalogAdapter = new CatalogAdapter(listener, inflater, catalogs);

        recyclerView.setAdapter(catalogAdapter);


        return view;
    }

    public static CatalogFragment newInstance() {
        return new CatalogFragment();
    }

    private void initializeData() {
        for (int i = 0; i < 10; i ++) {
            catalogs.add(new Catalog( UUID.randomUUID(), "Catalog name", "texttexttexttexttexttexttexttexttexttexttext"));
        }
    }
}