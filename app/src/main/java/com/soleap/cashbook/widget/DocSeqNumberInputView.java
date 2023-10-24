package com.soleap.cashbook.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.widget.input.BaseButtomSheetInputView;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocSeqNumberInputView extends BaseButtomSheetInputView<String> {

    private String seqId;

    private String prefix;

    public DocSeqNumberInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void processAttributeSet(Context context, @Nullable AttributeSet attrs, TypedArray a) {
        super.processAttributeSet(context, attrs, a);
        this.seqId = a.getString(R.styleable.BaseButtomSheetInputView_biv_seq_id);
        this.prefix = a.getString(R.styleable.BaseButtomSheetInputView_biv_seq_prefix);
        getNextCounterValue(this.seqId);
    }

    @Override
    protected void onClick() {

    }

    @Override
    protected void onValueSet() {

    }

    @Override
    protected void updateDisplayValue() {
        
    }

    private void getNextCounterValue(String id) {
        final APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        apiInterface.getDocumentCounter(id).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                TextView txtValue = getRootView().findViewById(R.id.txt_value);
                txtValue.setText(DocSeqNumberInputView.this.prefix + " - " + response.body().get("seq"));
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {

            }
        });
    }





}
