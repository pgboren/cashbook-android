package com.soleap.cashbook.common.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.repository.DocumentSnapshotRepository;
import com.soleap.cashbook.common.repository.RepositoryFactory;
import com.soleap.cashbook.common.widget.view.ActivityEventListner;
import com.soleap.cashbook.document.DocumentInfo;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;
import com.soleap.cashbook.common.widget.view.ViewFieldCreatorFactory;
import com.soleap.cashbook.common.widget.bottomsheetmenu.BottomSheetMenu;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ViewDataActivity<T extends DocumentSnapshot> extends AppCompatActivity implements DocumentSnapshotRepository.OnDocumentChangedListner {

    public final static int PICK_IMAGE_REQUEST_CODE = 104;

    private static final String TAG = "ModelViewActivity";
    public static final String KEY_MODEL = "key_model";
    public static final String KEY_MODEL_ID = "key_model_id";
    public static final String KEY_SELECTED_POSITION = "key_selected_position";
    public static final String KEY_MODIFIED_FLAG = "key_modofied_flag";
    public static final String KEY_DELETED_FLAG = "key_deleted_flag";
    public final static int EDIT_ACTIVITY_REQUEST_CODE = 103;
    private String photoFileName = "new_capture_product_image.jpg";

    public String documentName;
    private  Toolbar toolbar;
    protected boolean isModelEdited = false;
    protected boolean isModelDeleted = false;
    protected View loadingScreen;
    public String modelId = null;
    protected DocumentSnapshot model;
    protected APIInterface apiInterface;
    protected BottomSheetMenu buttomActionsMenu;
    protected Button btnDelete;
    protected ImageView imageView;

    private List<ActivityEventListner> listners = new ArrayList<>();

    protected Call<DocumentSnapshot> createGetApi() {
        return apiInterface.viewModel(documentName.toLowerCase(), modelId);
    }

    protected String getViewTitle() {
        return documentName;
    }

    protected void setViewContent() {
        setContentView(R.layout.activity_view_doc);
    }

    protected String getStringResourceByName(String aString) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        if (resId == 0) {
            return aString;
        }
        return getString(resId);
    }

    protected String getDeleteAlertDialogTitle() {
        String deleteMessageFormat = getString(R.string.delete_alert_dialog_title);
        return String.format(deleteMessageFormat, getStringResourceByName(this.documentName));
    }
    
    protected String getDeleteAlertDialogMessage() {
        String deleteMessageFormat = getString(R.string.delete_alert_dialog_message);
        return String.format(deleteMessageFormat, getStringResourceByName(this.documentName));
    }

    protected void loadDataFromServer() {
        showLoadingScreen();
        Call<DocumentSnapshot> call = createGetApi();
        call.enqueue(new Callback<DocumentSnapshot>() {
            @Override
            public void onResponse(Call<DocumentSnapshot> call, Response<DocumentSnapshot> response) {
                if (response.isSuccessful()) {
                    model = response.body();
                    assignValueToControls(model);
                    hideLoadingScreen();
                }
            }
            @Override
            public void onFailure(Call<DocumentSnapshot> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                call.cancel();
                hideLoadingScreen();
            }
        });
    }

    protected void startEditActivity() {
        Intent intent = new Intent(this, DocumentInfo.getInstance(this).getEditActivityClass(documentName));
        intent.putExtra(EditRestApiActivity.KEY_MODEL_ID, this.model.getId());
        intent.putExtra(DocumentInfo.DOCUMENT_NAME, documentName);
        this.startActivityForResult(intent, EDIT_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.documentName = getIntent().getExtras().getString(DocumentInfo.DOCUMENT_NAME);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        setViewContent();
        modelId = getIntent().getExtras().getString(KEY_MODEL_ID);
        if (modelId == null) {
            Log.e(TAG, "Must pass extra " + KEY_MODEL);
            throw new IllegalArgumentException("Must pass extra " + KEY_MODEL);
        }
        this.toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbar);
        loadingScreen = this.findViewById(R.id.loadingScreen);
        loadDataFromServer();
        RepositoryFactory.create().get(documentName).addOnChangedDocumentListner(documentName, this);
    }

    protected void refresh() {
        loadDataFromServer();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    protected void onDeleteOptionMenuClick() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle(getDeleteAlertDialogTitle());
        builder1.setMessage(Html.fromHtml("<font color='#8d8d8d'>" + getDeleteAlertDialogMessage() + "</font>"));
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                Html.fromHtml("<font color='#f44336'>" + getString(R.string.ok) + "</font>"),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        delete();
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    protected void delete() {
        RepositoryFactory.create().get(documentName).remove(documentName, model.getId());
        finish();
    }

    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(KEY_MODIFIED_FLAG, isModelEdited);
        returnIntent.putExtra(KEY_DELETED_FLAG, isModelDeleted);

        if (!isModelDeleted) {
            returnIntent.putExtra(KEY_MODEL, model);
        }
        setResult(Activity.RESULT_OK, returnIntent);
        RepositoryFactory.create().get(documentName).removeOnChangedDocumentListner(documentName, this);
        super.finish();
    }

    public void hideLoadingScreen() {
        loadingScreen.setVisibility(View.GONE);
    }

    public void showLoadingScreen() {
        loadingScreen.setVisibility(View.VISIBLE);
    }

    protected void assignValueToControls(DocumentSnapshot doc) {
        setTitle(getStringResourceByName(documentName) + " - " +  doc.getTitle());
        renderViewData(doc);
        renderOptionButtonActionMenu(doc);
    }

    protected void renderOptionButtonActionMenu(DocumentSnapshot doc) {
        String menu = doc.getContextMenu();
        int menuId = this.getResources().getIdentifier(menu, "menu", getPackageName());
//TODO
//        buttomActionsMenu = new BottomSheetMenu.Builder(this, this, "", menuId, R.layout.text_menu_item).create();
    }

    protected void renderViewData(DocumentSnapshot doc) {
        LinearLayout rootView = findViewById(R.id.content_container);
        rootView.removeAllViews();
        for (ViewData data: doc.getDataValue("root").getChildrent().values()) {
            if (data.getVisible() == View.VISIBLE) {
                rootView.addView(ViewFieldCreatorFactory.getInstance(this).create(this, data).createView());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        for (ActivityEventListner listner:listners) {
            listner.onActivityResult(requestCode, resultCode, intent);
        }
    }


    @Override
    public void onError(Throwable t) {
    }

    @Override
    public void onChanged(DocumentSnapshot documentSnapshot) {
        renderViewData((T) documentSnapshot);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.view_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
//
//        if (id == R.id.action_upload_photo) {
//        }
//
//        if (id == R.id.action_view_edit) {
//            startEditActivity();
//        }
//
//        if (id == R.id.action_view_delete) {
//            onDeleteOptionMenuClick();
//        }

        if (id == R.id.view_option_action_more_menuitem) {
            if (buttomActionsMenu != null) {
                buttomActionsMenu.show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void  addActivityEventListner(ActivityEventListner listner) {
        listners.add(listner);
    }
}
