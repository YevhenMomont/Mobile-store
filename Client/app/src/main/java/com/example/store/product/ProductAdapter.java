package com.example.store.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.store.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


    public interface onStateClickListener {
        void onStateClick(Product product, int position);
    }

    private final onStateClickListener onStateClickListener;
    private final LayoutInflater inflater;
    private final List<Product> products;
    private final Context context;


    public ProductAdapter(onStateClickListener onStateClickListener, Context context, List<Product> products) {
        this.onStateClickListener = onStateClickListener;
        this.inflater = LayoutInflater.from(context);
        this.products = products;
        this.context = context;
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
        holder.name.setText(product.getTitle());
//        holder.description.setText(product.getDescription());
        holder.price.setText(String.valueOf(product.getPrice()));
        holder.ratingBar.setRating(product.getRating());
        holder.itemView.setOnClickListener(v -> onStateClickListener.onStateClick(product, position));
        Glide.with(context)
                .load(product.getImageUrl())
                .into(holder.imageView)
        ;
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name, price;
        final RatingBar ratingBar;
        final ImageView imageView;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.product_name);
            price = view.findViewById(R.id.product_price);
            ratingBar = view.findViewById(R.id.product_rating);
            imageView = view.findViewById(R.id.imageView);
        }
    }
}
