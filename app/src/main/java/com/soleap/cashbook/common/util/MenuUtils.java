package com.soleap.cashbook.common.util;

import android.content.Context;
import android.view.Menu;

import androidx.appcompat.view.menu.MenuBuilder;

import java.lang.reflect.Constructor;

public class MenuUtils {
    public static Menu newMenuInstance(Context context) {
        try {
            Class<?> menuBuilderClass = Class.forName("com.android.internal.view.menu.MenuBuilder");
            Constructor<?> constructor = menuBuilderClass.getDeclaredConstructor(Context.class);
            return (Menu) constructor.newInstance(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
