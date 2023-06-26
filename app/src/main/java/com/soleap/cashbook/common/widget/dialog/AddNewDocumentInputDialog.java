package com.soleap.cashbook.common.widget.dialog;

import android.view.View;

import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.repository.RepositoryFactory;

public abstract class AddNewDocumentInputDialog<T extends Document> extends FullscreenDialog<T> {

    protected String documentName;

    public AddNewDocumentInputDialog(int viewLayout, String documentName) {
        super(viewLayout);
        this.documentName = documentName;
    }

    protected abstract T  readValueFromForm(View view);

    protected abstract void assignValueToForm(T value);

    @Override
    protected void onCheckButtonClick() {
        T doc = viewHolder.readValue();
//        RepositoryFactory.create().get(documentName).addNew(documentName, doc.toMap());
    }

    @Override
    protected DialogViewHolder<T> createViewHolder(View view) {
        return new DialogViewHolder<T>(view) {

            @Override
            protected void onBind(T value) {
                assignValueToForm(value);
            }

            @Override
            public T readValue() {
                return readValueFromForm(view);
            }
        };
    }

}