package com.demo.ecomApp.controller;

import com.demo.ecomApp.dto.ShopperDto;
import com.demo.ecomApp.entity.ProductsEntity;
import com.demo.ecomApp.entity.ShelfEntity;
import com.demo.ecomApp.entity.ShopperEntity;
import com.demo.ecomApp.service.ProductService;
import com.demo.ecomApp.service.ShelfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/ecom-service/v1")
public class EcomContoller {
    @Autowired
    ProductService productService;

    @Autowired
    ShelfService shelfService;
    @GetMapping("/get-products")
    public ResponseEntity getProductsByShopper(
            @RequestParam String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(defaultValue = "10") int limit
    ) {
        Pageable pageRequest = PageRequest.of(0, limit);
        List<ProductsEntity> productsEntityList = productService.getProductsByShopper(shopperId, category, brand, limit, pageRequest);
        ResponseEntity responseEntity = ResponseEntity.ok(productsEntityList);
        log.info("Data queried Successfully");
        return responseEntity;
    }

    @GetMapping("/get-shopper")
    public ResponseEntity getShopperByProduct(
            @RequestParam String productId,
            @RequestParam(defaultValue = "10") int limit
    ) {
        Pageable pageRequest = PageRequest.of(0, limit);
        List<ShopperDto> shopperEntityList = shelfService.getShopperByProducts(productId, limit, pageRequest);
        ResponseEntity responseEntity = ResponseEntity.ok(shopperEntityList);
        log.info("Data queried Successfully");
        return responseEntity;
    }
}
