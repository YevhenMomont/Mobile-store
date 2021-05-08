package com.example.store.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.store.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


    public interface onStateClickListener {
        void onStateClick(Product product, int position);
    }

    private final onStateClickListener onStateClickListener;
    private final LayoutInflater inflater;
    private final List<Product> products;


    public ProductAdapter(onStateClickListener onStateClickListener, Context context, List<Product> products) {
        this.onStateClickListener = onStateClickListener;
        this.inflater = LayoutInflater.from(context);
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_product_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.name.setText(product.getName());
//        holder.description.setText(product.getDescription());
        holder.price.setText(String.valueOf(product.getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStateClickListener.onStateClick(product, position);
            }
        });
//        holder.imageView.setImageBitmap(product.getImage());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name, price;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.product_name);
//            description = view.findViewById(R.id.product_description);
            price = view.findViewById(R.id.product_price);


        }
    }
}
