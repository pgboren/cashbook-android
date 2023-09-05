package com.soleap.cashbook.common.util;

import com.soleap.cashbook.common.document.DocumentSnapshot;

public interface FileUploaderCallback {
    void onSucess(DocumentSnapshot.Media media);
    void onFailure(Throwable t);
}
