package com.soleap.cashbook.common.repository;

import android.util.Log;

import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.document.Branch;
import com.soleap.cashbook.document.Category;
import com.soleap.cashbook.document.Color;
import com.soleap.cashbook.document.Contact;
import com.soleap.cashbook.document.DocumentInfo;
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
import retrofit2.http.Body;
import retrofit2.http.Path;

public class DocumentSnapshotRepository {

    public static String TAG = "DocumentSnapshotRepository";

    private String entity;
    protected APIInterface apiInterface;

    private OnListedDocumentListner listDocumentListner = null;
    private OnViewedDocumentListner viewDocumentListner = null;
    private OnGetDocumentListner getDocumentListner = null;

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
        this.entity = entity;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void list() {
        Call<List<DocumentSnapshot>> call = apiInterface.listViewModel(this.entity);
        call.enqueue(new Callback<List<DocumentSnapshot>>() {
            @Override
            public void onResponse(Call<List<DocumentSnapshot>> call, Response<List<DocumentSnapshot>> response) {
                listDocumentListner.onListed(response.body());
            }

            @Override
            public void onFailure(Call<List<DocumentSnapshot>> call, Throwable t) {
                listDocumentListner.onError(t);
                Log.e("ERROR", t.getMessage(), t );
                call.cancel();
            }
        });
    }

    public void view() {

    }

    private Call<DocumentSnapshot> createRestUpdateCall(String entity, Document document) {

        if (entity.equals(DocumentInfo.ITEM)) {
            return apiInterface.updateItem(document.getId(), (Item) document);
        }

        if (entity.equals(DocumentInfo.CATEGORY)) {
            return apiInterface.updateCategory(document.getId(), (Category) document);
        }

        if (entity.equals(DocumentInfo.INSTITUE)) {
            return apiInterface.updateInstitute(document.getId(), (Institute) document);
        }

        if (entity.equals(DocumentInfo.COLOR)) {
            return apiInterface.updateColor(document.getId(), (Color) document);
        }

        if (entity.equals(DocumentInfo.BRANCH)) {
            return apiInterface.updateBranch(document.getId(), (Branch) document);
        }

        if (entity.equals(DocumentInfo.CONTACT)) {
            return apiInterface.updateContact(document.getId(), (Contact) document);
        }

        throw new RuntimeException("Stub!");
    }

    private Call<DocumentSnapshot> createRestCreateCall(String entity, Document document) {

        if (entity.equals(DocumentInfo.ITEM)) {
            return apiInterface.createItem((Item) document);
        }


        if (entity.equals(DocumentInfo.INSTITUE)) {
            return apiInterface.createInstitute((Institute) document);
        }

        if (entity.equals(DocumentInfo.COLOR)) {
            return apiInterface.creatColor((Color) document);
        }

        if (entity.equals(DocumentInfo.CATEGORY)) {
            return apiInterface.createCategory((Category) document);
        }

        if (entity.equals(DocumentInfo.CONTACT)) {
            return apiInterface.createContact((Contact) document);
        }

        if (entity.equals(DocumentInfo.BRANCH)) {
            return apiInterface.createBranch((Branch)document);
        }

        throw new RuntimeException("Stub!");
    }

    private Call createRestGetCall(String document, String id) {

        if (entity.equals(DocumentInfo.BRANCH)) {
            return apiInterface.getBranch(id);
        }

        if (entity.equals(DocumentInfo.COLOR)) {
            return apiInterface.getColor(id);
        }

        if (entity.equals(DocumentInfo.ITEM)) {
            return apiInterface.getItem(id);
        }

        if (entity.equals(DocumentInfo.INSTITUE)) {
            return apiInterface.getInstitute(id);
        }

        if (entity.equals(DocumentInfo.CATEGORY)) {
            return apiInterface.getCategory(id);
        }

        if (entity.equals(DocumentInfo.CONTACT)) {
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

        Call<DocumentSnapshot> call = apiInterface.post(documentName, data);
        call.enqueue(new Callback<DocumentSnapshot>() {
            @Override
            public void onResponse(Call<DocumentSnapshot> call, Response<DocumentSnapshot> response) {
                if (response.code() == 200) {
                    DocumentSnapshot documentSnapshot = response.body();
                    List<OnCreatedDocumentListner> listners = addedDocumentListners.get(entity);
                    for (OnCreatedDocumentListner listner: listners) {
                        listner.onAdded(documentSnapshot);
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
            public void onFailure(Call<DocumentSnapshot> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                call.cancel();
            }
        });
    }

    public void add(Document document) {
        Call<DocumentSnapshot> call = createRestCreateCall(entity, document);
        call.enqueue(new Callback<DocumentSnapshot>() {
            @Override
            public void onResponse(Call<DocumentSnapshot> call, Response<DocumentSnapshot> response) {
                if (response.code() == 200) {
                    DocumentSnapshot documentSnapshot = response.body();
                    List<OnCreatedDocumentListner> listners = addedDocumentListners.get(entity);
                    for (OnCreatedDocumentListner listner: listners) {
                        listner.onAdded(documentSnapshot);
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
            public void onFailure(Call<DocumentSnapshot> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                call.cancel();
            }
        });
    }

    public void update(Document document) {
        Call<DocumentSnapshot> call = createRestUpdateCall(entity, document);
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
        List<OnDocumentChangedListner> listners = changedDocumentListners.get(entity);
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
        void onListed(List<DocumentSnapshot> documentSnapshots);
    }

    public interface OnViewedDocumentListner extends DocumentEventListner {
        void onViewed(DocumentSnapshot documentSnapshot);
    }

    public interface OnCreatedDocumentListner extends DocumentEventListner {
        void onAdded(DocumentSnapshot documentSnapshot);
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

}
