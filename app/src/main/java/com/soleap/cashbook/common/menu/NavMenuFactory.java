package com.soleap.cashbook.common.menu;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import androidx.appcompat.view.menu.MenuBuilder;

import com.google.android.material.navigation.NavigationView;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.util.ResourceUtil;

public class NavMenuFactory {
    public static NavMenuFactory instance;

    public static NavMenuFactory getInstance() {
        if (instance == null) {
            instance = new NavMenuFactory();
        }
        return instance;
    }

    public void createAdminMenu(Context context, NavigationView navView) {
        Menu menu  = navView.getMenu();
        MenuItem transactionItem = menu.add(0, Menu.FIRST, Menu.NONE, R.string.nav_group_transaction);
        
    }



}
