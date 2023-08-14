package com.soleap.cashbook.common.widget.view;

import android.view.View;
import android.view.ViewGroup;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.ViewData;

public class ViewInstituteLooupFieldCreator extends ViewDocumentLooupFieldCreator {

    private View circleBox;
    private DocumentSnapshot selectedDoc = null;

    public ViewInstituteLooupFieldCreator(ViewDataActivity activity, String documentName, int requestCode, ViewData fieldData, int value_layout) {
        super(activity, documentName, requestCode, fieldData, value_layout);
    }

    public void setSelectedDoc(DocumentSnapshot doc) {
        this.selectedDoc = doc;
        setSelectedDoc(this.selectedDoc);
    }

    public DocumentSnapshot getSelectedDoc() {
        return this.selectedDoc;
    }



    @Override
    protected void inflateLookupView(ViewGroup valueContainer) {
        super.inflateLookupView(valueContainer);
        circleBox = valueContainer.findViewById(R.id.short_name_view);
    }

}
