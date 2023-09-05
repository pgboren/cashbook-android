package com.soleap.cashbook.common.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.fragment.DocFormFragment;
import com.soleap.cashbook.fragment.ItemSpecFormFragment;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;

public class FragmentSuportDialog extends DialogFragment {

    protected String title;

    protected APIInterface apiInterface;

    private String docName;

    protected String docId;

    protected DialogInterface.OnDismissListener listener;

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocId() {
        return docId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setListener(DialogInterface.OnDismissListener listener) {
        this.listener = listener;
    }

    public FragmentSuportDialog(@NonNull Context context) {
        super();
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog, container, false);
        FrameLayout fragmentContainer = rootView.findViewById(R.id.fragment_container);
        DocFormFragment formFragment = new ItemSpecFormFragment();
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
        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog bottomSheetDialog = (Dialog) super.onCreateDialog(savedInstanceState);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                listener.onDismiss(dialogInterface);
            }
        });
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        return bottomSheetDialog;
    }


}
