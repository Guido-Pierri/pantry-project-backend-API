package com.guidopierri.pantryapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class User {
        @Id
        private ObjectId id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}

