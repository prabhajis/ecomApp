package com.demo.ecomApp.controller;

import com.demo.ecomApp.dto.ProductDto;
import com.demo.ecomApp.dto.ResponseDto;
import com.demo.ecomApp.dto.ShelfDto;
import com.demo.ecomApp.exception.EcomException;
import com.demo.ecomApp.service.ProductService;
import com.demo.ecomApp.service.ShelfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/data-service/v1")
public class DataController {

    @Autowired
    ProductService productService;

    @Autowired
    ShelfService shelfService;

    @PostMapping(value = "/add-products")
    public ResponseEntity<ResponseDto> addProducts(@RequestBody List<ProductDto> productList) {
        ResponseDto responseDto = new ResponseDto();
        ResponseEntity<ResponseDto> responseEntity;
            productService.insertProducts(productList);
            responseDto.setMessage("Products inserted");
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
            return responseEntity;
    }

    @PostMapping(value = "/addShelves")
    public ResponseEntity<ResponseDto> addShelves(@RequestBody List<ShelfDto> shelfDtoList) {
        ResponseEntity<ResponseDto> responseEntity = null;
        ResponseDto responseDto = new ResponseDto();
            if(shelfDtoList.isEmpty()){
                throw new EcomException("POL0001","Unable to insert shelves", "Shelf list not found");
            }
            shelfService.insertShelfData(shelfDtoList);
            responseDto.setMessage("Shopper Shelves inserted");
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        return  responseEntity;
    }
}
