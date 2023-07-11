package com.soleap.cashbook.common.widget.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.viewholder.DocListItemViewHolder;
import com.soleap.cashbook.viewholder.ItemSpecListItemViewHolder;
import com.soleap.cashbook.viewholder.OneToManyListItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class OneToManyDocRecyclerViewAdapter extends RecyclerView.Adapter<DocListItemViewHolder> {

    private StartDragListener startDragListener;
    private Context context;
    private List<Document> dataSet = new ArrayList<Document>();
    private View itemView;

    private int itemheight = 0;

    private RecyclerView recyclerView;

    public int getItemheight() {
        return itemheight;
    }

    private int list_item_layout;

    public List<Document> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<Document> dataSet) {
        this.dataSet = dataSet;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    private void computeRecyclerViewHeight() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) recyclerView.getLayoutParams();
        layoutParams.height = getItemheight() * getItemCount() + (30 * (getItemCount() - 1));
        layoutParams.weight = 1;
        recyclerView.setLayoutParams(layoutParams);
    }

    public OneToManyDocRecyclerViewAdapter(Context context, int list_item_layout, StartDragListener startDragListener) {
        this.context = context;
        this.list_item_layout = list_item_layout;
        this.startDragListener = startDragListener;
    }

    @NonNull
    @Override
    public DocListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(list_item_layout, parent, false);
        itemView.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        );
        this.itemheight= itemView.getMeasuredHeight();
        OneToManyListItemViewHolder viewHolder = new ItemSpecListItemViewHolder(context, itemView);
        viewHolder.setListener(new DocListItemViewHolder.DocListItemViewHolderListner() {
            @Override
            public void onClick(Document doc, int position) {

            }

            @Override
            public void onDelete(Document dcc, int position) {
                dataSet.remove(position);
                notifyItemRemoved(position);
                computeRecyclerViewHeight();
            }

            @Override
            public void onEdit(Document doc, int position) {

            }
        });
        return viewHolder;
    }

    public void addItem(Document doc) {
        dataSet.add(doc);
        notifyItemInserted(dataSet.size());
        computeRecyclerViewHeight();
    }

    @Override
    public void onBindViewHolder(@NonNull DocListItemViewHolder holder, int position) {
        holder.bind(position, dataSet.get(position));
        if (holder instanceof OneToManyListItemViewHolder) {
            OneToManyListItemViewHolder dragDropListItemViewHolder = (OneToManyListItemViewHolder) holder;
            dragDropListItemViewHolder.imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() ==MotionEvent.ACTION_DOWN) {
                        startDragListener.requestDrag(holder);
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (dataSet == null) {
            return  0;
        }
        return dataSet.size();
    }
}
