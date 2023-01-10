package com.soleap.cashbook.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.common.document.Document;

import java.util.List;

public abstract class RestApiRecyclerViewAdapter<T extends Document, TV extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<TV> {

    public static String TAG = "RestApiRecyclerViewAdapter";

    protected List<T> dataSet;

    protected OnItemListener listener;

    public void setDataSet(List<T> dataSet) {
        this.dataSet = dataSet;
        this.notifyDataSetChanged();
    }

    public void remove(T data) {
        dataSet.remove(data);
        this.notifyDataSetChanged();
    }

    public void remove(int position) {
        this.dataSet.remove(position );
        this.notifyItemRemoved(position);
    }

    public void set(int position, T data) {
        dataSet.set(position, data);
        this.notifyItemChanged(position);
    }

    public void add(T data) {
        this.dataSet.add(data);
        this.notifyItemInserted(dataSet.size());
    }

    @Override
    public int getItemCount() {
        if (dataSet != null) {
            return dataSet.size();
        }
        return 0;
    }

    public void setListener(OnItemListener listener) {
        this.listener = listener;
    }

    public interface OnItemListener {
        void onItemSelected(int selectedIndex, Document selectedModel);
    }

}
