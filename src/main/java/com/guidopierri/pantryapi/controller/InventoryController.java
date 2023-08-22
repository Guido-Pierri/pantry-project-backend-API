package com.guidopierri.pantryapi.controller;

import com.guidopierri.pantryapi.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

@RequestMapping("/api/v1/inventories")

public class InventoryController {
    //@Autowired
    //private InventoryService service;

    //@PostMapping()
    //public ResponseEntity<Inventory> createReview(@RequestBody Map<String, String> payload) {

        //return new ResponseEntity<Inventory>(service.createReview(payload.get("reviewBody"),
        // payload.get("imdbId")), HttpStatus.CREATED);
    //}
}