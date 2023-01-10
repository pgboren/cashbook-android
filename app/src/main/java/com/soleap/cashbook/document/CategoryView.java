package com.soleap.cashbook.document;

import java.util.ArrayList;
import java.util.List;

public class CategoryView {

    private Category category;

    private List<Category> subCategories;

    private List<Item> products;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public void addSubCategory(Category category) {
        if(this.subCategories == null) {
            this.subCategories = new ArrayList<Category>();
        }
        this.subCategories.add(category);
    }

    public List<Item> getProducts() {
        return products;
    }

    public void setProducts(List<Item> products) {
        this.products = products;
    }

    public void addProduct(Item product) {
        if (this.products == null) {
            products = new ArrayList<Item>();
        }
        products.add(product);
    }
}
