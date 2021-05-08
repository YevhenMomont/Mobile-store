package com.example.store.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.store.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    private final List<Product> products = new ArrayList<>() ;

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private BottomNavigationView bottomNavigationView;

    private Bundle args;

    private FragmentTransaction fragmentTransaction;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);

        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        refreshLayout = view.findViewById(R.id.refresh);

        recyclerView = view.findViewById(R.id.list_of_products);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        initiliazeData();

        ProductAdapter.onStateClickListener listener = new ProductAdapter.onStateClickListener() {
            @Override
            public void onStateClick(Product product, int position) {
                args = new Bundle();
                args.putSerializable("product", product);
                ProductDetailFragment fragment = new ProductDetailFragment();
                fragment.setArguments(args);


                bottomNavigationView.setVisibility(View.GONE);

                fragmentTransaction.replace(getId(), fragment);
                fragmentTransaction.commit();
            }
        };

        ProductAdapter productAdapter = new ProductAdapter(listener, getContext(), products);


        recyclerView.setAdapter(productAdapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initiliazeData();
            }
        });
        return view;
    }

    public static ProductFragment newInstance() {
        return new ProductFragment();
    }

    private void initiliazeData() {
        /*URL url = new URL("https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.goha.ru%2Fs%2FA%3ABC%2FC1%2FMy8mdJip6W.jpg&imgrefurl=https%3A%2F%2Fwww.goha.ru%2Fgames%2Fsubnautica-below-zero&tbnid=F26XZtQs5L2xmM&vet=12ahUKEwjnlovikPnvAhVICuwKHa1AD00QMygCegUIARCwAQ..i&docid=mt3IIziYB6pszM&w=1200&h=675&q=Subnautica%3A%20Below%20Zero%20%D0%B2%D1%8B%D1%85%D0%BE%D0%B4%20%D1%81%20%D0%B0%D0%BB%D1%8C%D1%84%D0%B0&client=opera&ved=2ahUKEwjnlovikPnvAhVICuwKHa1AD00QMygCegUIARCwAQ");
        Bitmap val = BitmapFactory.decodeStream(url.openConnection().getInputStream());*/
        /*for (int i = 0; i < 20; i++) {
            products.add(new Product("amog", "us", 3000d, ));
        }*/


    }
}
