package com.soleap.cashbook.document;

import android.content.Context;

import com.soleap.cashbook.R;

public class CurrencyFactory {

    public static String US_DOLLAR = "US_DOLLAR";
    public static String KH_RIEL = "KH_RIEL";

    public static Currency getCurrency(Context context, String key) {
        if (key.equals(CurrencyFactory.US_DOLLAR)) {
            Currency currency = new Currency();
            currency.setName(context.getString(R.string.us_dollar));
            currency.setFormat("$#,###.00");
            return  currency;
        }

        if (key.equals(CurrencyFactory.KH_RIEL)) {
            Currency currency = new Currency();
            currency.setName(context.getString(R.string.kh_rield));
            currency.setFormat("#,###.00 រៀល");
            return  currency;
        }
        return null;
    }

}
