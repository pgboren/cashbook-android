package com.soleap.cashbook.common.activity;

import com.soleap.cashbook.adapter.RestApiRecyclerViewAdapter;
import com.soleap.cashbook.common.document.Media;

import java.util.List;

import retrofit2.Call;

public class MediaLookupActivity extends RestApiLookupActivity<Media> {

    @Override
    protected void setViewContent() {

    }

    @Override
    Class getAddNewActivityClass() {
        return null;
    }

    @Override
    RestApiRecyclerViewAdapter getAdapter() {
        return null;
    }

    @Override
    Call<List<Media>> getAPICall() {
        return null;
    }
}