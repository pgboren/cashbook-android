package com.soleap.cashbook.common.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventHandler {

    private static EventHandler instance;

    public static EventHandler getInstance() {
        if (instance == null) {
            instance = new EventHandler();
        }
        return instance;
    }

    private Map<String, List<DocCreatedEventListner>> docCreatedEventListnersMap = new HashMap<>();

    private Map<String, List<DocChangedEventListner>> docChangedEventListnersMap = new HashMap<>();

    public void addDocChangedEventListner(String key, DocChangedEventListner listner) {
        if (!docChangedEventListnersMap.containsKey(key)) {
            docChangedEventListnersMap.put(key, new ArrayList<DocChangedEventListner>());
        }
        List<DocChangedEventListner> listners = docChangedEventListnersMap.get(key);
        listners.add(listner);
    }

    public void removeDocChangedEventListner(String key, DocChangedEventListner listner) {
        if (docChangedEventListnersMap.containsKey(key)) {
            List<DocChangedEventListner> listners = docChangedEventListnersMap.get(key);
            if (listners.contains(listner)) {
                listners.remove(listner);
            }
        }
    }

    public void addDocCreatedEventListner(String key, DocCreatedEventListner listner) {
        if (!docCreatedEventListnersMap.containsKey(key)) {
            docCreatedEventListnersMap.put(key, new ArrayList<DocCreatedEventListner>());
        }
        List<DocCreatedEventListner> listners = docCreatedEventListnersMap.get(key);
        listners.add(listner);
    }

    public void removeDocCreatedEventListner(String key, DocCreatedEventListner listner) {
        if (docCreatedEventListnersMap.containsKey(key)) {
            List<DocCreatedEventListner> listners = docCreatedEventListnersMap.get(key);
            if (listners.contains(listner)) {
                listners.remove(listner);
            }
        }
    }

    public void notifyDocumentChanged(String key, String docId) {
        if (docChangedEventListnersMap.containsKey(key)) {
            List<DocChangedEventListner> listners = docChangedEventListnersMap.get(key);
            for (DocChangedEventListner listner : listners) {
                listner.onChanged(docId);
            }
        }
    }

    public void notifyDocumentChangedError(String key, Throwable t) {
        if (docChangedEventListnersMap.containsKey(key)) {
            List<DocChangedEventListner> listners = docChangedEventListnersMap.get(key);
            for (DocChangedEventListner listner : listners) {
                listner.onError(t);
            }
        }
    }

    public void notifyDocumentCreated(String key, String docId) {
        if (docCreatedEventListnersMap.containsKey(key)) {
            List<DocCreatedEventListner> listners = docCreatedEventListnersMap.get(key);
            for (DocCreatedEventListner listner : listners) {
                listner.onAdded(docId);
            }
        }
    }

    public void notifyDocumentCreatedError(String key, Throwable t) {
        if (docCreatedEventListnersMap.containsKey(key)) {
            List<DocCreatedEventListner> listners = docCreatedEventListnersMap.get(key);
            for (DocCreatedEventListner listner : listners) {
                listner.onError(t);
            }
        }
    }



}
