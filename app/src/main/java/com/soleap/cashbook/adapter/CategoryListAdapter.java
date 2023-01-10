package com.soleap.cashbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.document.Category;

public class CategoryListAdapter extends RestApiRecyclerViewAdapter<Category, CategoryListAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_bsdoc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = (Category) dataSet.get(position);
        if (category != null) holder.onBind(position,category);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void onBind(final int position, final Category data) {
            TextView textPrefix = itemView.findViewById(R.id.circle_box);
            TextView txtName = itemView.findViewById(R.id.txt_name);
            txtName.setText(data.getName());
            textPrefix.setText(data.getName().substring(0, 1).toUpperCase());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemSelected(position, data);
                    }
                }
            });
        }
    }


}
