package com.demo.ecomApp.entity.mapper;

import com.demo.ecomApp.dto.ProductDto;
import com.demo.ecomApp.dto.ProductShelfItemDto;
import com.demo.ecomApp.dto.ShopperDto;
import com.demo.ecomApp.entity.ProductsEntity;
import com.demo.ecomApp.entity.ShelfEntity;

import java.util.List;
import java.util.stream.Collectors;

public class DtoMapper {

    public static List<ShopperDto> mapShelfEntitiesToShopperDtos(List<ShelfEntity> shelfEntities) {
        return shelfEntities.parallelStream() // Use parallelStream() for parallel processing
                .map(DtoMapper::mapShelfEntityToShopperDto)
                .collect(Collectors.toList());
    }

    private static ShopperDto mapShelfEntityToShopperDto(ShelfEntity shelfEntity) {
        ShopperDto shopperDto = new ShopperDto();
        shopperDto.setShopperId(shelfEntity.getShopperId());
        shopperDto.setRelevancyScore(shelfEntity.getRelevancyScore() != null ? shelfEntity.getRelevancyScore() : 0.0);
        return shopperDto;
    }

    //convert ProductDTO to Entity
    public static ProductsEntity convertToEntity(ProductDto productDto) {
        ProductsEntity productEntity = new ProductsEntity();
        productEntity.setProductId(productDto.getProductId());
        productEntity.setCategory(productDto.getCategory());
        productEntity.setBrand(productDto.getBrand());
        return productEntity;
    }

//    public static ShelfEntity convertToShelfEntity(ProductShelfItemDto productShelfItemDto) {
//        ShelfEntity shelfEntity = new ShelfEntity();
//        shelfEntity.setProductId(productShelfItemDto.getProductId());
//
//
//
//
////        productShelfItemDtos.stream()
////                .forEach(dto -> {
////                    shelfEntity.setProductId(dto.getProductId());
////                    shelfEntity.setRelevancyScore(dto.getRelevancyScore());
////                });
//        return  shelfEntity;
//    }
}
