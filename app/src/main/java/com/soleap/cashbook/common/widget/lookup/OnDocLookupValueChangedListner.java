package com.soleap.cashbook.common.widget.lookup;

import com.soleap.cashbook.common.document.DocumentSnapshot;

public interface OnDocLookupValueChangedListner {
    void onChanged(int lookupId, DocumentSnapshot value);
}
