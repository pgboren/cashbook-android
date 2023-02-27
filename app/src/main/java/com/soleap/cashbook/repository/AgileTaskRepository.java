package com.soleap.cashbook.repository;

import android.util.Log;

import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.repository.DocumentSnapshotRepository;
import com.soleap.cashbook.document.DocumentInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgileTaskRepository extends DocumentSnapshotRepository {

    public AgileTaskRepository() {
        super(DocumentInfo.AGILE_TASK);
    }

    public void getTasks(String[] stageIds) {
        Call<List<DocumentSnapshot>> call = apiInterface.getTasks(DocumentInfo.AGILE_TASK, stageIds);
        call.enqueue(new Callback<List<DocumentSnapshot>>() {
            @Override
            public void onResponse(Call<List<DocumentSnapshot>> call, Response<List<DocumentSnapshot>> response) {
                listDocumentListner.onListed(response.body());
            }

            @Override
            public void onFailure(Call<List<DocumentSnapshot>> call, Throwable t) {
                listDocumentListner.onError(t);
                Log.e("ERROR", t.getMessage(), t );
                call.cancel();
            }
        });
    }


}
