package com.soleap.cashbook.common.repository;

import android.util.Log;

import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.PagingRecyclerViewData;
import com.soleap.cashbook.common.document.ViewDocumentSnapshot;
import com.soleap.cashbook.common.global.DocCreatedEventListner;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentRepository {

    public static String TAG = "DocumentSnapshotRepository";

    private String documentName;
    protected APIInterface apiInterface;

    public DocumentRepository(String entity) {
        this.documentName = entity;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void listItem(String id, DocumentEventListner callback) {
        Call<ViewDocumentSnapshot> call = apiInterface.listItemViewData(documentName,id);
        call.enqueue(new Callback<ViewDocumentSnapshot>() {
            @Override
            public void onResponse(Call<ViewDocumentSnapshot> call, Response<ViewDocumentSnapshot> response) {
                callback.onResponse(response);
            }
            @Override
            public void onFailure(Call<ViewDocumentSnapshot> call, Throwable t) {
                callback.onError(t);
                call.cancel();
            }
        });
    }

    public void list(int page, DocumentEventListner callback) {

    }

    public void patch(String documentName, String id, Map<String, Object> attributeVaules, DocumentEventListner callback) {
        Call<Map<String, Object>> call = apiInterface.patch(documentName, id, attributeVaules);
        call.enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                callback.onResponse(response);
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                callback.onError(t);
                call.cancel();
            }
        });
    }

    public void addNew(String documentName, Map<String, Object> data, DocumentEventListner callback) {
        Call<Map<String, Object>> call = apiInterface.post(documentName, data);
        call.enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                callback.onResponse(response);
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                callback.onError(t);
                call.cancel();
            }
        });
    }

    public void update(Document document) {

    }
    public void remove(final String entity, final String id, DocumentEventListner callback) {
        Call<Void> call = apiInterface.delete(entity, id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                callback.onResponse(response);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public interface DocumentEventListner {

        void onError(Throwable t);

        void onResponse(Response response);
    }

}
