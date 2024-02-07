package com.demo.ecomApp.controller;

import com.demo.ecomApp.Exception.CommonException;
import com.demo.ecomApp.dto.ProductDto;
import com.demo.ecomApp.dto.ShelfDto;
import com.demo.ecomApp.service.ProductService;
import com.demo.ecomApp.service.ShelfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/data-service/v1")
public class DataController {

    @Autowired
    ProductService productService;

    @Autowired
    ShelfService shelfService;

    @PostMapping(value = "/add-products")
    public ResponseEntity addProducts(@RequestBody List<ProductDto> productList) {
        ResponseEntity responseEntity;
        try {
            productService.insertProducts(productList);
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body("Products Inserted");
            log.info("Inserted Successfully ");
            return responseEntity;
        } catch (Exception e){
            responseEntity = (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Insert Failed ", e);
        }
        return responseEntity;

    }

    @PostMapping(value = "/addShelves")
    public ResponseEntity addShelves(@RequestBody List<ShelfDto> shelfDtoList) {
        ResponseEntity responseEntity;
        try {
            shelfService.insertShelfData(shelfDtoList);
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body("shelves Inserted");
            log.info("Inserted Successfully ");
        } catch (Exception e){
            responseEntity = (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Insert Failed ", e);
        }
        return  responseEntity;
    }

}
