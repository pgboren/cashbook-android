package com.soleap.cashbook.widget.bottomsheetmenu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.soleap.cashbook.R;

import java.lang.reflect.Constructor;

public class BottomSheetMenu extends BottomSheetDialog {

    private final int menu;
    private final int mIconSize;
    private final Activity listener;
    private String title;
    private int menuResource;

    public static class Builder {

        private final Context mContext;
        private final Activity listener;
//        private final BottomSheetMenuListener mBottomSheetMenuListener;
        private String title ;
        private int menu;
        private int menuResource;

        public Builder(@NonNull Context context, Activity listener,String title, final int menu, final int menuResource) {
            mContext = context;
            this.listener = listener;
            this.menu = menu;
            this.menuResource = menuResource;
            this.title = title;
        }

        private BottomSheetMenu create(String title) {
            this.title = title;
            final BottomSheetMenu menu = new BottomSheetMenu(mContext, listener, title, this.menu, this.menuResource);
            menu.build();
            return menu;
        }

        @SuppressWarnings("UnusedReturnValue")
        public BottomSheetMenu create() {
            final BottomSheetMenu menu = create(title);
            return menu;
        }
    }

    @SuppressLint("InflateParams")
    private BottomSheetMenu(@NonNull Context context, Activity listener, String title, int menu, int resource) {
        super(context);
        mIconSize = context.getResources().getDimensionPixelSize(R.dimen.bottom_sheet_menu_item_icon_size);
        this.title = title;
        this.menu = menu;
        this.listener = listener;
        this.menuResource = resource;
        setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                BottomSheetDialog d = (BottomSheetDialog) dialog;
                FrameLayout bottomSheet = (FrameLayout) d.findViewById(R.id.design_bottom_sheet);
                if (bottomSheet != null) {
                    BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });
    }

    private void build() {
        final LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final Menu menu = newMenuInstance(getContext());

        if (menu == null) {
            return;
        }

        final MenuInflater menuInflater = new MenuInflater(getContext());
        menuInflater.inflate(this.menu, menu);

        final LinearLayout rootView = (LinearLayout) View.inflate(getContext(), R.layout.view_bottom_sheet_menu, null);
//        TextView txtTitle = rootView.findViewById(R.id.title);
//        txtTitle.setText(title);

        setContentView(rootView);
        for (int i = 0; i < menu.size(); i++) {
            rootView.addView(createMenuItemView(layoutInflater, rootView, menu.getItem(i)));
        }
    }

    private View createMenuItemView(final LayoutInflater inflater, ViewGroup rootView, final MenuItem item) {
        View itemView = inflater.inflate(this.menuResource, rootView, false);
        final TextView menuItemView = (TextView) itemView.findViewById(R.id.text);
        menuItemView.setCompoundDrawablesWithIntrinsicBounds(item.getIcon(), null, null, null);
        menuItemView.setCompoundDrawablePadding(40);
        menuItemView.setClickable(false);
        menuItemView.setText(item.getTitle());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOptionsItemSelected(item);
                BottomSheetMenu.this.hide();
            }
        });
        return itemView;
    }

    @SuppressLint("PrivateApi")
    private Menu newMenuInstance(Context context) {
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