package com.soleap.cashbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.document.Category;

public class CategoryAutoCompleteAdapter extends ListAdapter<Category, CategoryAutoCompleteAdapter.ListItemViewHolder> implements Filterable {

    @Override
    public Filter getFilter() {
        return null;
    }

    public CategoryAutoCompleteAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Category> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Category>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull Category oldUser, @NonNull Category newUser) {
                    // User properties may have changed if reloaded from the DB, but ID is fixed
                    return oldUser.getId() == newUser.getId();
                }
                @Override
                public boolean areContentsTheSame(
                        @NonNull Category oldUser, @NonNull Category newUser) {
                    // NOTE: if you use equals, your object must properly override Object#equals()
                    // Incorrectly returning false here will result in too many animations.
                    return oldUser.getName().equals(newUser.getName());
                }
            };

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_bsdoc, parent, false);
        return new ListItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {

    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder {
        public ListItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
