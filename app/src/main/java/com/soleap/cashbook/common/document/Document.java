package com.soleap.cashbook.common.document;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.soleap.cashbook.document.DocumentDeserializer;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@JsonAdapter(DocumentDeserializer.class)
public abstract class Document implements Serializable {

    @SerializedName("_id")
    @PrimaryKey
    @NonNull
    public String _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try { map.put(field.getName(), field.get(this)); } catch (Exception e) { }
        }
        return map;
    }
    public void fromJsonObject(JsonElement jsonElement) {
        JsonObject jsonObject = (JsonObject) jsonElement;
        set_id(jsonObject.get("_id").getAsString());
    }
}
