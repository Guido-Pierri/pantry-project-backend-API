package com.guidopierri.pantryapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    private ObjectId id;
    private String userEmail;
    private String name;
    private String quantity;
    private String expiryDate;
    private String GTIN;
    private String brand;
    private String image;

    public Item(String userEmail, String name, String quantity, String expiryDate, String GTIN, String brand, String image) {
        this.userEmail = userEmail;
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.GTIN = GTIN;
        this.brand = brand;
        this.image = image;
    }

    public ObjectId getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getExpirationDate() {
        return expiryDate;
    }

    public String getGTIN() {
        return GTIN;
    }

    public String getBrand() {
        return brand;
    }

    public String getImage() {
        return image;
    }
}
