package com.example.pharmaciedemo.Model;

public class Drug {
    private String itemName;
    private String itemCode;
    private String itemCostPrice;
    private String itemSellingPrice;
    private String quantity;
    private String imageUrl;
    private String itemCategory;

    public Drug() {

    }

    @Override
    public String toString() {
        return "Drug{" +
                "itemName='" + itemName + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemCostPrice='" + itemCostPrice + '\'' +
                ", itemSellingPrice='" + itemSellingPrice + '\'' +
                ", quantity='" + quantity + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", itemCategory='" + itemCategory + '\'' +
                '}';
    }

    public Drug(String itemName, String itemCode, String itemCostPrice, String itemSellingPrice, String quantity, String imageUrl, String itemCategory) {
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.itemCostPrice = itemCostPrice;
        this.itemSellingPrice = itemSellingPrice;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.itemCategory = itemCategory;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemCostPrice() {
        return itemCostPrice;
    }

    public void setItemCostPrice(String itemCostPrice) {
        this.itemCostPrice = itemCostPrice;
    }

    public String getItemSellingPrice() {
        return itemSellingPrice;
    }

    public void setItemSellingPrice(String itemSellingPrice) {
        this.itemSellingPrice = itemSellingPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }
}
