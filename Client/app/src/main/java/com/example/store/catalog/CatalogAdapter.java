package com.example.store.catalog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.store.R;

import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder>{


    public interface onStateClickListener {
        void onStateClick(Catalog catalog, int position);
    }

    private final CatalogAdapter.onStateClickListener onStateClickListener;
    private final LayoutInflater inflater;
    private final List<Catalog> catalogs;

    public CatalogAdapter(CatalogAdapter.onStateClickListener onStateClickListener, LayoutInflater inflater, List<Catalog> catalogs) {
        this.onStateClickListener = onStateClickListener;
        this.inflater = inflater;
        this.catalogs = catalogs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_catalog_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Catalog catalog = catalogs.get(position);

        holder.name.setText(catalog.getName());
        holder.desc.setText(catalog.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStateClickListener.onStateClick(catalog, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return catalogs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name, desc;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.catalog_name);
            desc = itemView.findViewById(R.id.catalog_description);
        }
    }
}
