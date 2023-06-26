package com.soleap.cashbook.common.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.ViewDataGroup;
import com.soleap.cashbook.common.document.Action;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.document.ViewDataModel;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;
import com.soleap.cashbook.common.value.ViewSetterFactory;
import com.soleap.cashbook.common.value.ViewType;
import com.soleap.cashbook.common.widget.bottomsheetmenu.BottomSheetMenu;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ModelViewActivity<T extends Document> extends AppCompatActivity {

    private static final String TAG = "ModelViewActivity";
    public static final String KEY_MODEL = "key_model";
    public static final String KEY_DOC = "key_doc";
    public static final String KEY_MODEL_ID = "key_model_id";
    public static final String KEY_SELECTED_POSITION = "key_selected_position";
    public static final String KEY_MODIFIED_FLAG = "key_modofied_flag";
    public static final String KEY_DELETED_FLAG = "key_deleted_flag";
    public final static int EDIT_ACTIVITY_REQUEST_CODE = 103;

    private  Toolbar toolbar;
    protected boolean isModelEdited = false;
    protected boolean isModelDeleted = false;
    protected View loadingScreen;
    protected String modelId = null;
    protected T model;
    protected APIInterface apiInterface;
    protected int position;

    private BottomSheetMenu buttomActionsMenu;

//    abstract void onFabClickListner();
    abstract Class editActivityClass();
    abstract Class entityClass();
    protected Button btnDelete;

    abstract Call<T> createGetApi();

    protected String getViewTitle() {
        ViewDataModel viewModel = (ViewDataModel) this.model;
        return viewModel.getTitle();
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
    
    protected String getDeleteMessage() {
        return getString(R.string.delete_alert_dialog_message);
    }

    protected void loadDataFromServer() {
        showLoadingScreen();
        Call<T> call = createGetApi();
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    model = response.body();
                    assignValueToControls(model);
                    setTitle(getViewTitle());
                    hideLoadingScreen();
                }
            }
            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                call.cancel();
                hideLoadingScreen();
            }
        });
    }

    protected void startEditActivity() {
        Intent intent = new Intent(this, editActivityClass());
        intent.putExtra(EditRestApiActivity.KEY_DOC_ID, this.model.getId());
        this.startActivityForResult(intent, EDIT_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        setViewContent();

        modelId = getIntent().getExtras().getString(KEY_MODEL_ID);
        if (modelId == null) {
            Log.e(TAG, "Must pass extra " + KEY_MODEL);
            throw new IllegalArgumentException("Must pass extra " + KEY_MODEL);
        }
        position = getIntent().getExtras().getInt(KEY_SELECTED_POSITION);
        this.toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_close);

//        btnDelete = findViewById(R.id.btn_delete);
//        if (btnDelete != null) {
//            btnDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onDeleteOptionMenuClick();
//                }
//            });
//        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbar);
        loadingScreen = this.findViewById(R.id.loadingScreen);
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
        builder1.setMessage(Html.fromHtml("<font color='#8d8d8d'>" + getDeleteMessage() + "</font>"));
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
        Call<Void> call = apiInterface.delete(entityClass().getSimpleName().toLowerCase(), model.id);
        showLoadingScreen();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                hideLoadingScreen();
                if (response.code() == 532) {
                    new MaterialAlertDialogBuilder(ModelViewActivity.this)
                            .setTitle(getString(R.string.error))
                            .setMessage(getString(R.string.category_is_used_as_parent)).setNeutralButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).show();
                }

                if (response.code() == 200) {
                    isModelDeleted = true;
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                hideLoadingScreen();
                finish();
                call.cancel();
            }

            });
    }

    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(KEY_SELECTED_POSITION, position);
        returnIntent.putExtra(KEY_MODIFIED_FLAG, isModelEdited);
        returnIntent.putExtra(KEY_DELETED_FLAG, isModelDeleted);

        if (!isModelDeleted) {
            returnIntent.putExtra(KEY_MODEL, model);
        }

        setResult(Activity.RESULT_OK, returnIntent);
        super.finish();
    }

    protected void hideLoadingScreen() {
        loadingScreen.setVisibility(View.GONE);
    }

    protected void showLoadingScreen() {
        loadingScreen.setVisibility(View.VISIBLE);
    }

    protected void assignValueToControls(T model) {
        ViewDataModel viewModel = (ViewDataModel)model;
        this.renderDataTextView();
        int menuId = this.getResources().getIdentifier(viewModel.getMenu(), "menu", getPackageName());
//TODO
//        buttomActionsMenu = new BottomSheetMenu.Builder(this, this, "", menuId, R.layout.text_menu_item).create();
    }

    protected void renderDataTextView() {

        ViewDataModel viewModel = (ViewDataModel)model;
        List<ViewDataGroup> groupLabel = viewModel.getData();
        LinearLayout rootView = findViewById(R.id.content_container);
        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(findViewById(R.id.root));

        for (ViewDataGroup viewGroup:groupLabel) {

            LinearLayout groupContainer = new LinearLayout(this);
            groupContainer.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams groupLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            groupLayoutParam.setMargins(0,0,0,50);
            groupContainer.setLayoutParams(groupLayoutParam);

            LinearLayout groupLabelContainer = new LinearLayout(this);
            groupLabelContainer.setGravity(Gravity.CENTER_VERTICAL);


            groupLabelContainer.setLayoutParams( new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150));

            groupLabelContainer.setOrientation(LinearLayout.HORIZONTAL);
            TextView label = new TextView(this);
            label.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f ));
            label.setText(getStringResourceByName(viewGroup.getLabel()));
            label.setTextColor(getColor(R.color.colorPrimary));
            label.setTextSize(17);

            groupLabelContainer.addView(label);
            if (viewGroup.getActions() != null) {
                groupLabelContainer.addView(renderActionButtons(viewGroup.getActions()));
            }

            groupContainer.addView(groupLabelContainer);

            View view = new View(this);
            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 3));
            view.setBackgroundColor(R.color.item_devider);
            groupContainer.addView(view);

            List<ViewData> values = viewGroup.getValues();
            for (ViewData value:values) {

                LinearLayout valueContainer = new LinearLayout(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(5, 20,0,20);
                valueContainer.setLayoutParams(params);
                valueContainer.setOrientation(LinearLayout.HORIZONTAL);

                TextView textLabel = new TextView(this);
                textLabel.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));

                textLabel.setText(getStringResourceByName(value.getLabel()));
                valueContainer.addView(textLabel);

                TextView textValue = new TextView(this);
                textValue.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.7f));
                valueContainer.addView(textValue);

                if (value.getValue() != null) {
                    if (value.getType().equals("STRING") ) {
                        viewSetterFactory.create(ViewType.TEXTVIEW, textValue).setString(value.getValue().toString());
                    }
                    if (value.getType().equals("RESOURCE_STRING") ) {
                        viewSetterFactory.create(ViewType.TEXTVIEW, textValue).setString(getStringResourceByName(value.getValue().toString()));
                    }

                    if (value.getType().equals("INTEGER") ) {
                        viewSetterFactory.create(ViewType.TEXTVIEW, textValue).setLong(((Double)value.getValue()).longValue(), "%04d");;
                    }

                    if (value.getType().equals("TIMESTAMP") ) {
                        viewSetterFactory.create(ViewType.TEXTVIEW, textValue).setDate(((Double)value.getValue()).longValue(), "dd-MMM-yyyy HH:mm:ss");
                    }

                    if (value.getType().equals("CURRENCY") ) {
                        viewSetterFactory.create(ViewType.TEXTVIEW, textValue).setCurrency((Double)value.getValue(), Locale.forLanguageTag(value.getLocale()));
                    }
                }
                groupContainer.addView(valueContainer);
            }
            rootView.addView(groupContainer);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK) {
                if (EDIT_ACTIVITY_REQUEST_CODE == requestCode) {
                    isModelEdited = true;
                }
                loadDataFromServer();
            }
        }
        catch (Exception ex) {
            Log.e(TAG, ex.getMessage(), ex);
        }
    }

    private View renderActionButtons(Action[] actions) {
        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for (final Action action: actions) {
            Context newContext = new ContextThemeWrapper(this,R.style.OutlinedCircleButton);
            MaterialButton button = new MaterialButton(newContext);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(120, LinearLayout.LayoutParams.MATCH_PARENT);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onActionButtonClicked(action.getName());
                }
            });
            button.setLayoutParams(param);
            int icon = this.getResources().getIdentifier(action.getIcon(), "drawable", getPackageName());
            button.setIcon(getDrawable(icon));
            layout.addView(button);
        }
        return layout;
    }

    private void onActionButtonClicked(String key) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.invoice_view_activity_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

//        if (id == R.id.view_option_action_more_menuitem) {
//            buttomActionsMenu.show();
//        }

        return super.onOptionsItemSelected(item);
    }

}
