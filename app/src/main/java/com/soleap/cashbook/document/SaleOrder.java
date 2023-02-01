package com.soleap.cashbook.document;

import com.google.gson.annotations.SerializedName;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.ViewData;

import java.util.List;
import java.util.Map;

public class SaleOrder extends Document {
    private String number;
    private ViewData branch;
    private ViewData customer;
    private int date;
    private double bookingAmount;
    private String paymentType;
    private ViewData institute;
    private String status;
    private List<SaleOrderItem> items;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ViewData getBranch() {
        return branch;
    }

    public void setBranch(ViewData branch) {
        this.branch = branch;
    }

    public ViewData getCustomer() {
        return customer;
    }

    public void setCustomer(ViewData customer) {
        this.customer = customer;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public double getBookingAmount() {
        return bookingAmount;
    }

    public void setBookingAmount(double bookingAmount) {
        this.bookingAmount = bookingAmount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public ViewData getInstitute() {
        return institute;
    }

    public void setInstitute(ViewData institute) {
        this.institute = institute;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SaleOrderItem> getItems() {
        return items;
    }

    public void setItems(List<SaleOrderItem> items) {
        this.items = items;
    }

    @Override
    public Map<String, Object> toMap() {
        return  null;
    }
}
