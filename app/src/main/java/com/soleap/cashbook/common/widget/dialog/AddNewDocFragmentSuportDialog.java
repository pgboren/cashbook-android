package com.soleap.cashbook.common.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.fragment.DocFormFragment;
import com.soleap.cashbook.fragment.ItemSpecFormFragment;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.document.ItemSpecification;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewDocFragmentSuportDialog extends FragmentSuportDialog {

    private String TAG = "AddNewDocFragmentSuportDialog";
    private DocFormFragment formFragment;

    public AddNewDocFragmentSuportDialogListner listner;

    public void setListner(AddNewDocFragmentSuportDialogListner listner) {
        this.listner = listner;
    }

    public AddNewDocFragmentSuportDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_new_fragment_dialog, container, false);
        FrameLayout fragmentContainer = rootView.findViewById(R.id.fragment_container);
        formFragment = new ItemSpecFormFragment();
        formFragment.setDocId(docId);
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragmentContainer.getId(), formFragment);
        fragmentTransaction.commit();
        ImageButton btnClose = rootView.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        TextView tvTitle = rootView.findViewById(R.id.tv_title);
        tvTitle.setText(ResourceUtil.getStringResourceByName(getContext(), this.title));

        Button btnSave = rootView.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        return rootView;
    }
    
    protected void save() {
        try {
            ItemSpecification itemSpecification = new ItemSpecification();
            formFragment.readInputData(itemSpecification);
            Call<Map<String, Object>> call = apiInterface.post("itemspecification", itemSpecification.toMap());
            call.enqueue(new Callback<Map<String, Object>>() {
                @Override
                public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                    listner.onDocAdded(response.body().get("id").toString());
                    dismiss();
                }

                @Override
                public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                    call.cancel();
                }
            });
        }
        catch (Exception ex) {
            Log.e(TAG, ex.getMessage(), ex);
        }
    }

    public interface AddNewDocFragmentSuportDialogListner {

        public void onDocAdded(String docId);
    }



}
