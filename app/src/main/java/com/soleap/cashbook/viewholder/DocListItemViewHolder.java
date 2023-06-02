package com.soleap.cashbook.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.Global;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.repository.DocumentSnapshotRepository;
import com.soleap.cashbook.common.repository.RepositoryFactory;
import com.soleap.cashbook.common.value.ViewSetterFactory;
import com.soleap.cashbook.document.DocumentName;

import java.util.Map;

public abstract class DocListItemViewHolder extends RecyclerView.ViewHolder  implements ListItemViewHolder {

    protected Context context;
    private String docName;
    private String viewName;
    private int resourceFileLayout;

    protected abstract void bindViewContent(DocumentSnapshot doc);

    public DocListItemViewHolder(Context activity, View itemView, String viewName, String docName, int resourceFileLayout) {
        super(itemView);
        this.context = activity;
        this.docName = docName;
        this.viewName = viewName;
        this.resourceFileLayout = resourceFileLayout;
    }

    @Override
    public String getViewName() {
        return viewName;
    }

    @Override
    public String getDocName() {
        return docName;
    }

    @Override
    public int getResourceFileLayout() {
        return resourceFileLayout;
    }

    public void bind(int position, DocumentSnapshot doc) {
        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
        String id = doc.getId();
        if (Global.build_type.equals("debug")) {
            itemView.findViewById(R.id.txt_id).setVisibility(View.VISIBLE);
            viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_id).setString((id));
        }
        TextView txtIndex = itemView.findViewById(R.id.txt_index);
        txtIndex.setText(String.valueOf(position + 1));
        bindViewContent(doc);
    }

    //    protected void bindTask(View itemView, int position, DocumentSnapshot doc) {
//        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
//        String name = doc.getDataValue("name").getValue().toString();
//        String date = doc.getDataValue("date").getValue().toString();
//        String desc = doc.getDataValue("description").getValue().toString();
//        String paymentOption = doc.getDataValue("paymentOption").getValue().toString();
//        ViewData itemData = doc.getDataValue("item");
//        String stage = doc.getDataValue("stage").getDataValue("name").getValue().toString();
//        String colorCode = doc.getDataValue("stage").getDataValue("color").getValue().toString();
//        TextView txtColor = itemView.findViewById(R.id.txt_stage);
//        txtColor.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorCode)));
//        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_customer_name).setString((name));
//        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_date).setString((date));
//        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_item).setString((itemData.getDataValue("name").getValue().toString()));
//        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_description).setString((desc));
//        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_payment_option).setString(ResourceUtil.getStringResourceByName(itemView.getContext(), paymentOption));
//        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_stage).setString((stage));
//    }
//
//    protected void bindDealItem(View itemView, int position, DocumentSnapshot doc) {
//        ViewData data = doc.getDataValue("deal");
//        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
//    }
//
//    private void bindInsitituteItem(View itemView, int position, DocumentSnapshot doc) {
//        ViewData data = doc.getDataValue("root").getDataValue("general");
//        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
//        String name = data.getDataValue("name").getValue().toString();
//        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_customer_name).setString((name));
//        TextView shortcut = itemView.findViewById(R.id.txt_no);
//        TextView circleBox = itemView.findViewById(R.id.circle_box);
//
//        ImageView imageView = (ImageView)itemView.findViewById(R.id.imv_item_photo);
//        if (data.getDataValue("photo").getValue() != null) {
//            imageView.setVisibility(View.VISIBLE);
//            circleBox.setVisibility(View.GONE);
//            MedialUtils.loadImage(activity, data.getDataValue("photo").getValue().toString(), imageView);
//        }
//        else {
//            imageView.setVisibility(View.GONE);
//            circleBox.setVisibility(View.VISIBLE);
//        }
//
//        circleBox.setText(name.substring(0, 1).toUpperCase());
//        shortcut.setText(name.substring(0, 1).toUpperCase());
//    }
//
//    private void bindContact(View itemView, int position, DocumentSnapshot doc) {
//        ViewData data = doc.getDataValue("root").getDataValue("general");
//        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
//        String name = data.getDataValue("name").getValue().toString();
//        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_customer_name).setString((name));
//        TextView shortcut = itemView.findViewById(R.id.txt_no);
//        TextView circleBox = itemView.findViewById(R.id.circle_box);
//
//        ImageView imageView = (ImageView)itemView.findViewById(R.id.imv_item_photo);
//        if (data.getDataValue("photo").getValue() != null) {
//            imageView.setVisibility(View.VISIBLE);
//            circleBox.setVisibility(View.GONE);
//            MedialUtils.loadImage(activity, data.getDataValue("photo").getValue().toString(), imageView);
//        }
//        else {
//            imageView.setVisibility(View.GONE);
//            circleBox.setVisibility(View.VISIBLE);
//        }
//
//        circleBox.setText(name.substring(0, 1).toUpperCase());
//        shortcut.setText(name.substring(0, 1).toUpperCase());
//    }
//
//
//    void bindItem(View itemView, final int position, final DocumentSnapshot doc) {
//        ViewData data = doc.getDataValue("root").getDataValue("general");
//        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
//        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_customer_name).setString((data.getDataValue("name").getValue().toString()));
//        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_name_kh).setString((data.getDataValue("nameKh").getValue().toString()));
//        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_category).setString((data.getDataValue("category").getValue().toString()));
//        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_full_price).setCurrency(Double.parseDouble(data.getDataValue("price").getValue().toString()), Locale.US);
//        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_isntallment_price).setCurrency(Double.parseDouble(data.getDataValue("installmentPaymentPrice").getValue().toString()), Locale.US);
//        TextView circleBox = itemView.findViewById(R.id.circle_box);
//        TextView shortcut = itemView.findViewById(R.id.txt_no);
//        ImageView imageView = (ImageView)itemView.findViewById(R.id.imv_item_photo);
//        if (data.getDataValue("photo").getValue() != null) {
//            imageView.setVisibility(View.VISIBLE);
//            circleBox.setVisibility(View.GONE);
//            MedialUtils.loadImage(activity, data.getDataValue("photo").getValue().toString(), imageView);
//        }
//        else {
//            imageView.setVisibility(View.GONE);
//            circleBox.setVisibility(View.VISIBLE);
//        }
//        shortcut.setText(String.valueOf(position + 1));
//    }

}
