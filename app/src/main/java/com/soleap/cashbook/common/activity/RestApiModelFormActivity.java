package com.soleap.cashbook.common.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.global.EventHandler;
import com.soleap.cashbook.common.repository.DocumentRepository;
import com.soleap.cashbook.common.repository.RepositoryFactory;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;
import com.soleap.cashbook.view.DocumentInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public abstract class RestApiModelFormActivity<T extends Document> extends AppCompatActivity implements TextWatcher {
    public static final String ALLOW_SAVE_AND_ADD_NEW_EXTRAS = "'allow_save_and_add_new'";
    public static final String KEY_DOC_INFO = "key_doc_info";

    public static final String TAG = "'RestApiModelFormActivity'";

    protected DocumentInfo documentInfo;

    protected APIInterface apiInterface;
    protected View loadingScreen;
    protected Button btnSave;
    protected boolean isValid = false;
    protected List<T> models = new ArrayList<T>();

    protected abstract String getViewTitle();

    protected String getStringResourceByName(String aString) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        if (resId == 0) {
            return aString;
        }
        return getString(resId);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        documentInfo = (DocumentInfo) getIntent().getSerializableExtra(DocumentInfo.DOCUMENT_INFO_KEY);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        setViewContent();
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_close);
            toolbar.setTitle(getStringResourceByName(getViewTitle()));
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (models.size() > 0) {
                        finishAndSendData();
                    }
                    else {
                        cancel();
                    }
                }
            });
            setSupportActionBar(toolbar);
        }


        btnSave = this.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    save();
                }
            });
        btnSave.setEnabled(false);

        loadingScreen = this.findViewById(R.id.loadingScreen);
        this.hideLoadingScreen();
    }

    protected void hideLoadingScreen() {
        loadingScreen.setVisibility(View.GONE);
    }

    protected void showLoadingScreen() {
        loadingScreen.setVisibility(View.VISIBLE);
    }

    protected abstract void setViewContent();

    protected Call<DocumentSnapshot> creatRestCall(T model) {
        return null;
    }

    protected void cancel() {
        if (models.size() > 0) {
            this.setResult(RESULT_OK);
        }
        else {
            this.setResult(RESULT_CANCELED);
        }
        this.finish();
    }

    @Override
    public void finish() {
        super.finish();
    }

    protected void finishAndSendData()  {
    }

    protected abstract void onError(JSONObject errorObject) throws JSONException;

    @SuppressLint("LongLogTag")
    protected void save() {
        try {
            if (validation()) {
                showLoadingScreen();
                T model = (T) documentInfo.getClassName().newInstance();
                readInputData(model);
                RepositoryFactory.create().get(documentInfo.getName()).addNew(documentInfo.getName(), model.toMap(), new DocumentRepository.DocumentEventListner() {
                    @Override
                    public void onError(Throwable t) {
                        hideLoadingScreen();
                        Log.e(TAG, t.getMessage(), t);
                    }

                    @Override
                    public void onResponse(Response response) {
                        hideLoadingScreen();
                        Map<String, Object> result = (Map<String, Object>) response.body();
                        EventHandler.getInstance().notifyDocumentCreated(documentInfo.getName(), result.get("id").toString());
                        finish();
                    }
                });
            }
        }
        catch (Exception ex) {
            hideLoadingScreen();
            Log.e("RestApiModelFormActivity", ex.getMessage(), ex);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //Close button
        if (id == 16908332) {
            if (models.size() > 0) {
                finishAndSendData();
            }
            else {
                cancel();
            }
            return true;
        }

        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        validation();
    }

    protected boolean validation() {
        if(isValid) {
            btnSave.setEnabled(true);
        }
        else {
            btnSave.setEnabled(false);
        }
        return isValid;
    }

    protected abstract void readInputData(T document);

    protected abstract void resetFields();

}
