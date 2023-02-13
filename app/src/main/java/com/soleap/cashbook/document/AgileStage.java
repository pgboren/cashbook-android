package com.soleap.cashbook.document;

import com.soleap.cashbook.common.document.BsDocument;

public class AgileStage extends BsDocument {

    public String name;
    public String color;
    public String order;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

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
