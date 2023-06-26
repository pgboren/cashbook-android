package com.soleap.cashbook.document;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.view.DocumentInfo;

import java.lang.reflect.Type;

public class DocumentDeserializer implements JsonDeserializer<Document> {
    @Override
    public Document deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            JsonObject jsonObject = json.getAsJsonObject();
            String docName = jsonObject.get("type").getAsString();
            Class docClass = DocumentInfo.getDocumentClass(docName);
            Document doc = (Document) docClass.newInstance();
            doc.fromJsonObject(jsonObject.get("doc"));
            return  doc;
        } catch (IllegalAccessException e) {
            throw new JsonParseException(e);
        } catch (InstantiationException e) {
            throw new JsonParseException(e);
        }
    }
}
