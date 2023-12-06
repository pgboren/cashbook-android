package com.soleap.cashbook;

import android.content.Context;

import com.soleap.cashbook.common.document.TextEnumValueDocument;
import com.soleap.cashbook.room.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public final class Global {

    public static Context context;

    public static final String API_BASE_URL = "http://192.168.8.104:8080/api/";
    public static final String agile_board = "641065cb7a8514141033c11f";
    public static final String agile_stage = "641065cb7a8514141033c121";
    public static final String build_type = "release";

    public static AppDatabase db;

    public static List<TextEnumValueDocument> getTextEnumDoc(Context context, String key) {

        if (key.equals("MAKER")) {
            return getMakers(context);
        }

        if (key.equals("TYPE")) {
            return getTypes(context);
        }

        if (key.equals("CONDITION")) {
            return getConditions(context);
        }

        throw new RuntimeException("Stub!");
    }

    private static List<TextEnumValueDocument> getMakers(Context context) {

        List<TextEnumValueDocument> makers = new ArrayList<>();
        TextEnumValueDocument doc = new TextEnumValueDocument();
        doc.setText(context.getString(R.string.honda));
        doc.setKey("HONDA");
        makers.add(doc);

        doc = new TextEnumValueDocument();
        doc.setText(context.getString(R.string.yamaha));
        doc.setKey("YAMAHA");
        makers.add(doc);
        return makers;
    }

    private static List<TextEnumValueDocument> getTypes(Context context) {

        List<TextEnumValueDocument> makers = new ArrayList<>();
        TextEnumValueDocument doc = new TextEnumValueDocument();
        doc.setText(context.getString(R.string.SCOOTER));
        doc.setKey("SCOOTER");
        makers.add(doc);

        doc = new TextEnumValueDocument();
        doc.setText(context.getString(R.string.OFF_ROAD));
        doc.setKey("OFF_ROAD");
        makers.add(doc);

        doc = new TextEnumValueDocument();
        doc.setText(context.getString(R.string.SPORT));
        doc.setKey("SPORT");
        makers.add(doc);

        return makers;
    }

    private static List<TextEnumValueDocument> getConditions(Context context) {

        List<TextEnumValueDocument> makers = new ArrayList<>();
        TextEnumValueDocument doc = new TextEnumValueDocument();
        doc.setText(context.getString(R.string.NEW));
        doc.setKey("NEW");
        makers.add(doc);

        doc = new TextEnumValueDocument();
        doc.setText(context.getString(R.string.USED));
        doc.setKey("USED");
        makers.add(doc);

        doc = new TextEnumValueDocument();
        doc.setText(context.getString(R.string.SECONDHAND));
        doc.setKey("SECONDHAND");
        makers.add(doc);

        return makers;
    }
}
