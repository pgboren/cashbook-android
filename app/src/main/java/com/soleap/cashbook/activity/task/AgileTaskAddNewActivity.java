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
import com.soleap.cashbook.common.repository.DocumentSnapshotRepository;
import com.soleap.cashbook.common.repository.RepositoryFactory;
import com.soleap.cashbook.common.widget.bottomsheetmenu.BottomSheetMenu;
import com.soleap.cashbook.common.widget.bottomsheetmenu.Menu;
import com.soleap.cashbook.common.widget.bottomsheetmenu.MenuItem;
import com.soleap.cashbook.document.AgileTask;
import com.soleap.cashbook.document.DocumentInfo;

import java.util.List;

public class AgileTaskAddNewActivity extends DocAddNewActivity implements BottomSheetMenu.ButtomSheetMenuItemClickListner {

    public static final String KEY_MODEL_ID = "key_stage_id";
    private TextInputLayout inputLayoutName;
    private TextInputEditText txtName;
    private TextInputLayout inputLayoutDesc;
    private TextInputEditText txtDesc;
    private BottomSheetMenu stageMenu;
    private String boardId = "63e33a686706e92dd049204c";
    private String stageId = "63e9dcec0759ba3dc06bfff4";

    @Override
    protected void setViewContent() {
        this.documentName = getIntent().getExtras().getString(DocumentInfo.DOCUMENT_NAME);
        setContentView(R.layout.activity_form_task);
        initInputView();
        buildStageButtomMenu();
    }

    @Override
    protected void initInputView() {
        inputLayoutName = findViewById(R.id.inputLayout_name);
        txtName = findViewById(R.id.txt_name);
        txtName.addTextChangedListener(this);
        inputLayoutDesc = findViewById(R.id.inputLayout_desc);
        txtDesc = findViewById(R.id.txt_desc);
    }

    @Override
    protected void readInputData(Document document) {
        AgileTask task = (AgileTask) document;
        task.setName(txtName.getText().toString());
        task.setBoard(boardId);
        task.setStage(stageId);
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
        return super.validation();
    }

    protected void buildStageButtomMenu() {
        DocumentSnapshotRepository repository = RepositoryFactory.create().get(DocumentInfo.AGILE_STAGE);
        repository.setListDocumentListner(new DocumentSnapshotRepository.OnListedDocumentListner() {
            @Override
            public void onListed(List<DocumentSnapshot> stageDocs) {
                Menu menu = new Menu();
                menu.setTitle("Stages");
                for (DocumentSnapshot stageDoc : stageDocs) {
                    menu.addItem(stageDoc.getId(), stageDoc.getDataValue("name").getValue().toString(), stageDoc);
                }
                menu.addItem("ALL", getString(R.string.all_records), null);
                stageMenu = new BottomSheetMenu(AgileTaskAddNewActivity.this, AgileTaskAddNewActivity.this, menu, R.layout.stage_menu_item);
                stageMenu.build(menu, new BottomSheetMenu.MenuItemViewHolder() {
                    @Override
                    public void bindView(View itemView, MenuItem menuItem) {
                        if (menuItem.getData() != null) {
                            DocumentSnapshot stageDoc = (DocumentSnapshot)menuItem.getData();
                            View colorView = itemView.findViewById(R.id.view_color);
                            colorView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(stageDoc.getDataValue("color").getValue().toString())));
                        }
                    }
                });
            }
            @Override
            public void onError(Throwable t) {
            }
        });
        repository.list();
    }

    @Override
    public void onItemClick(MenuItem item) {

    }
}
