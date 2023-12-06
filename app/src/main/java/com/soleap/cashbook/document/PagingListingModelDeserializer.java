package com.soleap.cashbook.document;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.view.DocumentInfo;

import java.lang.reflect.Type;

public class PagingListingModelDeserializer implements JsonDeserializer<PagingListingModel> {

    @Override
    public PagingListingModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        PagingListingModel pagingListingModel = new PagingListingModel();
        pagingListingModel.fromJsonObject(jsonObject);
        return pagingListingModel;
    }
}
