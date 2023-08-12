package com.guidopierri.pantryapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*")
@RestController
public class Controller {

    private final String externalApiBaseUrl = "https://api.dabas.com/DABASService/V2";
 //   private final String externalApiBaseUrlSeach = "https://api.dabas.com/DABASService/V2";
    @GetMapping("/product/{gtin}")
    public ResponseEntity<Map<String, Object>> fetchProductByGtin(@PathVariable String gtin) {
        String apiUrl = externalApiBaseUrl + "/article/gtin/" + gtin + "/JSON?apikey=741ffd2b-3be4-49b8-b837-45be48c7e7be";

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(apiUrl, Map.class);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/search/{searchparameter}")
    public ResponseEntity<List<Map<String, Object>>> fetchProductBySearchParameter(@PathVariable String searchparameter) {
        String apiUrl = externalApiBaseUrl +"/articles/searchparameter/" + searchparameter + "/JSON?apikey=741ffd2b-3be4-49b8-b837-45be48c7e7be";

        RestTemplate restTemplate = new RestTemplate();
        List<Map<String, Object>> productList = restTemplate.getForObject(apiUrl, List.class);

        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}