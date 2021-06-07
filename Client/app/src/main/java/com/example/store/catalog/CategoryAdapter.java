package com.example.store.catalog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.store.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{


    public interface onStateClickListener {
        void onStateClick(Category category, int position);
    }

    private final CategoryAdapter.onStateClickListener onStateClickListener;
    private final LayoutInflater inflater;
    private final List<Category> categories;
    private final Context context;

    public CategoryAdapter(CategoryAdapter.onStateClickListener onStateClickListener, LayoutInflater inflater, List<Category> categories, Context context) {
        this.onStateClickListener = onStateClickListener;
        this.inflater = inflater;
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_catalog_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categories.get(position);

        holder.name.setText(category.getTitle());
        holder.desc.setText(category.getDescription());
        holder.itemView.setOnClickListener(v -> onStateClickListener.onStateClick(category, position));
        Glide.with(context)
                .load(category.getImageUrl())
                .centerCrop()
                .into(holder.imageView)
        ;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name, desc;
        final ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.catalog_name);
            desc = itemView.findViewById(R.id.catalog_description);
            imageView = itemView.findViewById(R.id.catalog_image);
        }
    }
}
