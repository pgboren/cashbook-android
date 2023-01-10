package com.soleap.cashbook.common.document;

import java.util.List;

public class ViewDataGroup extends Document {

    private String label;
    private List<ViewData> values;
    private Action[] actions;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ViewData> getValues() {
        return values;
    }

    public void setValues(List<ViewData> values) {
        this.values = values;
    }

    public Action[] getActions() {
        return actions;
    }

    public void setActions(Action[] actions) {
        this.actions = actions;
    }
}
