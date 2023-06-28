package com.soleap.cashbook.common.widget.recyclerview;

import android.content.res.TypedArray;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.adapter.PagingRecyclerViewAdapter;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.util.DimensionUtils;
import com.soleap.cashbook.common.util.ResourceUtil;

import android.content.Context;
import android.widget.LinearLayout;

import java.util.Collections;
import java.util.List;

public class DragDropItemTouchRecyclerViewAdapter extends PagingRecyclerViewAdapter {


    public DragDropItemTouchRecyclerViewAdapter(Context context, String documentName, int viewResource) {
        super(context, documentName, viewResource);
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(dataSet, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(dataSet, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    public void onItemDismiss(int position) {
        dataSet.remove(position);
        notifyItemRemoved(position);
    }


    public class ItemLayout extends LinearLayout {

        private TextView txtSpecName;
        private TextView txtSpecValue;
        private TextView txtId;

        public ItemLayout(Context context) {
            super(context);
            initializeViews(context);
        }



        private void initializeViews(Context context) {
            setOrientation(LinearLayout.HORIZONTAL);
            setBackground(context.getDrawable(selectableItemBackground()));
            setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            setPadding(DimensionUtils.dpToPx(context, 5),DimensionUtils.dpToPx(context, 7),DimensionUtils.dpToPx(context, 5),DimensionUtils.dpToPx(context, 7));
            txtSpecName = new TextView(context);
            txtSpecName.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.3f));
            txtSpecName.setTextColor(ContextCompat.getColor(context, R.color.gray_dark));
            txtSpecName.setMaxLines(1);
            txtSpecName.setTextSize(12);
            txtSpecName.setText("Color");
            addView(txtSpecName);

            txtSpecValue = new TextView(context);
            txtSpecValue.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f));
            txtSpecValue.setTextColor(ContextCompat.getColor(context, R.color.gray_dark));
            txtSpecValue.setMaxLines(1);
            txtSpecValue.setText("Red");
            addView(txtSpecValue);

            ImageView imgView = new ImageView(context);
            imgView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.2f));
            imgView.setImageDrawable(context.getDrawable(R.drawable.ic_dragdrop));
            addView(imgView);
        }

        private int selectableItemBackground() {
            int[] attrs = new int[]{android.R.attr.selectableItemBackground};
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs);
            int backgroundResource = typedArray.getResourceId(0, 0);
            typedArray.recycle();
            return backgroundResource;
        }

        public void setSpecName(String specName) {
            txtSpecName.setText(ResourceUtil.getStringResourceByName(getContext(), specName));
        }

        public void setSpecValue(String specValue) {
            txtSpecValue.setText(specValue);
        }

    }

}
