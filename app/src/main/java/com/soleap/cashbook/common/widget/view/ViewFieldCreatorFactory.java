package com.soleap.cashbook.common.widget.view;

import android.content.Context;

import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.ViewData;
public class ViewFieldCreatorFactory {
    private static ViewFieldCreatorFactory instance;
    public ViewFieldCreatorFactory() {
    }
    public static ViewFieldCreatorFactory getInstance(Context context) {
        if (instance == null) {
            instance = new ViewFieldCreatorFactory();
        }
        return instance;
    }

    public FieldCreator create(ViewDataActivity viewActivity, ViewData fieldData) {

        if (fieldData.getDataType().equals(FieldType.TEXT)) {
            return new ViewTextFieldCreator(viewActivity, fieldData);
        }

        if (fieldData.getDataType().equals(FieldType.RESOURCE_STRING)) {
            return new ViewResourceStringTextFieldCreator(viewActivity, fieldData);
        }

        if (fieldData.getDataType().equals(FieldType.CONTACT)) {
            return new ViewContactFieldCreator(viewActivity, fieldData);
        }

        if (fieldData.getDataType().equals(FieldType.ITEM)) {

            return new ViewItemFieldCreator(viewActivity, fieldData);
        }

        if (fieldData.getDataType().equals(FieldType.GROUP)) {
            return new ViewGroupFieldCreator(viewActivity, fieldData);
        }

        if (fieldData.getDataType().equals(FieldType.PHOTO)) {
            return new ViewPhotoFieldCreator(viewActivity, fieldData);
        }

        if (fieldData.getDataType().equals(FieldType.PHONENUMBER)) {
            return new ViewPhoneNumberFieldCreator(viewActivity, fieldData);
        }

        if (fieldData.getDataType().equals(FieldType.FACEBOOK)) {
            return new ViewMessengerFieldCreator(viewActivity, fieldData);
        }


        if (fieldData.getDataType().equals(FieldType.TELEGRAM)) {
            return new ViewTelegramFieldCreator(viewActivity, fieldData);
        }

        if (fieldData.getDataType().equals(FieldType.CURRENCY)) {
            return new ViewCurrencyFieldCreator(viewActivity, fieldData);
        }

        return new ViewTextFieldCreator(viewActivity, fieldData);

    }


}
