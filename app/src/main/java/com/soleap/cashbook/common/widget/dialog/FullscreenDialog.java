package com.soleap.cashbook.common.widget.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.soleap.cashbook.R;
import com.soleap.cashbook.document.Contact;

public abstract class FullscreenDialog<V> extends DialogFragment implements View.OnClickListener {

    private final int viewLayout;
    protected Callback callback;

    private EditText txtValue;

    private View rootView;

    private String title;
    private V value;

    public void setTitle(String title) {
        this.title = title;
    }

    protected DialogViewHolder<V> viewHolder;

    public FullscreenDialog(int viewLayout) {
        this.viewLayout = viewLayout;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(viewLayout, container, false);
        ImageButton close = rootView.findViewById(R.id.fullscreen_dialog_close);
        ImageButton action = rootView.findViewById(R.id.fullscreen_dialog_check_action);
        viewHolder = createViewHolder(rootView);
        viewHolder.onBind(value);
        close.setOnClickListener(this);
        action.setOnClickListener(this);
        TextView txtTitle = this.rootView.findViewById(R.id.txtTitle);
        txtTitle.setText(title);
        return rootView;
    }

    protected abstract DialogViewHolder<V> createViewHolder(View view);

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.fullscreen_dialog_close:
                dismiss();
                break;
            case R.id.fullscreen_dialog_check_action:
                onCheckButtonClick();
                break;
        }
    }

    protected void onCheckButtonClick() {
        dismiss();
    }

    public void setValue(V value) {
        this.value = value;
        if (viewHolder != null) {
            viewHolder.onBind(value);
        }
    }

    public V getValue() {
        value =viewHolder.readValue();
        return value;
    }

    public interface Callback<V> {

        void onActionClick(V value);

    }

}