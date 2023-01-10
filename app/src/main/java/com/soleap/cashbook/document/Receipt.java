package com.soleap.cashbook.document;

import com.google.gson.annotations.SerializedName;
import com.soleap.cashbook.common.document.Document;

public class Receipt extends Document {

    public static final String CASH_RECEIPT  = "CASH_RECEIPT";
    public static final String BOOKING  = "BOOKING";

    @SerializedName("number")
    private int number;
    @SerializedName("payee")
    private Customer payee;
    @SerializedName("date")
    private long date;
    @SerializedName("amount")
    private double amount;
    @SerializedName("type")
    private String type;
    @SerializedName("descripiton")
    private String descripiton;
    @SerializedName("order")
    private String order;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Customer getPayee() {
        return payee;
    }

    public void setPayee(Customer payee) {
        this.payee = payee;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescripiton() {
        return descripiton;
    }

    public void setDescripiton(String descripiton) {
        this.descripiton = descripiton;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

}
