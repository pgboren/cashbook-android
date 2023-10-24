package com.soleap.cashbook.common.global;

public interface DocRemovedEventListner extends EventListner {

    void onRemove(String docId, int position);

}
