package com.soleap.cashbook.common.activity;

import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.fragment.BSDocFormFragment;
import com.soleap.cashbook.common.fragment.DocFormFragment;
import com.soleap.cashbook.common.document.BsDocument;

public class BsDocAddNewActivity extends DocAddNewActivity {

    private static final String TAG = "BsDocListActivity";
    private DocFormFragment formFragment;
    @Override
    protected void setViewContent() {
        setContentView(R.layout.activity_form_bsdoc);
        initInputView();
    }

    @Override
    protected void readInputData(Document document) {
        formFragment.readInputData(document);
    }

    protected void initInputView() {
        try {
            Class fragmentClass = documentInfo.getDocAddNewDef().getFormFragmentViewClass();
            formFragment = (DocFormFragment) fragmentClass.newInstance();
            formFragment.setValueChangeListner(new DocFormFragment.ValueChangeListner() {
                @Override
                public void onValueChanged() {
                    validation();
                }
            });
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.form_container,formFragment);
            fragmentTransaction.commit();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    protected boolean validation() {
       isValid = formFragment.validation();
        return super.validation();
    }

}
