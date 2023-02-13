package com.soleap.cashbook.document;

import com.soleap.cashbook.common.document.Document;

public class SaleOrder extends Document {

    public static String ORDER_PAYMENT_VEHICLE_STATUS_NEW = "ORDER_PAYMENT_VEHICLE_STATUS_NEW";
    public static String ORDER_PAYMENT_VEHICLE_STATUS_USED = "ORDER_PAYMENT_VEHICLE_STATUS_USED";
    public static String ORDER_PAYMENT_VEHICLE_STATUS_SECONDHAND = "ORDER_PAYMENT_VEHICLE_STATUS_SECONDHAND";

    public static String ORDER_PAYMENT_TYPE_LOAN = "ORDER_PAYMENT_TYPE_LOAN";
    public static String ORDER_PAYMENT_TYPE_FULL_PAY = "ORDER_PAYMENT_TYPE_FULL_PAY";

    public static String ORDER_STATUS_NEW = "NEW";
    public static String ORDER_STATUS_DOCUMENTATION = "DOCUMENTATION";
    public static String ORDER_STATUS_LOAN_REQUEST = "ORDER_LOAN_REQUEST";
    public static String ORDER_STATUS_APPROVED = "ORDER_APPROVED";
    public static String ORDER_STATUS_INVOICE = "ORDER_INVOICE";
    public static String ORDER_STATUS_PAID = "ORDER_PAID";

    private String number;
    private String branch;
    private String customer;
    private int date;
    private String paymentOption;
    private String institute;
    private String status;
    private String item;
    private double quantity;
    private double price;
    private double bookingAmount;
    private String vehicleCondition;

    public String getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
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

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
