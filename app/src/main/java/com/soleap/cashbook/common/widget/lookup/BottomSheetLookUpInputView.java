package com.soleap.cashbook.common.widget.lookup;

import android.content.Context;
import android.util.AttributeSet;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.common.viewholder.OnRecyclerViewListner;

public abstract class BottomSheetLookUpInputView<T> extends BaseTextInputView<T> {

    private DocumentListBottomSheetFragment bottomsheet;

    public BottomSheetLookUpInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    protected abstract RecyclerView.Adapter createAdapter();

    private void lookupDocument() {
        BaseLookupRecyclerViewAdapter adapter = (BaseLookupRecyclerViewAdapter) createAdapter();
        adapter.setListner(new OnRecyclerViewListner() {
            @Override
            public void onItemClicked(Object item, int position) {
                setValue((T) item);
                bottomsheet.dismiss();
            }
        });

        bottomsheet = new DocumentListBottomSheetFragment(adapter);
        AppCompatActivity activity = (AppCompatActivity) getContext();
        bottomsheet.show(activity.getSupportFragmentManager(), "Expanded");
    }


    @Override
    protected void onTextChange(String text) {
    }

    @Override
    protected void onEndIconClickedListner() {
        lookupDocument();
    }

}
