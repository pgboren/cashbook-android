package com.soleap.cashbook.common.widget.recyclerview;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.adapter.PagingRecyclerViewAdapter;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.PagingRecyclerViewData;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.util.DimensionUtils;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.viewholder.DocListItemViewHolder;
import com.soleap.cashbook.viewholder.DragDropListItemViewHolder;

import android.content.Context;
import android.widget.LinearLayout;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DragDropItemTouchRecyclerViewAdapter extends PagingRecyclerViewAdapter implements ItemMoveCallback.ItemTouchHelperContract {

    private StartDragListener startDragListener;

    public DragDropItemTouchRecyclerViewAdapter(Context context, String documentName, int viewResource, StartDragListener startDragListener) {
        super(context, documentName, viewResource);
        this.startDragListener = startDragListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof DragDropListItemViewHolder) {
            DragDropListItemViewHolder dragDropListItemViewHolder = (DragDropListItemViewHolder) holder;
            dragDropListItemViewHolder.imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() ==
                            MotionEvent.ACTION_DOWN) {
                        startDragListener.requestDrag(holder);
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public void loadPage(int page) {
        if (runnable != null) {
            handler.removeCallbacks(runnable);
            isLoading = true;
        }

        if (page == 1) {
            dataSet.clear();
            notifyDataSetChanged();
            dataSet.add(null);
            notifyItemInserted(dataSet.size() - 1);
        }
        runnable = new Runnable() {
            @Override
            public void run() {
                Map<String, Object> body = new HashMap<>();
                Map<String, Object> orders = getOrders();
                Map<String, Object> filter = getFilter();
                body.put("filter", filter);
                body.put("orders", orders);

                Call<PagingRecyclerViewData> call = apiInterface.listViewData("LIST_VIEW", documentName,page, 10, body);
                call.enqueue(new Callback<PagingRecyclerViewData>() {
                    @Override
                    public void onResponse(Call<PagingRecyclerViewData> call, Response<PagingRecyclerViewData> response) {
                        PagingRecyclerViewData pagingData = (PagingRecyclerViewData) response.body();
                        if (dataSet.size() > 0) {
                            dataSet.remove(dataSet.size()-1);
                            notifyItemRemoved(dataSet.size());
                        }
                        if (pagingData != null) {
                            dataSet.addAll(pagingData.getData());
                            maxPage = pagingData.getTotalPages();
                            notifyDataSetChanged();
                        }
                        isLoading = false;
                    }
                    @Override
                    public void onFailure(Call<PagingRecyclerViewData> call, Throwable t) {
                        Log.e("ERROR", t.getMessage(), t);
                        call.cancel();
                    }
                });

            }
        };

        new Handler().postDelayed(runnable, 2000);
    }

    @Override
    protected Map<String, Object> getOrders() {
        Map<String, Object> order = new HashMap<>();
        order.put("order", "asc");
        return order;
    }

    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(dataSet, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(dataSet, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        updateItemOrder();
    }

    private void updateItemOrder() {
        for (int i =0; i<dataSet.size(); i++) {
            Document doc = (Document) dataSet.get(i);
            Map<String, Object> itemAttributes = new HashMap<>();
            itemAttributes.put("order", i);
            apiInterface.patch(documentInfo.getName(), doc.getId(), itemAttributes).enqueue(new Callback<Map<String, Object>>() {
                @Override
                public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {

                }
                @Override
                public void onFailure(Call<Map<String, Object>> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void onRowSelected(DragDropListItemViewHolder myViewHolder) {
        myViewHolder.rowView.setBackgroundColor(context.getColor(R.color.gray_light));
    }

    @Override
    public void onRowClear(DragDropListItemViewHolder myViewHolder) {
        myViewHolder.rowView.setBackgroundColor(Color.WHITE);
    }

}
