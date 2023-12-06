package com.soleap.cashbook.common.widget.recyclerview;

import android.graphics.Color;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.adapter.PagingRecyclerViewAdapter;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.PagingRecyclerViewData;
import com.soleap.cashbook.common.document.ViewDocumentSnapshot;
import com.soleap.cashbook.viewholder.OneToManyListItemViewHolder;

import android.content.Context;

import java.util.Collections;
import java.util.HashMap;
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

    }

    public void refresh() {
        loadPage(1);
    }
    public void insert(String id) {
        Call<ViewDocumentSnapshot> call = apiInterface.listItemViewData( documentName, id);
        call.enqueue(new Callback<ViewDocumentSnapshot>() {
            @Override
            public void onResponse(Call<ViewDocumentSnapshot> call, Response<ViewDocumentSnapshot> response) {
                dataSet.add(response.body());
                notifyItemInserted(dataSet.size() - 1);
            }

            @Override
            public void onFailure(Call<ViewDocumentSnapshot> call, Throwable t) {
            }
        });
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
//                Call<PagingRecyclerViewData> call = apiInterface.listViewData("LIST_VIEW", documentName,page, 10, body);
//                call.enqueue(new Callback<PagingRecyclerViewData>() {
//                    @Override
//                    public void onResponse(Call<PagingRecyclerViewData> call, Response<PagingRecyclerViewData> response) {
//                        PagingRecyclerViewData pagingData = (PagingRecyclerViewData) response.body();
//                        if (dataSet.size() > 0) {
//                            dataSet.remove(dataSet.size()-1);
//                            notifyItemRemoved(dataSet.size());
//                        }
//                        if (pagingData != null) {
//                            dataSet.addAll(pagingData.getData());
//                            maxPage = pagingData.getTotalPages();
//                            notifyDataSetChanged();
//                        }
//                        isLoading = false;
//                    }
//                    @Override
//                    public void onFailure(Call<PagingRecyclerViewData> call, Throwable t) {
//                        Log.e("ERROR", t.getMessage(), t);
//                        call.cancel();
//                    }
//                });

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
            apiInterface.patch(documentInfo.getName(), doc.get_id(), itemAttributes).enqueue(new Callback<Map<String, Object>>() {
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
    public void onRowSelected(OneToManyListItemViewHolder myViewHolder) {
//        myViewHolder.rowView.setBackgroundColor(context.getColor(R.color.gray_light));
    }

    @Override
    public void onRowClear(OneToManyListItemViewHolder myViewHolder) {
        myViewHolder.rowView.setBackgroundColor(Color.WHITE);
    }

}
