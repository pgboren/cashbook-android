package com.soleap.cashbook.datasync;

import com.google.gson.annotations.JsonAdapter;
import com.soleap.cashbook.common.document.Color;
import com.soleap.cashbook.common.document.RefDocument;
import com.soleap.cashbook.document.Category;
import com.soleap.cashbook.document.Commune;
import com.soleap.cashbook.document.District;
import com.soleap.cashbook.document.Province;
import com.soleap.cashbook.document.Village;

import java.util.ArrayList;
import java.util.List;

@JsonAdapter(RefDataDeserializer.class)
public class RefData {

    private List<Province> provinces = new ArrayList<>();
    private List<District> districts = new ArrayList<>();
    private List<Commune> communes = new ArrayList<>();
    private List<Village> villages = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

    private List<RefDocument> data = new ArrayList<>();

    private List<Color> colors = new ArrayList<>();

    public List<Province> getProvinces() {
        return provinces;
    }

    public void addProvince(Province province) {
        provinces.add(province);
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void addDistrict(District district) {
        this.districts.add(district);
    }

    public List<Commune> getCommunes() {
        return communes;
    }

    public void addCommune(Commune commune) {
        this.communes.add(commune);
    }

    public List<Village> getVillages() {
        return villages;
    }

    public void addVillage(Village village) {
        this.villages.add(village);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public List<RefDocument> getData() {
        return data;
    }

    public void addData(RefDocument refDocument) {
        this.data.add(refDocument);
    }

    public List<Color> getColors() {
        return colors;
    }

    public void addColor(Color color) {
        this.colors.add(color);
    }
}
