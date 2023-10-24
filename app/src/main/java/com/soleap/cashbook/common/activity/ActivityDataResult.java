package com.soleap.cashbook.common.activity;

import java.io.Serializable;

public abstract class ActivityDataResult implements Serializable {

    public final static int ACTION_DELETED_CODE = 101;

    public final static int ACTION_EDITED_CODE = 102;

    public final static String DOC_ID_KEY = "DOC_ID_KEY";
    public final static String DOCUMENT_INFO_KEY = "DOCUMENT_INFO_KEY";

    public final static String DOC_POSITION_KEY = "DOC_POSITION_KEY";

    public final static String DOC_KEY = "DOC_KEY";

}
