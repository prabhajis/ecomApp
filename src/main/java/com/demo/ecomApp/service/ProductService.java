package com.demo.ecomApp.service;

import com.demo.ecomApp.dto.ProductDto;
import com.demo.ecomApp.entity.ProductsEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

     //insert product elements
     void insertProducts(List<ProductDto> productlist);

     //Get product details by Shopper Id
     List<ProductsEntity> getProductsByShopper(String shopperId, String category, String brand, int limit, Pageable pageRequest);

     void deleteProductByProductId(String productId);
}
