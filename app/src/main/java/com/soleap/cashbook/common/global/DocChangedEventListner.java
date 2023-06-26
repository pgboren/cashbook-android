package com.soleap.cashbook.common.global;

public interface DocChangedEventListner extends EventListner {

    void onChanged(String docId);

}
