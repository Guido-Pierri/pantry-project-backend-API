package com.guidopierri.pantryapi.controller;

import com.guidopierri.pantryapi.model.GtinItem;
import com.guidopierri.pantryapi.model.Item;
import com.guidopierri.pantryapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/items")

public class ItemController {

    @Autowired
    private ItemService service;

    @PostMapping({"/create"})
    public ResponseEntity<Item> createGtinItem(@RequestBody Map<String, String> payload) {

        return new ResponseEntity<Item>(service.createItem(payload.get("userEmail"), payload.get("name"), payload.get("quantity"), payload.get("expiryDate"), payload.get("brand"), payload.get("image"),payload.get("GTIN")), HttpStatus.CREATED);
    }
    @PostMapping()
    public ResponseEntity<Item> createCustomItem(@RequestBody Map<String, String> payload) {

        return new ResponseEntity<Item>(service.createItem(payload.get("userEmail"), payload.get("name"), payload.get("quantity"), payload.get("expiryDate"),payload.get("GTIN"),payload.get("brand"),payload.get("image")), HttpStatus.CREATED);
    }
    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItem(@PathVariable String id) {

        return new ResponseEntity<Item>(service.getItem(id), HttpStatus.OK);
    }
}
