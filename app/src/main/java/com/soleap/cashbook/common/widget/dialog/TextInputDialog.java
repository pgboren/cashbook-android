package com.soleap.cashbook.common.widget.dialog;

import android.view.View;
import android.widget.EditText;

import com.soleap.cashbook.R;
import com.soleap.cashbook.document.Contact;

public class TextInputDialog extends FullscreenDialog<String> {

        public TextInputDialog(int viewLayout) {
            super(viewLayout);
        }

        @Override
        protected DialogViewHolder<String> createViewHolder(View view) {
            return new DialogViewHolder<String>(view) {
                @Override
                protected void onBind(String value) {
                    EditText txtValue = itemView.findViewById(R.id.txt_value);
                    txtValue.setText(value);
                }

                @Override
                public String readValue() {
                    EditText txtValue = itemView.findViewById(R.id.txt_value);
                    return txtValue.getText().toString();
                }
            };
        }
    }