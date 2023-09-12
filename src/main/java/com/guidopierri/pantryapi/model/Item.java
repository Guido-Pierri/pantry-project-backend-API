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
    private String expirationDate;

    public Item(String userEmail, String name, String quantity, String expirationDate) {
        this.userEmail = userEmail;
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }
}
