package com.soleap.cashbook.datasync;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.soleap.cashbook.common.document.Color;
import com.soleap.cashbook.common.document.RefDocument;
import com.soleap.cashbook.document.Commune;
import com.soleap.cashbook.document.District;
import com.soleap.cashbook.document.Province;
import com.soleap.cashbook.document.Village;
import com.soleap.cashbook.document.Category;
import java.lang.reflect.Type;

public class RefDataDeserializer implements JsonDeserializer<RefData> {
    @Override
    public RefData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonObject data =  jsonObject.getAsJsonObject("data");
//        JsonArray provoinceArray = data.getAsJsonArray("provinces");
        RefData doc = new RefData();
//        for (JsonElement jsonElement: provoinceArray) {
//            JsonObject provinceObj = jsonElement.getAsJsonObject();
//            Province province = new Province();
//            province.name = provinceObj.get("name").getAsString();
//            province.code = provinceObj.get("code").getAsString();
//            province._id = provinceObj.get("_id").getAsString();
//            doc.addProvince(province);
//        }
//
//        JsonArray districtArray = data.getAsJsonArray("districts");
//        for (JsonElement jsonElement: districtArray) {
//            JsonObject districtObj = jsonElement.getAsJsonObject();
//            District district = new District();
//            district.name = districtObj.get("name").getAsString();
//            district.code = districtObj.get("code").getAsString();
//            district.province = districtObj.get("province").getAsString();
//            district._id = districtObj.get("_id").getAsString();
//            doc.addDistrict(district);
//        }
//
//        JsonArray communeArray = data.getAsJsonArray("communes");
//        for (JsonElement jsonElement: communeArray) {
//            JsonObject dataObj = jsonElement.getAsJsonObject();
//            Commune commune = new Commune();
//            commune.name = dataObj.get("name").getAsString();
//            commune.code = dataObj.get("code").getAsString();
//            commune.district = dataObj.get("district").getAsString();
//            commune._id = dataObj.get("_id").getAsString();
//            doc.addCommune(commune);
//        }
//
//        JsonArray villagesArray = data.getAsJsonArray("villages");
//        for (JsonElement jsonElement: villagesArray) {
//            JsonObject dataObj = jsonElement.getAsJsonObject();
//            Village village = new Village();
//            village.name = dataObj.get("name").getAsString();
//            village.commune = dataObj.get("commune").getAsString();
//            village._id = dataObj.get("_id").getAsString();
//            doc.addVillage(village);
//        }

        addDatasToDoc("category", data, doc);
        addDatasToDoc("maker", data, doc);
        addDatasToDoc("type", data, doc);
        addDatasToDoc("condition", data, doc);
        addDatasToDoc("model", data, doc);
        addDatasToDoc("color", data, doc);
        return  doc;
    }

    private void addDatasToDoc(String docName, JsonObject jsonObject, RefData doc) {
        JsonArray categoriesArray = jsonObject.getAsJsonArray(docName);
        for (JsonElement jsonElement: categoriesArray) {
            JsonObject dataObj = jsonElement.getAsJsonObject();
            RefDocument document = new RefDocument();
            document._id = dataObj.get("_id").getAsString();
            document.name = dataObj.get("name").getAsString();
            document.type = docName;
            doc.addData(document);
        }
    }

    private void addColorsToDoc(String docName, JsonObject jsonObject, RefData doc) {
        JsonArray categoriesArray = jsonObject.getAsJsonArray(docName);
        for (JsonElement jsonElement: categoriesArray) {
            JsonObject dataObj = jsonElement.getAsJsonObject();
            Color document = new Color();
            document._id = dataObj.get("_id").getAsString();
            document.name = dataObj.get("name").getAsString();
            document.code = dataObj.get("code").getAsString();
            document.type = docName;
            doc.addColor(document);
        }
    }

}
