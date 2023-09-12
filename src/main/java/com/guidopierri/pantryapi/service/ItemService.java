package com.guidopierri.pantryapi.service;

import com.guidopierri.pantryapi.model.Item;
import com.guidopierri.pantryapi.model.User;
import com.guidopierri.pantryapi.repository.ItemRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.time.LocalDate;

@Service
public class ItemService {
    @Autowired
    private ItemRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Item createItem(String userEmail, String name, String quantity, String expirationDate) {
        Item item = repository.insert(new Item(userEmail, name, quantity, expirationDate));

        mongoTemplate.update(User.class)
                .matching(Criteria.where("email").is(userEmail))
                .apply(new Update().push("itemIds").value(item))
                .first();

        return item;
    }

}
