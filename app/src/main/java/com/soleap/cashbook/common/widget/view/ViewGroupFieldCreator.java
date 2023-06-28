package com.soleap.cashbook.common.widget.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.Action;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.common.widget.lookup.DocumentListBottomSheetFragment;
import com.soleap.cashbook.common.widget.lookup.DocumentListBottomSheetFragmentEventListner;
import com.soleap.cashbook.common.widget.lookup.DocumentLookupEditText;
import com.soleap.cashbook.common.widget.lookup.DragDropDocListBottomSheetFragment;
import com.soleap.cashbook.view.DocumentInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewGroupFieldCreator extends FieldCreator {

    public ViewGroupFieldCreator(ViewDataActivity activity, ViewData data) {
        super(activity, data);
    }

    @Override
    public View createView() {
        ViewData groupData = fieldData;
        ViewGroup view = createLabel(groupData);
        if (groupData.getType().equals("GROUP")) {
            Map<String, ViewData> fieldDatas = groupData.getChildrent();
            if (fieldDatas != null && fieldDatas.size() > 0) {
                for (ViewData childFieldData: fieldDatas.values()) {
                    if (childFieldData.getVisible() == View.VISIBLE) {
                        view.addView(ViewFieldCreatorFactory.getInstance(activity).create(activity, childFieldData).createView());
                    }
                }
            }

        }
        return view;
    }

    protected LinearLayout createLabel(ViewData groupData) {
        LinearLayout groupContainer = new LinearLayout(activity);
        groupContainer.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams groupLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        groupLayoutParam.setMargins(0,40,0,10);
        groupContainer.setLayoutParams(groupLayoutParam);
        LinearLayout groupLabelContainer = new LinearLayout(activity);
//        groupLabelContainer.setGravity(Gravity.CENTER_VERTICAL);
        groupLabelContainer.setLayoutParams( new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100));
        groupLabelContainer.setOrientation(LinearLayout.HORIZONTAL);

        TextView label = new TextView(activity);
        label.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f ));
        label.setText(ResourceUtil.getStringResourceByName(activity, groupData.getLabel()));
        label.setTextColor(activity.getColor(R.color.blue_light));
        groupLabelContainer.addView(label);

        if (groupData.isEditTable()) {
            ImageView imageView = new ImageView(activity);
            imageView.setImageResource(R.drawable.ic_angle_right);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.1f ));
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            ColorStateList tintList = ColorStateList.valueOf(activity.getResources().getColor(R.color.blue_light));
            imageView.setBackgroundTintList(tintList);
            groupLabelContainer.addView(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DragDropDocListBottomSheetFragment demoBottomsheet = new DragDropDocListBottomSheetFragment(true, "Expanded state");
                    demoBottomsheet.setTitle(ResourceUtil.getStringResourceByName(activity, groupData.getLabel()));
                    demoBottomsheet.setDocumentInfo(DocumentInfo.ITEM);
                    demoBottomsheet.setEventListner(new DocumentListBottomSheetFragmentEventListner() {
                        @Override
                        public void onItemSelected(DocumentSnapshot documentSnapshot) {

                        }
                    });
                    demoBottomsheet.show(activity.getSupportFragmentManager(), "Expanded");
                }
            });
        }

        groupContainer.addView(groupLabelContainer);
        View view = new View(activity);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 3));
        view.setBackgroundColor(activity.getColor(R.color.blue_light));
        groupContainer.addView(view);
        return groupContainer;
    }

    private View createActionButtons(Action[] actions) {
        LinearLayout layout = new LinearLayout(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for (final Action action: actions) {
            Context newContext = new ContextThemeWrapper(activity, R.style.OutlinedCircleButton);
            MaterialButton button = new MaterialButton(newContext);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(120, LinearLayout.LayoutParams.MATCH_PARENT);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onActionButtonClicked(action);
                }
            });
            button.setLayoutParams(param);
            int icon = activity.getResources().getIdentifier(action.getIcon(), "drawable", activity.getPackageName());
            button.setIcon(activity.getDrawable(icon));
            layout.addView(button);
        }
        return layout;
    }

    private void onActionButtonClicked(Action action) {
    }

}
