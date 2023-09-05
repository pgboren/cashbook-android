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
import com.soleap.cashbook.common.document.ViewDocumentSnapshot;
import com.soleap.cashbook.common.global.DocChangedEventListner;
import com.soleap.cashbook.common.global.EventHandler;
import com.soleap.cashbook.common.repository.DocumentRepository;
import com.soleap.cashbook.common.repository.RepositoryFactory;
import com.soleap.cashbook.common.util.PDFUtil;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.common.widget.view.ActivityEventListner;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;
import com.soleap.cashbook.common.widget.view.ViewFieldCreatorFactory;
import com.soleap.cashbook.common.widget.bottomsheetmenu.BottomSheetMenu;
import com.soleap.cashbook.view.DocumentInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ViewDataActivity<T extends DocumentSnapshot> extends AppCompatActivity {

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
    private Toolbar toolbar;
    protected boolean isModelEdited = false;
    protected boolean isModelDeleted = false;
    protected View loadingScreen;
    public String modelId = null;

    protected DocumentInfo documentInfo;
    protected ViewDocumentSnapshot viewDoc;

    protected APIInterface apiInterface;

    private DocChangedEventListner docChangedEventListner = new DocChangedEventListner() {
        @Override
        public void onChanged(String docId) {
            if (docId.equals(docId)) {
                refresh();
            }
        }

        @Override
        public void onError(Throwable t) {

        }
    };
    protected Call<ViewDocumentSnapshot> createGetApi() {
        return apiInterface.viewData(documentInfo.getName().toString().toLowerCase(), modelId);
    }

    protected String getViewTitle() {
        return documentName;
    }

    protected void setViewContent() {
        setContentView(R.layout.activity_view_doc);
    }

    protected String getStringResourceByName(String aString) {
        int resId = getResources().getIdentifier(aString, "string", getPackageName());
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
        Call<ViewDocumentSnapshot> call = createGetApi();
        call.enqueue(new Callback<ViewDocumentSnapshot>() {
            @Override
            public void onResponse(Call<ViewDocumentSnapshot> call, Response<ViewDocumentSnapshot> response) {
                if (response.isSuccessful()) {
                    viewDoc = response.body();
                    assignValueToControls(viewDoc);
                    hideLoadingScreen();
                }
            }

            @Override
            public void onFailure(Call<ViewDocumentSnapshot> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                call.cancel();
                hideLoadingScreen();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.documentInfo = (DocumentInfo) getIntent().getExtras().getSerializable(DocumentInfo.DOCUMENT_INFO_KEY);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        setViewContent();
        modelId = getIntent().getExtras().getString(KEY_MODEL_ID);
        if (modelId == null) {
            Log.e(TAG, "Must pass extra " + KEY_MODEL);
            throw new IllegalArgumentException("Must pass extra " + KEY_MODEL);
        }
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbar);
        loadingScreen = findViewById(R.id.loadingScreen);
        EventHandler.getInstance().addDocChangedEventListner(documentInfo.getName(), docChangedEventListner);
        loadDataFromServer();
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
        RepositoryFactory.create().get(documentName).remove(documentName, viewDoc.getId(), new DocumentRepository.DocumentEventListner() {
            @Override
            public void onError(Throwable t) {
                finish();
            }

            @Override
            public void onResponse(Response response) {
                finish();
            }
        });
    }

    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(KEY_MODIFIED_FLAG, isModelEdited);
        returnIntent.putExtra(KEY_DELETED_FLAG, isModelDeleted);
        if (!isModelDeleted) {
            returnIntent.putExtra(KEY_MODEL, viewDoc);
        }
        setResult(Activity.RESULT_OK, returnIntent);
        super.finish();
    }

    public void hideLoadingScreen() {
        loadingScreen.setVisibility(View.GONE);
    }

    public void showLoadingScreen() {
        loadingScreen.setVisibility(View.VISIBLE);
    }

    protected void assignValueToControls(ViewDocumentSnapshot doc) {
        setTitle(ResourceUtil.getStringResourceByName(this, documentInfo.getDocViewViewDef().getTitle()));
        renderViewData(doc);
    }

    protected void renderViewData(ViewDocumentSnapshot doc) {
        LinearLayout rootView = findViewById(R.id.content_container);
        rootView.removeAllViews();
        for (String key : doc.getData().keySet()) {
            rootView.addView(ViewFieldCreatorFactory.getInstance(this).create(this, doc.getDataValue(key)).createView());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(documentInfo.getDocViewViewDef().getViewOptionMenu(), menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_view_pdf) {
            String url  = APIClient.API_URL + "view/pdfInvoice/" + viewDoc.getId();
            PDFUtil.downloadAndOpenPDF(this, url, viewDoc.getId() + ".pdf");
        }

        if (id == R.id.menu_edit) {
            onEditMenuClick();
        }
        return super.onOptionsItemSelected(item);
    }

    private void onEditMenuClick() {
        Intent intent = new Intent(this, documentInfo.getDocEditViewDef().getActivityClass());
        intent.putExtra(DocumentInfo.DOCUMENT_INFO_KEY, documentInfo);
        intent.putExtra(EditRestApiActivity.KEY_DOC_ID, viewDoc.getId());
        startActivity(intent);
    }

}
