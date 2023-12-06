package com.soleap.cashbook.common.document;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class Media extends Document {

        private String name;
        private int size;
        private String mimetype;
        private String path;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getMimetype() {
            return mimetype;
        }

        public void setMimetype(String mimetype) {
            this.mimetype = mimetype;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", getName());
        data.put("size", getSize());
        data.put("mimetype", getMimetype());
        data.put("path", getPath());
        return data;
    }
    @Override
    public void fromJsonObject(JsonElement jsonElement) {
        super.fromJsonObject(jsonElement);
        JsonObject jsonObject = (JsonObject) jsonElement;
        set_id(jsonObject.get("_id").getAsString());
        setName(jsonObject.get("name").getAsString());
        setSize(jsonObject.get("size").getAsInt());
        setMimetype(jsonObject.get("mimetype").getAsString());
        setPath(jsonObject.get("path").getAsString());
    }
}