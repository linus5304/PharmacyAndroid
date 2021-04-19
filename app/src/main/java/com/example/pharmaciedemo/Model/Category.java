package com.example.pharmaciedemo.Model;

public class Category {
    private String catId;
    private String catName;


    public Category() {

    }

    @Override
    public String toString() {
        return "Category{" +
                "catId='" + catId + '\'' +
                ", catName='" + catName + '\'' +
                '}';
    }

    public Category(String catId, String catName) {
        this.catId = catId;
        this.catName = catName;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
