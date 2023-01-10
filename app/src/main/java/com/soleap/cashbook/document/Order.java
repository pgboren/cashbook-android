package com.soleap.cashbook.document;

import com.google.gson.annotations.SerializedName;
import com.soleap.cashbook.common.document.Document;

public class Order extends Document {

    public static final String FULL_PAYMENT  = "FULL_PAYMENT";
    public static final String INSTALLMENT  = "INSTALLMENT";
    public static final String NEW = "NEW";
    public static final String BOOKED = "BOOKED";
    public static final String SUBMIT_APPLICATION = "SUBMIT_APPLICATION";
    public static final String STUDIED = "STUDIED";
    public static final String CANCELED = "CANCELED";
    public static final String REJECTED = "REJECTED";
    public static final String APPROVED = "APPROVED";
    public static final String INVOICED = "INVOICED";
    public static final String OFFERED_VEHICLE = "OFFERED_VEHICLE";
    public static final String PAYMENT = "PAYMENT";
    public static final String CLOSED = "CLOSED";

    @SerializedName("number")
    private int number;
    @SerializedName("date")
    private long date;
    @SerializedName("price")
    private double price;
    @SerializedName("customer")
    private String customer;
    @SerializedName("vehicleYear")
    private String vehicleYear;
    @SerializedName("vehicleChassisNo")
    private String vehicleChassisNo;
    @SerializedName("vehicleEngineNo")
    private String vehicleEngineNo;
    @SerializedName("vehicleColor")
    private String vehicleColor;
    @SerializedName("paymentType")
    private String paymentType;
    @SerializedName("institute")
    private Institute institute;
    @SerializedName("vehicle")
    private String vehicle;
    @SerializedName("status")
    private String status;

    @SerializedName("booking")
    private Receipt booking;

    public Receipt getBooking() {
        return booking;
    }

    public void setBooking(Receipt booking) {
        this.booking = booking;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(String vehicleYear) {
        this.vehicleYear = vehicleYear;
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

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

}
