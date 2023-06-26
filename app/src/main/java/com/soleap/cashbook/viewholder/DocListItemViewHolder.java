package com.soleap.cashbook.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.Global;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.value.ViewSetterFactory;

public abstract class DocListItemViewHolder extends RecyclerView.ViewHolder  implements ListItemViewHolder {

    protected Context context;
    private String docName;
    private String viewName;
    private int resourceFileLayout;

    private DocumentSnapshot doc;

    private OnViewClickListner listener;

    public void setListener(OnViewClickListner listener) {
        this.listener = listener;
    }

    protected abstract void bindViewContent(DocumentSnapshot doc);

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

    public void bind(int position, DocumentSnapshot doc) {
        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
        String id = doc.getId();
        this.doc = doc;
        if (Global.build_type.equals("debug")) {
            itemView.findViewById(R.id.txt_id).setVisibility(View.VISIBLE);
            viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_id).setString((id));
        }
        TextView txtIndex = itemView.findViewById(R.id.txt_index);
        txtIndex.setText(String.valueOf(position + 1));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(doc, position);
                }
            }
        });
        bindViewContent(doc);
    }

    public interface OnViewClickListner {
        void onClick(DocumentSnapshot doc, int position);
    }

}
