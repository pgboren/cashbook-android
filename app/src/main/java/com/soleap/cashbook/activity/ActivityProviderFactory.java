package com.soleap.cashbook.activity;

import com.soleap.cashbook.activity.task.AgileTaskViewActivity;
import com.soleap.cashbook.common.activity.BsDocViewActivity;
import com.soleap.cashbook.document.DocumentName;

public class ActivityProviderFactory {

    public static Class getViewActivity(String entityName) {

        if (entityName.equals(DocumentName.AGILE_TASK)) {
            return AgileTaskViewActivity.class;
        }

        if (entityName.equals(DocumentName.CONTACT)) {
            return BsDocViewActivity.class;
        }

        if (entityName.equals(DocumentName.ITEM)) {
            return BsDocViewActivity.class;
        }

        if (entityName.equals(DocumentName.COLOR)) {
            return BsDocViewActivity.class;
        }

        if (entityName.equals(DocumentName.BRANCH)) {
            return BsDocViewActivity.class;
        }

        if (entityName.equals(DocumentName.INSTITUE)) {
            return BsDocViewActivity.class;
        }

        if (entityName.equals(DocumentName.CATEGORY)) {
            return BsDocViewActivity.class;
        }

        return null;
    }
}
