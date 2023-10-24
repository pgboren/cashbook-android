package com.soleap.cashbook.common.widget.bottomsheetmenu;

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

    private int menu_item_layout;
    private MenuItemViewHolder viewHolder;
    private BottomSheetMenuItemClickListener itemClickListner;

    public BottomSheetMenu(@NonNull Context context, BottomSheetMenuItemClickListener listener, Menu menu, int menu_item_layout) {
        super(context);
        mIconSize = context.getResources().getDimensionPixelSize(R.dimen.bottom_sheet_menu_item_icon_size);
        this.menu = menu;
        this.itemClickListner = listener;
        this.menu_item_layout = menu_item_layout;
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
        build(menu, null);
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
        View itemView = inflater.inflate(this.menu_item_layout, rootView, false);
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

    public interface BottomSheetMenuItemClickListener {
        void onItemClick(MenuItem item);
    }



}