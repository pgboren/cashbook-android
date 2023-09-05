package com.soleap.cashbook.common.widget.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.common.viewholder.BaseViewHolder;
import com.soleap.cashbook.common.viewholder.OnRecyclerViewListner;

import java.util.List;

public abstract class BaseLookupRecyclerViewAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {

    private List<T> dataSet;

    private OnRecyclerViewListner listner;

    public void setListner(OnRecyclerViewListner listner) {
        this.listner = listner;
    }

    public void setDataSet(List<T> dataSet) {
        this.dataSet = dataSet;
    }

    protected abstract VH createViewHolder(View itemView, OnRecyclerViewListner listner);

    protected abstract int getItemLayout();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(getItemLayout(), parent, false);
        return createViewHolder(itemView, listner);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(dataSet.get(position), position);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
