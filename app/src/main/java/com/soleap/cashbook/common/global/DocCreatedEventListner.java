package com.soleap.cashbook.common.global;

public interface DocCreatedEventListner extends EventListner {

    void onAdded(String docId);

}
