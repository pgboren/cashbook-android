package com.soleap.cashbook.common.view;

import android.view.View;
import android.view.ViewGroup;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.BsDocLookUpActivity;
import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.document.DocumentInfo;

public class ViewInstituteLooupFieldCreator extends ViewDocumentLooupFieldCreator {

    private View circleBox;
    private DocumentSnapshot selectedDoc = null;

    public void setSelectedDoc(DocumentSnapshot doc) {
        this.selectedDoc = doc;
        setSelectedDoc(this.selectedDoc);
    }

    public DocumentSnapshot getSelectedDoc() {
        return this.selectedDoc;
    }

    public ViewInstituteLooupFieldCreator(ViewDataActivity activity, ViewData fieldData) {
        super(activity, DocumentInfo.INSTITUE, BsDocLookUpActivity.LOOK_UP_INSTITUTE_REQUEST_CODE, fieldData, R.layout.lookup_item_bsdoc);
    }

    @Override
    protected void inflateLookupView(ViewGroup valueContainer) {
        super.inflateLookupView(valueContainer);
        circleBox = valueContainer.findViewById(R.id.circle_box);
    }

}
