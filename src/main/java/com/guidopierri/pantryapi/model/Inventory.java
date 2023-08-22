package com.guidopierri.pantryapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    private ObjectId id;
    private String body;

    public Inventory(String body) {
        this.body = body;
    }


}
