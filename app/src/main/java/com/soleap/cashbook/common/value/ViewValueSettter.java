package com.soleap.cashbook.common.value;

import java.util.Date;
import java.util.Locale;

public abstract class ViewValueSettter {

    public abstract void setLong(long value);
    public abstract void setLong(long value, String format);
    public abstract void setDouble(double value, String format);
    public abstract void setDate(long value, String format);
    public abstract void setDate(Date value, String format);
    public abstract void setString(String value);
    public abstract void setCurrency(double value, Locale locale);
}
