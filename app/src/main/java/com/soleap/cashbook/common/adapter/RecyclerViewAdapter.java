package com.soleap.cashbook.common.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.common.activity.RecyclerActivity;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.repository.DocumentSnapshotRepository;
import com.soleap.cashbook.common.repository.RepositoryFactory;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.viewholder.DocListItemViewHolder;
import com.soleap.cashbook.viewholder.ListItemViewHolderFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerViewAdapter<TV extends DocListItemViewHolder> extends RecyclerView.Adapter<TV>
        implements DocumentSnapshotRepository.OnListedDocumentListner,
        DocumentSnapshotRepository.OnCreatedDocumentListner,
        DocumentSnapshotRepository.OnDocumentChangedListner,
        DocumentSnapshotRepository.OnRemovedDocumentListner {

    public static String TAG = "RecyclerViewAdapter";
    public final static int ADD_NEW_ENTITY_REQUEST_CODE = 2001;

    protected final Context context;

    protected com.soleap.cashbook.view.View itemView;

    protected String documentName;
    protected TV viewHolder;

    protected List<Document> dataSet = new ArrayList<Document>();
    protected Class viewActivityClass;
    protected Class addNewActivityClass;
    protected int viewResource;

    protected DocumentSnapshotRepository repository;
    protected EventListner listner;

    public void setItemView(com.soleap.cashbook.view.View itemView) {
        this.itemView = itemView;
    }

    public void setListner(EventListner listner) {
        this.listner = listner;
    }

    public void setAddNewActivityClass(Class addNewActivityClass) {
        this.addNewActivityClass = addNewActivityClass;
    }

    public void setViewHolder(TV viewHolder) {
        this.viewHolder = viewHolder;
    }

    public RecyclerViewAdapter(Context context, String documentName, int viewResource) {
        this.documentName = documentName;
        this.context = context;
        this.viewResource = viewResource;
        initRepository();
    }

    protected void initRepository() {
        this.repository = RepositoryFactory.create().get(documentName);
        this.repository.setListDocumentListner(this);
        this.repository.addOnCreatedDocumentListner(documentName, this);
        this.repository.addOnChangedDocumentListner(documentName, this);
        this.repository.addOnCreatedDocumentListner(documentName, this);
        this.repository.addOnRemovedDocumentListner(documentName, this);
    }

    public void startListening() {
        this.repository.list();
        if (listner != null) {
            this.listner.onStartListening();
        }
    }

    public void stopListening() {
    }

    public void addNew() {
        Intent intent = new Intent(this.context, this.addNewActivityClass);
        intent.putExtra(DocumentName.DOCUMENT_NAME, documentName);
        ((RecyclerActivity) this.context).startActivityForResult(intent, ADD_NEW_ENTITY_REQUEST_CODE);
    }

    @Override
    public void onRemoved(String id) {
        int index = getItemIndex(id);
        if (index > -1) {
            dataSet.remove(index);
            notifyItemRemoved(index);
        }
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
        View view = LayoutInflater.from(parent.getContext()).inflate(itemView.getLayout(), parent, false);
        this.viewHolder = (TV) ListItemViewHolderFactory.create(context, view, itemView.getDocName());
        return viewHolder;
    }

    protected abstract TV createItemViewHolder(View view);

//    @Override
//    public void onBindViewHolder(@NonNull TV viewHolder, final int position) {
//        final Document documentSnapshot = dataSet.get(position);
//        viewHolder.bind(position, documentSnapshot);
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (listner != null) {
//                    listner.onItemSelected(documentSnapshot);
//                }
//            }
//        });
//    }

    @Override
    public int getItemCount() {
        if (dataSet != null) {
            return dataSet.size();
        }
        return 0;
    }

    public void setViewActivityClass(Class viewActivityClass) {
        this.viewActivityClass = viewActivityClass;
    }

    @Override
    public void onListed(List<Document> documentSnapshots) {
        this.dataSet = documentSnapshots;
        if (listner != null) {
            this.listner.onStopListening();
        }
        notifyDataSetChanged();
    }


    @Override
    public void onChanged(DocumentSnapshot documentSnapshot) {
        int position = this.getItemIndex(documentSnapshot.getId());
        this.dataSet.set(position, documentSnapshot);
        this.notifyDataSetChanged();
    }

    @Override
    public void onAdded(Document document) {
//        this.dataSet.add((DocumentSnapshot) documentSnapshot);
        notifyDataSetChanged();
    }

    @Override
    public void onError(Throwable t) {
        if (this.listner != null) {
            this.listner.onError(t);
        }
    }

    public void notifyActivityFinish() {
        this.repository.removeOnRemovedDocumentListner(documentName, this);
        this.repository.removeOnCreatedDocumentListner(documentName, this);
    }

    public interface EventListner {
        void onItemSelected(DocumentSnapshot documentSnapshot);
        void onStartListening();
        void onError(Throwable e);
        void onStopListening();
    }

}
