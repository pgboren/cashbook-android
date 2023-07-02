package com.soleap.cashbook.common.widget.bottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.soleap.cashbook.R;

public class FragmentBottomSheetDialogFragment extends BottomSheetDialogFragment {
    private Fragment currentFragment;

    public static FragmentBottomSheetDialogFragment newInstance() {
        return new FragmentBottomSheetDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_dialog_fragment_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        showFragment(FirstSubFragment.newInstance());
    }

    public void showFragment(Fragment fragment) {
        if (currentFragment != null) {
            getChildFragmentManager().beginTransaction().remove(currentFragment).commit();
        }
        currentFragment = fragment;
        getChildFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
    }
}