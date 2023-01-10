package com.soleap.cashbook.common.value;

import android.view.View;
import android.widget.TextView;

public class ViewSetterFactory {

    public static ViewSetterFactory instance;
    private View parent;

    public ViewSetterFactory(View parent) {
        this.parent = parent;
    }

    public static ViewSetterFactory getInstance(View parent) {
          return new ViewSetterFactory(parent);
    }

    public ViewValueSettter create(ViewType type, TextView textView) {
        ViewValueSettter settter = null;
        if (type == ViewType.TEXTVIEW) {
            settter = new TextViewValueSettter(parent, textView);
        }
        return  settter;
    }

    public ViewValueSettter create(ViewType type, int id) {
        ViewValueSettter settter = null;
        if (type == ViewType.TEXTVIEW) {
            settter = new TextViewValueSettter(parent, id);
        }
        return  settter;
    }

}
