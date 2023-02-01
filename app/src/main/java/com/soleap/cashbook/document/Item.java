package com.soleap.cashbook.document;

import com.soleap.cashbook.common.document.BsDocument;

import java.util.HashMap;
import java.util.Map;

public class Item extends BsDocument {

    public static String NEW = "NEW";
    public static String USED = "USED";
    public static String SECOND_HAND = "SECOND_HAND";

    private String nameKh;
    private String status;
    private String description;
    private double price;
    private double installmentPaymentPrice;
    private double cost;
    private String branch;
    private Category category;
    private Color color;
    private Media photo;
    private String year;
    private String power;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getNameKh() {
        return nameKh;
    }

    public void setNameKh(String nameKh) {
        this.nameKh = nameKh;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getInstallmentPaymentPrice() {
        return installmentPaymentPrice;
    }

    public void setInstallmentPaymentPrice(double installmentPaymentPrice) {
        this.installmentPaymentPrice = installmentPaymentPrice;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Media getPhoto() {
        return photo;
    }

    public void setPhoto(Media photo) {
        this.photo = photo;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", getName());
        data.put("nameKh", getNameKh());
        data.put("status", getStatus());
        data.put("description", getDescription());
        data.put("price",getPrice());
        data.put("installmentPaymentPrice",getInstallmentPaymentPrice());
        data.put("cost", getCost());
        data.put("year", getYear());
        data.put("power", getPower());
        data.put("branch", getBranch());
        data.put("category", getCategory().getId());
        data.put("color", getColor().getId());
        data.put("enable", enable);
        if (getPhoto() != null) {
            data.put("photo", getPhoto().getId());
        }
        return data;
    }
}
