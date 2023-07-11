package com.soleap.cashbook.common.widget.lookup;

import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.ViewDocumentSnapshot;

public interface DocumentListBottomSheetFragmentEventListner {
    void onItemSelected(Document documentSnapshot);
}
