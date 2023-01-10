package com.soleap.cashbook.document;

import com.google.gson.annotations.SerializedName;
import com.soleap.cashbook.common.document.Document;

public class Deal extends Document {
    @SerializedName("number")
    private String number;
    @SerializedName("branch")
    private Branch branch;
    @SerializedName("customer")
    private Contact customer;
    @SerializedName("date")
    private long date;
    @SerializedName("price")
    private double price;
    @SerializedName("vehicleChassisNo")
    private String vehicleChassisNo;
    @SerializedName("vehicleEngineNo")
    private String vehicleEngineNo;
    @SerializedName("paymentType")
    private String paymentType;
    @SerializedName("institute")
    private Institute institute;
    @SerializedName("item")
    private Item item;
    @SerializedName("status")
    private String status;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Contact getCustomer() {
        return customer;
    }

    public void setCustomer(Contact customer) {
        this.customer = customer;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getVehicleChassisNo() {
        return vehicleChassisNo;
    }

    public void setVehicleChassisNo(String vehicleChassisNo) {
        this.vehicleChassisNo = vehicleChassisNo;
    }

    public String getVehicleEngineNo() {
        return vehicleEngineNo;
    }

    public void setVehicleEngineNo(String vehicleEngineNo) {
        this.vehicleEngineNo = vehicleEngineNo;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
