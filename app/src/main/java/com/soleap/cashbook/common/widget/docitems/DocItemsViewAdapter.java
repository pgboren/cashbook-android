package com.soleap.cashbook.common.widget.docitems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.common.document.Document;

import java.util.Collections;
import java.util.List;

public abstract class DocItemsViewAdapter<D extends Document, VH extends DocItemsViewHolder> extends RecyclerView.Adapter<VH> {

    private final Context context;
    private List<D> items = Collections.emptyList();

    public DocItemsViewAdapter(List<D> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(getItemViewResourceId(), parent, false);
        return createViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        D item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected abstract int getItemViewResourceId();

    protected abstract VH createViewHolder(View itemView);

}
