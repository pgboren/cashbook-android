package com.soleap.cashbook.common.widget.dialog;

import android.content.Context;
import android.os.Bundle;
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
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.fragment.DocFormFragment;
import com.soleap.cashbook.fragment.ItemSpecFormFragment;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.document.ItemSpecification;

public class OneToManyDocFragmentSuportDialog extends FragmentSuportDialog {

    private String TAG = "OneToManyDocFragmentSuportDialog";
    private DocFormFragment formFragment;

    private Document doc;

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public OneToManyDocFragmentSuportDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.one_to_many_fragment_dialog, container, false);
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

        Button btnSave = rootView.findViewById(R.id.btn_add);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doc = new ItemSpecification();
                formFragment.readInputData(doc);
                listener.onDismiss(null);
                dismiss();
            }
        });
        return rootView;
    }

}
