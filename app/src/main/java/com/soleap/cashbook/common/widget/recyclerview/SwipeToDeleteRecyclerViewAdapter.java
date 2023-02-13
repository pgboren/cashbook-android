package com.soleap.cashbook.common.widget.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.widget.saleorder.SaleOrderItemViewAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class SwipeToDeleteRecyclerViewAdapter<VH extends SwipeToDeleteRecyclerViewHolder> extends RecyclerView.Adapter<VH> {

    private List<Document> data;

    public SwipeToDeleteRecyclerViewAdapter(List<Document> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(getItemViewResourceId(), parent, false);
        return createViewHolderView(itemView);
    }

    protected abstract int getItemViewResourceId();

    protected abstract VH createViewHolderView(View itemView);

    @Override
    public void onBindViewHolder(VH holder, int position) {

        holder.bind(data.get(position));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Document item, int position) {
        data.add(position, item);
        notifyItemInserted(position);
    }

    public List<Document> getData() {
        return data;
    }
}
