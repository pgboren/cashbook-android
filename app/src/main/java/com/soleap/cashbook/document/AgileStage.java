package com.soleap.cashbook.document;

import com.soleap.cashbook.common.document.BsDocument;

public class AgileStage extends BsDocument {

    private String color;
    private String order;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
