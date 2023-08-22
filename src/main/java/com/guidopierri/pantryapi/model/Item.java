package com.guidopierri.pantryapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    private ObjectId id;
    private String userId;
    private String name;
    private String quantity;
    private String  expirationDate;

    public Item(String userId, String name, String quantity, String expirationDate) {
        this.userId = userId;
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }
}
