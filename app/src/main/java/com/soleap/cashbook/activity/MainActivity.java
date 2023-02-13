package com.soleap.cashbook.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.soleap.cashbook.Global;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.BsDocListActivity;
import com.soleap.cashbook.content.AppPrefrences;
import com.soleap.cashbook.document.DocumentInfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ActivityCompat.OnRequestPermissionsResultCallback {

    private AppBarConfiguration mAppBarConfiguration;
    private static final int PERMISSION_REQUEST_CAMERA = 0;
    private View mLayout;

    private boolean fabExpanded = false;
    private FloatingActionButton fabSettings;
    private LinearLayout layoutFabContact;
    private LinearLayout layoutFabDeal;
    private NavigationView navigationView;

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
        fabSettings = (FloatingActionButton) this.findViewById(R.id.fabSetting);
        layoutFabContact = (LinearLayout) this.findViewById(R.id.layoutFabContact);
        layoutFabDeal = (LinearLayout) this.findViewById(R.id.layoutFabDeal);

        fabSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabExpanded == true){
                    closeSubMenusFab();
                } else {
                    openSubMenusFab();
                }
            }
        });

        //Only main FAB is visible in the beginning
        closeSubMenusFab();

    }

    private void openSubMenusFab(){
        layoutFabContact.setVisibility(View.VISIBLE);
        layoutFabDeal.setVisibility(View.VISIBLE);
        //Change settings icon to 'X' icon
//        fabSettings.setImageResource(R.drawable.ic_close_black_24dp);
        fabExpanded = true;
    }


    //closes FAB submenus
    private void closeSubMenusFab(){
        layoutFabContact.setVisibility(View.INVISIBLE);
        layoutFabDeal.setVisibility(View.INVISIBLE);
//        fabSettings.setImageResource(R.drawable.ic_settings_black_24dp);
        fabExpanded = false;
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        String documentName = null;
        Intent intent = null;

        if (id == R.id.nav_signOut) {
            AppPrefrences.signOut(this);
            intent = new Intent(this, LoginActivity.class);
            intent.putExtra(DocumentInfo.DOCUMENT_NAME, DocumentInfo.CONTACT);
            startActivity(intent);
            finish();
        }

        if (id == R.id.nav_add_contact) {
            intent = new Intent(this, ContactAddNewActivity.class);
            intent.putExtra(DocumentInfo.DOCUMENT_NAME, DocumentInfo.CONTACT);
            startActivity(intent);
            return true;
        }

        if (id == R.id.nav_add_new_deal) {
            documentName = DocumentInfo.SALE_ORDER;
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_layout);
            drawer.closeDrawer(GravityCompat.START);
            intent = new Intent(this, SaleOrderAddNewActivity.class);
            intent.putExtra(DocumentInfo.DOCUMENT_NAME, documentName);
            startActivity(intent);
            return true;
        }

        if (id == R.id.nav_item_list) {
            documentName = DocumentInfo.ITEM;
        }

        if (id == R.id.nav_color_list) {
            documentName = DocumentInfo.COLOR;
        }

        if (id == R.id.nav_branch_list) {
            documentName = DocumentInfo.BRANCH;
        }

        if (id == R.id.nav_category_list) {
            documentName = DocumentInfo.CATEGORY;
        }

        if (id == R.id.nav_loan_institute_list) {
            documentName = DocumentInfo.INSTITUE;
        }

        if (id == R.id.nav_contact_list) {
            documentName = DocumentInfo.CONTACT;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_layout);
        drawer.closeDrawer(GravityCompat.START);
        intent = new Intent(this, BsDocListActivity.class);
        intent.putExtra(DocumentInfo.DOCUMENT_NAME, documentName);
        startActivity(intent);
        return true;
    }


    /**
     * Requests the {@link android.Manifest.permission#CAMERA} permission.
     * If an additional rationale should be displayed, the user has to launch the request from
     * a SnackBar that includes additional information.
     */
    private void requestCameraPermission() {
        // Permission has not been granted and must be requested.
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // Display a SnackBar with cda button to request the missing permission.
            Snackbar.make(mLayout, R.string.camera_access_required,
                    Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Request the permission
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSION_REQUEST_CAMERA);
                }
            }).show();

        } else {
            Snackbar.make(mLayout, R.string.camera_unavailable, Snackbar.LENGTH_SHORT).show();
            // Request the permission. The result will be received in onRequestPermissionResult().
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
        }
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
    }

}