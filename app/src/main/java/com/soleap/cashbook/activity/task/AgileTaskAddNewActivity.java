package com.soleap.cashbook.activity.task;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.soleap.cashbook.R;
import com.soleap.cashbook.activity.DocAddNewActivity;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.repository.DocumentSnapshotRepository;
import com.soleap.cashbook.common.repository.RepositoryFactory;
import com.soleap.cashbook.common.widget.DatePickerView;
import com.soleap.cashbook.common.widget.bottomsheetmenu.BottomSheetMenu;
import com.soleap.cashbook.common.widget.bottomsheetmenu.Menu;
import com.soleap.cashbook.common.widget.bottomsheetmenu.MenuItem;
import com.soleap.cashbook.common.widget.lookup.DocLookupTextInputView;
import com.soleap.cashbook.common.widget.lookup.OnDocLookupValueChangedListner;
import com.soleap.cashbook.document.AgileTask;
import com.soleap.cashbook.document.DocumentInfo;
import com.soleap.cashbook.widget.paymentoption.PaymentOptionBottomSheetView;

import java.util.List;

public class AgileTaskAddNewActivity extends DocAddNewActivity implements BottomSheetMenu.BottomSheetMenuItemClickListener, OnDocLookupValueChangedListner {

    public static final String KEY_MODEL_ID = "key_stage_id";
    private TextInputLayout inputLayoutName;
    private TextInputLayout inputLayoutDesc;
    private TextInputLayout inputLayoutPhonenumber;
    private TextInputLayout inputLayouyPrice;
    private TextInputEditText txtName;
    private TextInputEditText txtPhoneNumber;
    private TextInputEditText txtDesc;
    private TextInputEditText txtPrice;
    private DocLookupTextInputView luItem;
    private BottomSheetMenu stageMenu;
    private PaymentOptionBottomSheetView paymentOptionButtomSheetView;
    private String boardId = "63e33a686706e92dd049204c";
    private String stageId = "641065cb7a8514141033c121";
    private DatePickerView dtView;

    @Override
    protected void setViewContent() {
        this.documentName = getIntent().getExtras().getString(DocumentInfo.DOCUMENT_NAME);
        setContentView(R.layout.activity_form_task);
        initInputView();
    }

    @Override
    protected void initInputView() {
        inputLayoutName = findViewById(R.id.inputLayout_customer_name);
        inputLayoutDesc = findViewById(R.id.inputLayout_desc);
        inputLayouyPrice = findViewById(R.id.inputLayout_price);
        inputLayoutPhonenumber = findViewById(R.id.inputLayout_phonenumber);

        txtName = findViewById(R.id.txt_customer_name);
        luItem = findViewById(R.id.task_item);
        txtDesc = findViewById(R.id.txt_desc);
        txtPrice = findViewById(R.id.task_price);
        dtView = findViewById(R.id.task_datepicker);
        txtPhoneNumber = findViewById(R.id.txt_phonenumber);

        txtName.addTextChangedListener(this);
        txtPhoneNumber.addTextChangedListener(this);
        txtPrice.addTextChangedListener(this);

        paymentOptionButtomSheetView = findViewById(R.id.task_paymentOption);
        paymentOptionButtomSheetView.setFragmentManager(getSupportFragmentManager());
        luItem.registerForActivityResult(this);
        luItem.setOnValueChangedListner(this);
    }



    @Override
    protected void readInputData(Document document) {
        AgileTask task = (AgileTask) document;
        task.setName(txtName.getText().toString());
        task.setBoard(boardId);
        task.setStage(stageId);
        task.setItem(luItem.getValue().getId());
        task.setDate(dtView.getValue().getTimeInMillis());
        task.setPaymentOption(paymentOptionButtomSheetView.getValue());
        task.setPhoneNumber(txtPhoneNumber.getText().toString());
        task.setDescription(txtDesc.getText().toString());
    }

    @Override
    protected boolean validation() {
        isValid = true;
        if (txtName.getText().toString().isEmpty()) {
            inputLayoutName.setHelperText(getString(R.string.require));
            isValid = false;
        } else {
            inputLayoutName.setHelperText("");
            isValid = true;
        }

        if (txtPhoneNumber.getText().toString().isEmpty()) {
            inputLayoutPhonenumber.setHelperText(getString(R.string.require));
            isValid &= false;
        } else {
            inputLayoutPhonenumber.setHelperText("");
            isValid &= true;
        }

        if (txtPrice.getText().toString().isEmpty()) {
            inputLayouyPrice.setHelperText(getString(R.string.require));
            isValid &= false;
        } else {
            inputLayouyPrice.setHelperText("");
            isValid &= true;
        }

        return super.validation();
    }

    @Override
    public void onItemClick(MenuItem item) {

    }

    @Override
    public void onChanged(int lookupId, DocumentSnapshot doc) {
        if (lookupId == R.id.task_item) {
            ViewData data = doc.getDataValue("root").getDataValue("general");
            txtPrice.setText(data.getDataValue("price").getValue().toString());
        }
    }
}



//    protected void buildStageButtomMenu() {
//        DocumentSnapshotRepository repository = RepositoryFactory.create().get(DocumentInfo.AGILE_STAGE);
//        repository.setListDocumentListner(new DocumentSnapshotRepository.OnListedDocumentListner() {
//            @Override
//            public void onListed(List<DocumentSnapshot> stageDocs) {
//                Menu menu = new Menu();
//                menu.setTitle("Stages");
//                for (DocumentSnapshot stageDoc : stageDocs) {
//                    menu.addItem(stageDoc.getId(), stageDoc.getDataValue("name").getValue().toString(), stageDoc);
//                }
//                menu.addItem("ALL", getString(R.string.all_records), null);
//                stageMenu = new BottomSheetMenu(AgileTaskAddNewActivity.this, AgileTaskAddNewActivity.this, menu, R.layout.stage_menu_item);
//                stageMenu.build(menu, new BottomSheetMenu.MenuItemViewHolder() {
//                    @Override
//                    public void bindView(View itemView, MenuItem menuItem) {
//                        if (menuItem.getData() != null) {
//                            DocumentSnapshot stageDoc = (DocumentSnapshot)menuItem.getData();
//                            View colorView = itemView.findViewById(R.id.view_color);
//                            colorView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(stageDoc.getDataValue("color").getValue().toString())));
//                        }
//                    }
//                });
//            }
//            @Override
//            public void onError(Throwable t) {
//            }
//        });
//        repository.list();
//    }