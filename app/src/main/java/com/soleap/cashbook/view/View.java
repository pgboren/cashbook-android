package com.soleap.cashbook.view;

public class View {

    private String docName;

    private int layout;

    private String type;

    public View(String type, String docName, int layout) {
        this.type = type;
        this.docName = docName;
        this.layout = layout;
    }

    public String getDocName() {
        return docName;
    }

    public int getLayout() {
        return layout;
    }

    public String getType() {
        return type;
    }
}
