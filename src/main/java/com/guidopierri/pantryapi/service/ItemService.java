package com.guidopierri.pantryapi.service;

import com.guidopierri.pantryapi.model.GtinItem;
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
import java.util.Date;

@Service
public class ItemService {
    @Autowired
    private ItemRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Item createItem(String userEmail, String name, String quantity, String expiryDate, String GTIN, String brand, String image ) {
        Item item = repository.insert(new Item(userEmail, name, quantity, expiryDate, GTIN, brand, image));

        mongoTemplate.update(User.class)
                .matching(Criteria.where("email").is(userEmail))
                .apply(new Update().push("itemIds").value(item))
                .first();

        return item;
    }
    public Item getItem(String id){
        return repository.findById(new ObjectId(id)).orElseThrow();
    }

    public GtinItem createGtinItem(String userEmail, String name, String quantity, String expiryDate, String image, String brand, String GTIN) {
        GtinItem item = repository.insert(new GtinItem(userEmail, GTIN, name, brand, image, expiryDate, Integer.parseInt(quantity)));

        mongoTemplate.update(User.class)
                .matching(Criteria.where("email").is(userEmail))
                .apply(new Update().push("itemIds").value(item))
                .first();

        return item;
    }
}
