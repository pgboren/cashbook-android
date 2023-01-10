package com.soleap.cashbook.activity;

import android.view.View;
import android.widget.TextView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.BsDocAddNewActivity;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.document.Color;
import com.soleap.cashbook.document.DocumentInfo;

import java.util.ArrayList;

import petrov.kristiyan.colorpicker.ColorPicker;

public class BsColorDocAddNewActivity extends BsDocAddNewActivity {

    private ArrayList<String> colorsHexList = new ArrayList<String>();

    @Override
    protected void readInputData(Document document) {
        Color color = (Color)document;
        super.readInputData(color);
        TextView txtCode = findViewById(R.id.txt_code);
        color.setCode(txtCode.getText().toString());
    }

    private void createColorCode() {
        colorsHexList.add("#7F1D1D");
        colorsHexList.add("#991B1B");
        colorsHexList.add("#B91C1C");
        colorsHexList.add("#DC2626");
        colorsHexList.add("#EF4444");
        colorsHexList.add("#F87171");

        colorsHexList.add("#713F12");
        colorsHexList.add("#854D0E");
        colorsHexList.add("#A16207");
        colorsHexList.add("#CA8A04");
        colorsHexList.add("#EAB308");
        colorsHexList.add("#FACC15");

        colorsHexList.add("#365314");
        colorsHexList.add("#3F6212");
        colorsHexList.add("#4D7C0F");
        colorsHexList.add("#65A30D");
        colorsHexList.add("#84CC16");
        colorsHexList.add("#A3E635");

        colorsHexList.add("#14532D");
        colorsHexList.add("#166534");
        colorsHexList.add("#15803D");
        colorsHexList.add("#16A34A");
        colorsHexList.add("#22C55E");
        colorsHexList.add("#4ADE80");

        colorsHexList.add("#134E4A");
        colorsHexList.add("#115E59");
        colorsHexList.add("#0F766E");
        colorsHexList.add("#0D9488");
        colorsHexList.add("#14B8A6");
        colorsHexList.add("#2DD4BF");

        colorsHexList.add("#164E63");
        colorsHexList.add("#155E75");
        colorsHexList.add("#0E7490");
        colorsHexList.add("#0891B2");
        colorsHexList.add("#06B6D4");
        colorsHexList.add("#22D3EE");

        colorsHexList.add("#312E81");
        colorsHexList.add("#3730A3");
        colorsHexList.add("#4338CA");
        colorsHexList.add("#4F46E5");
        colorsHexList.add("#6366F1");
        colorsHexList.add("#818CF8");

        colorsHexList.add("#581C87");
        colorsHexList.add("#6B21A8");
        colorsHexList.add("#7E22CE");
        colorsHexList.add("#9333EA");
        colorsHexList.add("#A855F7");
        colorsHexList.add("#C084FC");

        colorsHexList.add("#701A75");
        colorsHexList.add("#86198F");
        colorsHexList.add("#A21CAF");
        colorsHexList.add("#C026D3");
        colorsHexList.add("#D946EF");
        colorsHexList.add("#E879F9");

        colorsHexList.add("#831843");
        colorsHexList.add("#9D174D");
        colorsHexList.add("#BE185D");
        colorsHexList.add("#DB2777");
        colorsHexList.add("#EC4899");
        colorsHexList.add("#F472B6");

        colorsHexList.add("#881337");
        colorsHexList.add("#9F1239");
        colorsHexList.add("#BE123C");
        colorsHexList.add("#E11D48");
        colorsHexList.add("#F43F5E");
        colorsHexList.add("#FB7185");

        colorsHexList.add("#18181B");
        colorsHexList.add("#27272A");
        colorsHexList.add("#3F3F46");
        colorsHexList.add("#52525B");
        colorsHexList.add("#71717A");
        colorsHexList.add("#A1A1AA");

        colorsHexList.add("#0F172A");
        colorsHexList.add("#1E293B");
        colorsHexList.add("#334155");
        colorsHexList.add("#475569");
        colorsHexList.add("#64748B");
        colorsHexList.add("#94A3B8");
    }

    private void setSelectingColor(int color, String code) {
        final View viewColor = findViewById(R.id.view_color);
        final TextView txtCode = findViewById(R.id.txt_code);
        viewColor.setBackgroundColor(color);
        txtCode.setText(code);
        txtCode.setTextColor(color);
    }

    @Override
    protected void setViewContent() {
        this.documentName = getIntent().getExtras().getString(DocumentInfo.DOCUMENT_NAME);
        setContentView(R.layout.activity_form_color);
        initInputView();
        createColorCode();
        final View viewColor = findViewById(R.id.view_color);

        viewColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ColorPicker colorPicker = new ColorPicker(BsColorDocAddNewActivity.this);
                colorPicker.setOnFastChooseColorListener(new ColorPicker.OnFastChooseColorListener() {

                    @Override
                    public void setOnFastChooseColorListener(int position, int color) {
                        String colorText = colorsHexList.get(position);
                        setSelectingColor(color, colorText);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).setColumns(6).setColors(colorsHexList).show();

            }
        });
        int currentColor = android.graphics.Color.parseColor("#18181B");
        setSelectingColor(currentColor, "#18181B");
    }

    @Override
    protected void resetFields() {

    }
}
