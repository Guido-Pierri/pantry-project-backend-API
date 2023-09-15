package com.guidopierri.pantryapi.repository;

import com.guidopierri.pantryapi.model.GtinItem;
import com.guidopierri.pantryapi.model.Inventory;
import com.guidopierri.pantryapi.model.Item;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, ObjectId> {

    Item insert(Item item);
    GtinItem insert(GtinItem item);
}