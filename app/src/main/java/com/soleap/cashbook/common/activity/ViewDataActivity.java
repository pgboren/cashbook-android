package com.soleap.cashbook.common.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.document.ViewDocumentSnapshot;
import com.soleap.cashbook.common.global.DocChangedEventListner;
import com.soleap.cashbook.common.global.EventHandler;
import com.soleap.cashbook.common.repository.DocumentRepository;
import com.soleap.cashbook.common.repository.RepositoryFactory;
import com.soleap.cashbook.common.util.PDFUtil;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;
import com.soleap.cashbook.common.widget.view.ViewFieldCreatorFactory;
import com.soleap.cashbook.view.DocumentInfo;

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

    public final static int VIEW_ACTIVITY_REQUEST_CODE = 103;

    public final static int VIEW_ACTIVITY_RESULT_CODE = 104;

    private String photoFileName = "new_capture_product_image.jpg";

    public String documentName;
    private Toolbar toolbar;
    protected boolean isModelEdited = false;
    protected boolean isModelDeleted = false;
    protected View loadingScreen;
    public String docId = null;

    protected DocumentInfo documentInfo;

    private int listPosition;
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
        return apiInterface.viewData(documentInfo.getName().toString().toLowerCase(), docId);
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
        return String.format(deleteMessageFormat, getStringResourceByName(this.documentInfo.getName()));
    }

    protected String getDeleteAlertDialogMessage() {
        String deleteMessageFormat = getString(R.string.delete_alert_dialog_message);
        return String.format(deleteMessageFormat, getStringResourceByName(this.documentInfo.getName()));
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
        Bundle extraBundle = getIntent().getExtras();
        this.documentInfo = (DocumentInfo)extraBundle.getSerializable(ActivityDataResult.DOCUMENT_INFO_KEY);
        this.listPosition = extraBundle.getInt(ActivityDataResult.DOC_POSITION_KEY);
        documentName = documentInfo.getName();
        apiInterface = APIClient.getClient().create(APIInterface.class);
        setViewContent();
        docId = getIntent().getExtras().getString(ActivityDataResult.DOC_ID_KEY);
        if (docId == null) {
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
        RepositoryFactory.create().get(documentInfo.getName()).remove(documentInfo.getName(), viewDoc.get_id(), new DocumentRepository.DocumentEventListner() {
            @Override
            public void onError(Throwable t) {
                finish();
            }

            @Override
            public void onResponse(Response response) {
                isModelDeleted = true;
                finish();
            }
        });
    }

    @Override
    public void finish() {
        if (isModelDeleted) {
            EventHandler.getInstance().notifyDocumentRemoved(documentInfo.getName(), docId, listPosition);
        }
        else {
            Intent returnIntent = new Intent();
            returnIntent.putExtra(KEY_MODIFIED_FLAG, isModelEdited);
            returnIntent.putExtra(KEY_DELETED_FLAG, isModelDeleted);
            setResult(ActivityDataResult.ACTION_EDITED_CODE, returnIntent );
        }

        super.finish();
    }

    public void hideLoadingScreen() {
        loadingScreen.setVisibility(View.GONE);
    }

    public void showLoadingScreen() {
        loadingScreen.setVisibility(View.VISIBLE);
    }

    protected void assignValueToControls(ViewDocumentSnapshot doc) {
        setTitle(doc.getTitle());
//        setTitle(ResourceUtil.getStringResourceByName(this, documentInfo.getDocViewViewDef().getTitle()));
        renderViewData(doc);
    }

    protected void renderViewData(ViewDocumentSnapshot doc) {
        LinearLayout rootView = findViewById(R.id.content_container);
        rootView.removeAllViews();
        for (String key : doc.getData().keySet()) {
            ViewData viewData = doc.getDataValue(key);
            if (viewData.getVisible() == View.VISIBLE) {
                rootView.addView(ViewFieldCreatorFactory.getInstance(this).create(this, viewData).createView());
            }
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
            String url  = APIClient.API_URL + "view/pdfInvoice/" + viewDoc.get_id();
            PDFUtil.downloadAndOpenPDF(this, url, viewDoc.get_id() + ".pdf");
        }

        if (id == R.id.menu_view_qrcode) {
            String url  = APIClient.API_URL + "view/vehiclelable/" + viewDoc.get_id();
            PDFUtil.downloadAndOpenPDF(this, url, viewDoc.get_id() + ".pdf");
        }

        if (id == R.id.menu_edit) {
            onEditMenuClick();
        }

        if (id == R.id.menu_delete) {
            onDeleteOptionMenuClick();
        }

        return super.onOptionsItemSelected(item);
    }

    private void onEditMenuClick() {
        Intent intent = new Intent(this, documentInfo.getDocEditViewDef().getActivityClass());
        intent.putExtra(DocumentInfo.DOCUMENT_INFO_KEY, documentInfo);
        intent.putExtra(EditRestApiActivity.KEY_DOC_ID, viewDoc.get_id());
        startActivity(intent);
    }

}
