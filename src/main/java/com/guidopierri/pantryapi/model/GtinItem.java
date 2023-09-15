package com.guidopierri.pantryapi.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class GtinItem {
    @Id
    private ObjectId id;
    private String userEmail;
    private String GTIN;
    private String name;
    private String brand;
    private String image;
    private String expiryDate;
    private int quantity;

    public String getUserEmail() {
        return userEmail;
    }

    public String getGTIN() {
        return GTIN;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getImage() {
        return image;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public GtinItem(String userEmail, String GTIN, String name, String brand, String image, String expiryDate, int quantity) {
        this.userEmail = userEmail;
        this.GTIN = GTIN;
        this.name = name;
        this.brand = brand;
        this.image = image;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }
}
