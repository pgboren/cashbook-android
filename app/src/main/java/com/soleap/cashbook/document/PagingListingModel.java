package com.soleap.cashbook.document;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.JsonAdapter;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.view.DocumentInfo;

import java.util.ArrayList;
import java.util.List;

@JsonAdapter(PagingListingModelDeserializer.class)
public class PagingListingModel {
    private int currentPage;
    private int totalPages;
    private int totalItems;

    public void setData(List<Document> data) {
        this.data = data;
    }

    private List<Document> data;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<Document> getData() {
        return data;
    }

    public void fromJsonObject(JsonElement jsonElement) {
        try {
            JsonObject jsonObject = (JsonObject) jsonElement;
            setCurrentPage(jsonObject.get("currentPage").getAsInt());
            setTotalItems(jsonObject.get("totalItems").getAsInt());
            setTotalPages(jsonObject.get("totalPages").getAsInt());
            setCurrentPage(jsonObject.get("currentPage").getAsInt());
            JsonArray dataArray = jsonObject.get("data").getAsJsonArray();
            String docName = jsonObject.get("type").getAsString();
            Class docClass = DocumentInfo.getDocumentClass(docName);

            if (data == null) {
                data = new ArrayList<>();
            }
            for (int i = 0; i < dataArray.size() ; i++) {
                JsonObject dataObject = dataArray.get(i).getAsJsonObject();
                Document doc = (Document) docClass.newInstance();
                doc.fromJsonObject(dataObject);
                data.add(doc);
            }
        } catch (IllegalAccessException e) {
            throw new JsonParseException(e);
        } catch (InstantiationException e) {
            throw new JsonParseException(e);
        }


    }
}
