package com.soleap.cashbook.widget.agile;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.common.widget.OnValueChanged;
import com.soleap.cashbook.common.widget.docitems.DocItemsView;
import com.soleap.cashbook.widget.paymentoption.PaymentOptionBottomSheet;
import com.soleap.cashbook.widget.paymentoption.PaymentOptionBottomSheetItemClickListener;
import com.soleap.cashbook.widget.paymentoption.PaymentOptionView;
import com.soleap.cashbook.widget.saleorder.SaleOrderItemViewAdapter;

import java.util.ArrayList;

public class TaskVehicleItemView extends LinearLayout {

    private static String TAG = "PaymentOptionView";

    private OnValueChanged onValueChangedListner = null;

    private String value = null;

    private TextView tvValue;

    private FragmentManager fragmentManager;

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setOnValueChangedListner(OnValueChanged onValueChangedListner) {
        this.onValueChangedListner = onValueChangedListner;
    }

    public TaskVehicleItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.task_vehicle_item_view, this, true);

        View rootView = findViewById(R.id.root_view);

    }


}
