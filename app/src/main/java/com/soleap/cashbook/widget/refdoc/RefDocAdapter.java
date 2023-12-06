package com.soleap.cashbook.widget.refdoc;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.Global;
import com.soleap.cashbook.common.document.RefDocument;
import com.soleap.cashbook.document.Category;
import com.soleap.cashbook.view.DocumentInfo;

import java.util.List;

public class RefDocAdapter extends RecyclerView.Adapter<RefDocItemViewHolder> {

    private EventListner listner;
    private List<RefDocument> refDocs;
    private DocumentInfo documentInfo;

    public RefDocAdapter(Context context, String documentName, EventListner listner) {
        this.documentInfo = DocumentInfo.getDocumentInfo(documentName);
        this.listner = listner;
        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
            }

            @Override
            protected String doInBackground(Void... params) {
                refDocs = Global.db.refDocumentDao().loadAllByType(documentInfo.getName());
                return "Task completed!";
            }

            @Override
            protected void onPostExecute(String result) {

            }
        }.execute();

    }

    @NonNull
    @Override
    public RefDocItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(documentInfo.getDocListViewDef().getList_item_layout(), parent, false);
        return new RefDocItemViewHolder(view, listner);
    }

    @Override
    public void onBindViewHolder(@NonNull RefDocItemViewHolder holder, int position) {
        holder.bind(refDocs.get(position));
    }

    @Override
    public int getItemCount() {
        return  refDocs == null ? 0 : refDocs.size();
    }

    public interface EventListner {
        void onItemClick(RefDocument refDoc, int position);
    }

}
