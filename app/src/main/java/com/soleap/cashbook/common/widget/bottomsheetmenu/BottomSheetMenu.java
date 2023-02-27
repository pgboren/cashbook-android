package com.soleap.cashbook.common.widget.bottomsheetmenu;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.soleap.cashbook.R;

public class BottomSheetMenu extends BottomSheetDialog {

    private int mIconSize;
    private Menu menu;
    private String title;
    private int menuResource;
    private MenuItemViewHolder viewHolder;
    private ButtomSheetMenuItemClickListner itemClickListner;

    public BottomSheetMenu(@NonNull Context context, ButtomSheetMenuItemClickListner listener, Menu menu, int resource) {
        super(context);
        mIconSize = context.getResources().getDimensionPixelSize(R.dimen.bottom_sheet_menu_item_icon_size);
        this.menu = menu;
        this.itemClickListner = listener;
        this.menuResource = resource;
        this.setTitle(menu.getTitle());
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

    public void build(Menu menu, MenuItemViewHolder viewHolder) {
        this.viewHolder = viewHolder;
        final LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final LinearLayout rootView = (LinearLayout) View.inflate(getContext(), R.layout.view_bottom_sheet_menu, null);
        setContentView(rootView);
        for (int i = 0; i < menu.size(); i++) {
            rootView.addView(createMenuItemView(layoutInflater, rootView, menu.getItem(i)));
        }
    }



    private View createMenuItemView(final LayoutInflater inflater, ViewGroup rootView, final MenuItem item) {
        View itemView = inflater.inflate(this.menuResource, rootView, false);
        final TextView menuItemView = (TextView) itemView.findViewById(R.id.text);

        if (item.getIcon() != null) {
            menuItemView.setCompoundDrawablesWithIntrinsicBounds(item.getIcon(), null, null, null);
            menuItemView.setCompoundDrawablePadding(40);
        }

        menuItemView.setClickable(false);
        menuItemView.setText(item.getTitle());
        if (viewHolder != null) {
            viewHolder.bindView(itemView, item);
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListner != null) {
                    itemClickListner.onItemClick(item);
                }
                BottomSheetMenu.this.hide();
            }
        });
        return itemView;
    }

    public interface MenuItemViewHolder {
        void bindView(View itemView, MenuItem menuItem);
    }

    public interface ButtomSheetMenuItemClickListner {
        void onItemClick(MenuItem item);
    }

}