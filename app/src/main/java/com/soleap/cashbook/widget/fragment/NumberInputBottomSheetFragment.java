package com.soleap.cashbook.widget.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.soleap.cashbook.R;
import com.soleap.cashbook.widget.NumberInputView;

public class NumberInputBottomSheetFragment extends BottomSheetDialogFragment {

    private String title;
    private final boolean isExpanded;
    private BottomSheetDialog bottomSheetDialog;

    private TextInputEditText txtValue;
    private BottomSheetFragmentListner listner;
    private double value;

    public NumberInputBottomSheetFragment(boolean isExpanded, String title) {
        super();
        this.isExpanded = isExpanded;
        this.title = title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View lookupView = inflater.inflate(R.layout.bottom_sheet_number_lookup_input_view, container, false);
        TextView tvTitle = lookupView.findViewById(R.id.tv_title);
        txtValue = lookupView.findViewById(R.id.txt_value);
        ImageButton btnClose = lookupView.findViewById(R.id.btn_close);
        ImageButton btnCheck = lookupView.findViewById(R.id.btn_check);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onDialogClosed();
                bottomSheetDialog.hide();

            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = Double.parseDouble(txtValue.getText().toString());
                listner.onDialogClosed();
                bottomSheetDialog.hide();
            }
        });
        tvTitle.setText(title);
        txtValue.setText(String.valueOf(value));
        return lookupView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        if (isExpanded) {
            bottomSheetDialog.setOnShowListener(dialog -> {
                FrameLayout bottomSheet = ((BottomSheetDialog) dialog).findViewById(com.google.android.material.R.id.design_bottom_sheet);
                if (bottomSheet != null) {
                    BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }

            });
        }
        return bottomSheetDialog;
    }




    public void setTitle(String title) {
        this.title = title;
    }

    public void setListner(BottomSheetFragmentListner listner) {
        this.listner = listner;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
        if (txtValue != null) {
            txtValue.setText(String.valueOf(value));
        }
    }
}
