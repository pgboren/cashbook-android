package com.soleap.cashbook.common.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.ViewDocumentSnapshot;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;
import com.soleap.cashbook.view.DocumentInfo;
import com.soleap.cashbook.viewholder.DocListItemViewHolder;
import com.soleap.cashbook.viewholder.ListItemViewHolderFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RecyclerViewAdapter<TV extends DocListItemViewHolder> extends RecyclerView.Adapter<TV> {

    public static String TAG = "RecyclerViewAdapter";
    public final static int ADD_NEW_ENTITY_REQUEST_CODE = 2001;

    protected APIInterface apiInterface;

    protected final Context context;

    protected DocumentInfo documentInfo;

    protected String documentName;
    protected TV viewHolder;

    protected List<Document> dataSet = new ArrayList<Document>();
    protected int viewResource;

    public void setDocumentInfo(DocumentInfo documentInfo) {
        this.documentInfo = documentInfo;
    }


    public RecyclerViewAdapter(Context context, String documentName, int viewResource) {
        this.documentName = documentName;
        this.context = context;
        this.viewResource = viewResource;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void startListening() {
    }

    public void onDocChanged(String id) {
        Call<ViewDocumentSnapshot> call = apiInterface.listItemViewData(documentName,id);
        call.enqueue(new Callback<ViewDocumentSnapshot>() {
            @Override
            public void onResponse(Call<ViewDocumentSnapshot> call, Response<ViewDocumentSnapshot> response) {
                ViewDocumentSnapshot doc = (ViewDocumentSnapshot) response.body();
                int position = getItemIndex(id);
                if (position > -1) {
                    dataSet.set(position, doc);
                    notifyItemChanged(position);
                }
            }
            @Override
            public void onFailure(Call<ViewDocumentSnapshot> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
                call.cancel();
            }
        });

    }

    public void onDocCreated(String id) {

        Call<ViewDocumentSnapshot> call = apiInterface.listItemViewData(documentName,id);
        call.enqueue(new Callback<ViewDocumentSnapshot>() {
            @Override
            public void onResponse(Call<ViewDocumentSnapshot> call, Response<ViewDocumentSnapshot> response) {
                ViewDocumentSnapshot doc = (ViewDocumentSnapshot) response.body();
                dataSet.add(doc);
                notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<ViewDocumentSnapshot> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
                call.cancel();
            }
        });

    }

    private int getItemIndex(String id) {
        for (int i = 0; i < dataSet.size(); i++) {
            if (dataSet.get(i).getId().equals(id)) {
                return i;
            }
        }
        return  -1;
    }

    @NonNull
    @Override
    public TV onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(documentInfo.getDocListViewDef().getList_item_layout(), parent, false);
        this.viewHolder = (TV) ListItemViewHolderFactory.create(context, view, documentInfo.getName());
        return viewHolder;
    }

    protected abstract TV createItemViewHolder(View view);

    @Override
    public int getItemCount() {
        if (dataSet != null) {
            return dataSet.size();
        }
        return 0;
    }

    public void notifyActivityFinish() {

    }

    public interface EventListner {
        void onItemSelected(DocumentSnapshot documentSnapshot);
        void onStartListening();
        void onError(Throwable e);
        void onStopListening();
    }

}
