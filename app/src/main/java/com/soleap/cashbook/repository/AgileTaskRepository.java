package com.soleap.cashbook.repository;

import android.util.Log;

import com.soleap.cashbook.common.document.PagingRecyclerViewData;
import com.soleap.cashbook.common.repository.DocumentSnapshotRepository;
import com.soleap.cashbook.document.DocumentInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgileTaskRepository extends DocumentSnapshotRepository {

    public AgileTaskRepository() {
        super(DocumentInfo.AGILE_TASK);
    }

    public void getTasks(String board, String[] stageIds, int page) {
        Call<PagingRecyclerViewData> call = apiInterface.getTasks(page, stageIds, board);
        call.enqueue(new Callback<PagingRecyclerViewData>() {
            @Override
            public void onResponse(Call<PagingRecyclerViewData> call, Response<PagingRecyclerViewData> response) {
                pagingDocsRequestListner.onGet(response.body());
            }
            @Override
            public void onFailure(Call<PagingRecyclerViewData> call, Throwable t) {
                pagingDocsRequestListner.onError(t);
                Log.e("ERROR", t.getMessage(), t );
                call.cancel();
            }
        });
    }


}
