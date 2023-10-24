package com.soleap.cashbook.common.util;

import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.Media;

public interface FileUploaderCallback {
    void onSucess(Media media);
    void onFailure(Throwable t);
}
