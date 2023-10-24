package com.soleap.cashbook.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.JsonReader;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.client.android.Intents;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.soleap.cashbook.BuildConfig;
import com.soleap.cashbook.Global;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.ActivityDataResult;
import com.soleap.cashbook.common.activity.BsDocListActivity;
import com.soleap.cashbook.common.activity.QrCodeScannerActivity;
import com.soleap.cashbook.common.activity.QrCodeScannerResultActivity;
import com.soleap.cashbook.common.activity.RecyclerActivity;
import com.soleap.cashbook.content.AppPrefrences;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.view.DocumentInfo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ActivityCompat.OnRequestPermissionsResultCallback {

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
        result -> {
            if(result.getContents() == null) {
                Intent originalIntent = result.getOriginalIntent();
                if (originalIntent == null) {
                    Log.d("MainActivity", "Cancelled scan");
                    Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                } else if(originalIntent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION)) {
                    Log.d("MainActivity", "Cancelled scan due to missing camera permission");
                    Toast.makeText(MainActivity.this, "Cancelled due to missing camera permission", Toast.LENGTH_LONG).show();
                }
            } else {
                Log.d("MainActivity", "Scanned");
                handleQrCodeResult(result.getContents());
            }
        }
    );

    private AppBarConfiguration mAppBarConfiguration;
    private static final int PERMISSION_REQUEST_CAMERA = 0;
    private View mLayout;

    private boolean fabExpanded = false;

    private FloatingActionButton fabSettings;
    private LinearLayout layoutFabContact;
    private LinearLayout layoutFabDeal;
    private NavigationView navigationView;

    private ActivityResultLauncher<Intent> activityResultLauncher;

    private void handleQrCodeResult(String qrCodeString) {
        try {
            JSONObject jsonObject = new JSONObject(qrCodeString);
            String docName = jsonObject.get("doc").toString();
            String value = jsonObject.get("value").toString();
            DocumentInfo documentInfo = DocumentInfo.getDocumentInfo(docName);
            Intent intent = new Intent(MainActivity.this, QrCodeScannerResultActivity.class);
            intent.putExtra(ActivityDataResult.DOCUMENT_INFO_KEY, documentInfo);
            intent.putExtra(ActivityDataResult.DOC_ID_KEY, value);
            startActivity(intent);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mLayout = findViewById(R.id.main_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.main_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        navigationView = findViewById(R.id.nav_view);
        navigationView.inflateMenu(R.menu.nav_sale_menu);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        requestCameraPermission();
        Global.context = getApplicationContext();
//        initFabView();
        initUserProfileView();

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            String data = result.getData().getStringExtra("SCAN_RESULT");
                        }
                    }
                }
        );

    }

    public void addNewInvoiceButtonClicked(View view) {
        Intent intent = new Intent(MainActivity.this, DocumentInfo.INVOICE.getDocAddNewDef().getActivityClass());
        intent.putExtra(DocumentInfo.DOCUMENT_INFO_KEY, DocumentInfo.INVOICE);
        startActivity(intent);
    }

    public void scanQRCodeButtonClicked(View view) {
        ScanOptions options = new ScanOptions().setOrientationLocked(false).setCaptureActivity(QrCodeScannerActivity.class);
        barcodeLauncher.launch(options);
    }

    private void initUserProfileView() {
        View imgUserProfile =  navigationView.getHeaderView(0).findViewById(R.id.img_user_profile);
        imgUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initFabView() {
        closeSubMenusFab();
    }

    private void openSubMenusFab(){
        layoutFabContact.setVisibility(View.VISIBLE);
        layoutFabDeal.setVisibility(View.VISIBLE);
        fabExpanded = true;
    }

    //closes FAB submenus
    private void closeSubMenusFab(){
        layoutFabContact.setVisibility(View.INVISIBLE);
        layoutFabDeal.setVisibility(View.INVISIBLE);
        fabExpanded = false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        String documentName = null;
        Intent intent = null;

        if (id == R.id.nav_signOut) {
            AppPrefrences.signOut(this);
            intent = new Intent(this, LoginActivity.class);
            intent.putExtra(DocumentName.DOCUMENT_NAME, DocumentName.CONTACT);
            startActivity(intent);
            finish();
        }

        if (id == R.id.nav_add_contact) {
             DocumentInfo documentInfo = DocumentInfo.CONTACT;
            intent = new Intent(this, documentInfo.getDocAddNewDef().getActivityClass());
            intent.putExtra(DocumentInfo.DOCUMENT_INFO_KEY, documentInfo);
            startActivity(intent);
            return true;
        }

        intent = new Intent(this, BsDocListActivity.class);

        if (id == R.id.nav_contact_list) {
            intent.putExtra(DocumentInfo.DOCUMENT_INFO_KEY, DocumentInfo.CONTACT);
        }

        if (id == R.id.nav_vehicle_list) {
            intent.putExtra(DocumentInfo.DOCUMENT_INFO_KEY, DocumentInfo.VEHICLE);
        }

        if (id == R.id.nav_invoice) {
            intent.putExtra(DocumentInfo.DOCUMENT_INFO_KEY, DocumentInfo.INVOICE);
        }

        startActivity(intent);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1;

    private void requestCameraPermission() {

        // Permission has not been granted and must be requested.
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            Snackbar.make(mLayout, R.string.camera_access_required, Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE , Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CAMERA);
                }
            }).show();

        } else {
            Snackbar.make(mLayout, R.string.camera_unavailable, Snackbar.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
        }


    }



    private void writeToFile() {
        // Perform your file writing operations here
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
//            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Snackbar.make(mLayout, R.string.camera_permission_granted,
//                        Snackbar.LENGTH_SHORT)
//                        .show();
//            } else {
//                Snackbar.make(mLayout, R.string.camera_permission_denied,
//                        Snackbar.LENGTH_SHORT)
//                        .show();
//            }
        }

        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted, you can proceed with writing to external storage
                writeToFile();
            } else {
                // Permission is denied, handle accordingly (e.g., show a message or disable functionality)
            }
        }
    }

}