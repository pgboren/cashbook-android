package com.soleap.cashbook.common.document;

import java.util.Map;

public class ViewDocumentSnapshot extends DocumentSnapshot {

    private String docClassName;

    public String getDocClassName() {
        return docClassName;
    }

    public void setDocClassName(String docClassName) {
        this.docClassName = docClassName;
    }
}
