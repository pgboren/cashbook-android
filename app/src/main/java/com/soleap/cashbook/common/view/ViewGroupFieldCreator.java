package com.soleap.cashbook.common.view;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.Action;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.util.ResourceUtil;

import java.util.Map;

public class ViewGroupFieldCreator extends FieldCreator {

    public ViewGroupFieldCreator(ViewDataActivity activity, ViewData data) {
        super(activity, data);
    }

    @Override
    public View createView() {
        ViewGroup view = createLabel(fieldData);
        Map<String, ViewData> fieldDatas = fieldData.getChildrent();
        if (fieldDatas != null && fieldDatas.size() > 0) {
            for (ViewData childFieldData: fieldDatas.values()) {
                if (childFieldData.getVisible() == View.VISIBLE) {
                    view.addView(ViewFieldCreatorFactory.getInstance(activity).create(activity, childFieldData).createView());
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
        groupLabelContainer.setGravity(Gravity.CENTER_VERTICAL);
        groupLabelContainer.setLayoutParams( new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100));
        groupLabelContainer.setOrientation(LinearLayout.HORIZONTAL);
        TextView label = new TextView(activity);
        label.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f ));
        label.setText(ResourceUtil.getStringResourceByName(activity, groupData.getLabel()));
        label.setTextColor(activity.getColor(R.color.blue_light));
        groupLabelContainer.addView(label);
        if (groupData.getActions() != null) {
            groupLabelContainer.addView(createActionButtons(groupData.getActions()));
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
