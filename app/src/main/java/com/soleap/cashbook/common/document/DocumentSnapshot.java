package com.soleap.cashbook.common.document;

import java.util.Map;

public class DocumentSnapshot extends Document {

    private String className;
    private Map<String, ViewData> data;
    private String contextMenu;

    public String title;

    private Document document;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Map<String, ViewData> getData() {
        return data;
    }

    public void setData(Map<String, ViewData> data) {
        this.data = data;
    }
    
    public void onCreated(String id) {
        this.setId(id);
    }

    public String getContextMenu() {
        return contextMenu;
    }

    public void setContextMenu(String contextMenu) {
        this.contextMenu = contextMenu;
    }

    public ViewData getDataValue(String key) {
        return getData().get(key);
    }

    public static class Media extends Document {

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

    }
}
