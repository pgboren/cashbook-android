package com.soleap.cashbook.activity;

import com.soleap.cashbook.common.activity.BsDocViewActivity;
import com.soleap.cashbook.document.DocumentInfo;

public class ActivityProviderFactory {

    public static Class getViewActivity(String entityName) {

        if (entityName.equals(DocumentInfo.CONTACT)) {
            return BsDocViewActivity.class;
        }

        if (entityName.equals(DocumentInfo.ITEM)) {
            return BsDocViewActivity.class;
        }

        if (entityName.equals(DocumentInfo.COLOR)) {
            return BsDocViewActivity.class;
        }

        if (entityName.equals(DocumentInfo.BRANCH)) {
            return BsDocViewActivity.class;
        }

        if (entityName.equals(DocumentInfo.INSTITUE)) {
            return BsDocViewActivity.class;
        }

        if (entityName.equals(DocumentInfo.CATEGORY)) {
            return BsDocViewActivity.class;
        }

        return null;
    }
}
