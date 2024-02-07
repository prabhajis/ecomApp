package com.demo.ecomApp.service;

import com.demo.ecomApp.dto.ShelfDto;
import com.demo.ecomApp.dto.ShopperDto;
import com.demo.ecomApp.entity.ProductsEntity;
import com.demo.ecomApp.entity.ShelfEntity;
import com.demo.ecomApp.entity.ShopperEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShelfService {

    //insert Shelf Data
    void insertShelfData(List<ShelfDto> shelfDtoList);

    //get shopper details by product id
    List<ShopperDto> getShopperByProducts(String productId, int limit, Pageable pageRequest);
}
