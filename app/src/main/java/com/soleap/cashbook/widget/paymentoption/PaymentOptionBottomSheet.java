package com.soleap.cashbook.widget.paymentoption;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.soleap.cashbook.R;

import javax.annotation.Nullable;

public class PaymentOptionBottomSheet extends BottomSheetDialogFragment {

        private PaymentOptionBottomSheetItemClickListener listener;

        public PaymentOptionBottomSheet(PaymentOptionBottomSheetItemClickListener listener) {
            this.listener = listener;

        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.paymentoption_bottom_sheet_menu, container, false);
            View fullPaymentView = view.findViewById(R.id.option_full_payment);
            fullPaymentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick("FULL_PAYMENT");
                    dismiss();
                }
            });

            View installmentPaymentView = view.findViewById(R.id.option_installment_payment);
            installmentPaymentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick("INSTALLMENT");
                    dismiss();
                }
            });
            return view;
        }
    }