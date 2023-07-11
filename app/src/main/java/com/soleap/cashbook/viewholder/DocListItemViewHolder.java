package com.soleap.cashbook.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.Global;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.value.ViewSetterFactory;

public abstract class DocListItemViewHolder extends RecyclerView.ViewHolder  implements ListItemViewHolder {

    protected Context context;
    private String docName;
    private String viewName;
    private int resourceFileLayout;

    protected int position;

    private Document doc;

    protected DocListItemViewHolderListner listener;

    public void setListener(DocListItemViewHolderListner listener) {
        this.listener = listener;
    }

    protected abstract void bindViewContent(Document doc);

    public DocListItemViewHolder(Context activity, View itemView, String viewName, String docName, int resourceFileLayout) {
        super(itemView);
        this.context = activity;
        this.docName = docName;
        this.viewName = viewName;
        this.resourceFileLayout = resourceFileLayout;
    }

    @Override
    public String getViewName() {
        return viewName;
    }

    @Override
    public String getDocName() {
        return docName;
    }

    @Override
    public int getResourceFileLayout() {
        return resourceFileLayout;
    }

    public void bind(int position, Document doc) {
        this.position = position;
        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
        String id = doc.getId();
        this.doc = doc;
        if (Global.build_type.equals("debug")) {
            itemView.findViewById(R.id.txt_id).setVisibility(View.VISIBLE);
            viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_id).setString((id));
        }
        TextView txtIndex = itemView.findViewById(R.id.txt_index);
        if (txtIndex != null) {
            txtIndex.setText(String.valueOf(position + 1));
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(DocListItemViewHolder.this.doc, position);
                }
            }
        });
        bindViewContent(this.doc);
    }

    public interface DocListItemViewHolderListner {
        void onClick(Document doc, int position);

        void onDelete(Document dcc, int position);

        void onEdit(Document doc, int position);

    }

}
