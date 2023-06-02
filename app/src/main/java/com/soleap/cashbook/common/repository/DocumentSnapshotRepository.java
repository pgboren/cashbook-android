package com.soleap.cashbook.common.repository;

import android.util.Log;

import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.PagingRecyclerViewData;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.document.Branch;
import com.soleap.cashbook.document.Category;
import com.soleap.cashbook.document.Color;
import com.soleap.cashbook.document.Contact;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.document.Institute;
import com.soleap.cashbook.document.Item;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentSnapshotRepository {

    public static String TAG = "DocumentSnapshotRepository";

    private String documentName;
    protected APIInterface apiInterface;

    public void setPagingDocsRequestListner(OnGetPagingDocsRequestListner pagingDocsRequestListner) {
        this.pagingDocsRequestListner = pagingDocsRequestListner;
    }

    protected OnGetPagingDocsRequestListner pagingDocsRequestListner = null;

    protected OnListedDocumentListner listDocumentListner = null;
    protected OnViewedDocumentListner viewDocumentListner = null;
    protected OnGetDocumentListner getDocumentListner = null;

    private Map<String, List<OnCreatedDocumentListner>> addedDocumentListners = new HashMap<>();
    private Map<String, List<OnDocumentChangedListner>> changedDocumentListners = new HashMap<>();
    private Map<String, List<OnRemovedDocumentListner>> removeDocumentListners = new HashMap<>();

    public void setListDocumentListner(OnListedDocumentListner listDocumentListner) {
        this.listDocumentListner = listDocumentListner;
    }

    public void removeOnChangedDocumentListner(String key, OnDocumentChangedListner listner) {
        if (this.changedDocumentListners.containsKey(key)) {
            this.changedDocumentListners.get(key).remove(listner);
        }
    }

    public void addOnChangedDocumentListner(String key, OnDocumentChangedListner listner) {
        List<OnDocumentChangedListner> listners = null;
        if (!this.changedDocumentListners.containsKey(key)) {
            listners = new ArrayList<>();
            this.changedDocumentListners.put(key, listners);
        }
        else{
            listners = this.changedDocumentListners.get(key);
        }
        if (!listners.contains(listner)) {
            listners.add(listner);
        }
    }

    public void removeOnRemovedDocumentListner(String key, OnRemovedDocumentListner listner) {
        if (this.removeDocumentListners.containsKey(key)) {
            this.removeDocumentListners.get(key).remove(listner);
        }
    }

    public void addOnRemovedDocumentListner(String key, OnRemovedDocumentListner listner) {
        List<OnRemovedDocumentListner> listners = null;
        if (!this.removeDocumentListners.containsKey(key)) {
            listners = new ArrayList<>();
            this.removeDocumentListners.put(key, listners);
        }
        else{
            listners = this.removeDocumentListners.get(key);
        }
        if (!listners.contains(listner)) {
            listners.add(listner);
        }
    }

    public void removeOnCreatedDocumentListner(String key, OnCreatedDocumentListner listner) {
        if (this.addedDocumentListners.containsKey(key)) {
            this.addedDocumentListners.get(key).remove(listner);
        }
    }

    public void addOnCreatedDocumentListner(String key, OnCreatedDocumentListner listner) {
        List<OnCreatedDocumentListner> listners = null;
        if (!this.addedDocumentListners.containsKey(key)) {
            listners = new ArrayList<>();
            this.addedDocumentListners.put(key, listners);
        }
        else{
            listners = this.addedDocumentListners.get(key);
        }
        if (!listners.contains(listner)) {
            listners.add(listner);
        }
    }

    public DocumentSnapshotRepository(String entity) {
        this.documentName = entity;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void getViewData(String id, OnGetViewDataRequestListner listner) {
        Call<Map<String, Object>> call = apiInterface.getViewData("contact", id);
        call.enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                Map<String, Object> data = response.body();
                listner.onGet(data);
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                listner.onError(t);
            }
        });
    }

    public void list(int page) {
        Map<String, Object> body = new HashMap<>();
        Map<String, Object> order = new HashMap<>();
        order.put("name", "asc");
//        body.put("orders", order);

        Call<PagingRecyclerViewData> call = apiInterface.listViewData("LIST_VIEW", documentName,page, 10, body);
        call.enqueue(new Callback<PagingRecyclerViewData>() {
            @Override
            public void onResponse(Call<PagingRecyclerViewData> call, Response<PagingRecyclerViewData> response) {
                pagingDocsRequestListner.onGet(response.body());
            }
            @Override
            public void onFailure(Call<PagingRecyclerViewData> call, Throwable t) {
                pagingDocsRequestListner.onError(t);
                Log.e("ERROR", t.getMessage(), t );
                call.cancel();
            }
        });
    }

    private Call<DocumentSnapshot> createRestUpdateCall(String entity, Document document) {

        if (entity.equals(DocumentName.ITEM)) {
            return apiInterface.updateItem(document.getId(), (Item) document);
        }

        if (entity.equals(DocumentName.CATEGORY)) {
            return apiInterface.updateCategory(document.getId(), (Category) document);
        }

        if (entity.equals(DocumentName.INSTITUE)) {
            return apiInterface.updateInstitute(document.getId(), (Institute) document);
        }

        if (entity.equals(DocumentName.COLOR)) {
            return apiInterface.updateColor(document.getId(), (Color) document);
        }

        if (entity.equals(DocumentName.BRANCH)) {
            return apiInterface.updateBranch(document.getId(), (Branch) document);
        }

        if (entity.equals(DocumentName.CONTACT)) {
            return apiInterface.updateContact(document.getId(), (Contact) document);
        }

        throw new RuntimeException("Stub!");
    }

    private Call createRestGetCall(String document, String id) {

        if (documentName.equals(DocumentName.BRANCH)) {
            return apiInterface.getBranch(id);
        }

        if (documentName.equals(DocumentName.COLOR)) {
            return apiInterface.getColor(id);
        }

        if (documentName.equals(DocumentName.ITEM)) {
            return apiInterface.getItem(id);
        }

        if (documentName.equals(DocumentName.INSTITUE)) {
            return apiInterface.getInstitute(id);
        }

        if (documentName.equals(DocumentName.CATEGORY)) {
            return apiInterface.getCategory(id);
        }

        if (documentName.equals(DocumentName.CONTACT)) {
            return apiInterface.getContact(id);
        }
        throw new RuntimeException("Stub!");
    }

    public void get(final String document, String id, final OnGetDocumentListner listner) {
        Call call = createRestGetCall(document, id);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.code() == 200) {
                    Document document = (Document) response.body();
                    listner.onGet(document);
                    return;
                }
                throw new RuntimeException("Stub!");
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                throw new RuntimeException("Stub!");
            }
        });
    }

    public void patch(String documentName, String id, Map<String, Object> attributeVaules) {
        Call<DocumentSnapshot> call = apiInterface.patch(documentName, id, attributeVaules);
        call.enqueue(new Callback<DocumentSnapshot>() {
            @Override
            public void onResponse(Call<DocumentSnapshot> call, Response<DocumentSnapshot> response) {
                if (response.code() == 200) {
                    onDocumentChanged(response.body());
                }
                else {
                    try {
                        Log.e(TAG, response.errorBody().string());
                    }
                    catch (Exception ex) {
                        Log.e(TAG, ex.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<DocumentSnapshot> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                call.cancel();
            }
        });
    }

    public void addNew(String documentName, Map<String, Object> data) {
        Call<Document> call = apiInterface.post(documentName, data);
        call.enqueue(new Callback<Document>() {
            @Override
            public void onResponse(Call<Document> call, Response<Document> response) {
                if (response.code() == 200) {
                    Document doc = response.body();
                    List<OnCreatedDocumentListner> listners = addedDocumentListners.get(DocumentSnapshotRepository.this.documentName);
                    for (OnCreatedDocumentListner listner: listners) {
                        listner.onAdded(doc);
                    }
                }
                else {
                    try {
                        Log.e(TAG, response.errorBody().string());
                    }
                    catch (Exception ex) {
                        Log.e(TAG, ex.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Document> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                call.cancel();
            }
        });
    }

    public void add(Document document) {
        Call<Document> call = apiInterface.post(documentName, document.toMap());
        call.enqueue(new Callback<Document>() {
            @Override
            public void onResponse(Call<Document> call, Response<Document> response) {
                if (response.code() == 200) {
                    Document doc = response.body();
                    List<OnCreatedDocumentListner> listners = addedDocumentListners.get(documentName);
                    for (OnCreatedDocumentListner listner: listners) {
                        listner.onAdded(doc);
                    }
                }
                else {
                    try {
                        Log.e(TAG, response.errorBody().string());
                    }
                    catch (Exception ex) {
                        Log.e(TAG, ex.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Document> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                call.cancel();
            }
        });
    }

    public void update(Document document) {
        Call<DocumentSnapshot> call = createRestUpdateCall(documentName, document);
        call.enqueue(new Callback<DocumentSnapshot>() {
            @Override
            public void onResponse(Call<DocumentSnapshot> call, Response<DocumentSnapshot> response) {
                if (response.code() == 200) {
                    onDocumentChanged(response.body());
                }
                else {
                    try {
                        Log.e(TAG, response.errorBody().string());
                    }
                    catch (Exception ex) {
                        Log.e(TAG, ex.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<DocumentSnapshot> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                call.cancel();
            }
        });
    }

    private void onDocumentChanged(DocumentSnapshot doc) {
        List<OnDocumentChangedListner> listners = changedDocumentListners.get(documentName);
        for (OnDocumentChangedListner listner: listners) {
            listner.onChanged(doc);
        }
    }

    public void remove(final String entity, final String id) {
        Call<Void> call = apiInterface.delete(entity, id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                List<OnRemovedDocumentListner> listners = removeDocumentListners.get(entity);
                if (listners != null) {
                    for (OnRemovedDocumentListner listner: listners) {
                        listner.onRemoved(id);
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public interface DocumentEventListner {
        void onError(Throwable t);
    }

    public interface OnListedDocumentListner extends DocumentEventListner {
        void onListed(List<Document> documentSnapshots);
    }

    public interface OnViewedDocumentListner extends DocumentEventListner {
        void onViewed(DocumentSnapshot documentSnapshot);
    }

    public interface OnCreatedDocumentListner extends DocumentEventListner {
        void onAdded(Document docId);
    }

    public interface OnDocumentChangedListner extends DocumentEventListner {
        void onChanged(DocumentSnapshot documentSnapshot);
    }

    public interface OnRemovedDocumentListner extends DocumentEventListner {
        void onRemoved(String id);
    }

    public interface OnGetDocumentListner extends DocumentEventListner {
        void onGet(Document document);
    }

    public interface OnGetPagingDocsRequestListner extends DocumentEventListner {
        void onGet(PagingRecyclerViewData data);
    }

    public interface OnGetViewDataRequestListner extends DocumentEventListner {
        void onGet(Map<String, Object> data);
    }

}
