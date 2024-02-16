package com.demo.ecomApp.service.Impl;

import com.demo.ecomApp.dto.ProductDto;
import com.demo.ecomApp.entity.ProductsEntity;
import com.demo.ecomApp.entity.mapper.DtoMapper;
import com.demo.ecomApp.exception.EcomException;
import com.demo.ecomApp.repository.ProductRepository;
import com.demo.ecomApp.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void insertProducts(List<ProductDto> productlist){
        List<ProductsEntity> productsEntityList = new ArrayList<>();
        for (ProductDto products : productlist) {
            if(!productRepository.findByProductId(products.getProductId()).isPresent()) {
                ProductsEntity productsEntity = DtoMapper.convertToEntity(products);
                productsEntityList.add(productsEntity);
                log.info("Products inserted. Product Id: {}",productsEntity.getProductId());
            } else {
                log.error("Products already exists. Product Id:{}", products.getProductId());
            }
        }
        productRepository.saveAll(productsEntityList);
    }

    @Override
    public List<ProductsEntity> getProductsByShopper(String shopperId, String category, String brand, int limit, Pageable pageRequest){
        List<ProductsEntity> products = productRepository.getProductsByShopper(shopperId, category, brand, pageRequest);
        return products;
    }

    @Override
    public void deleteProductByProductId(String productId){
        productRepository.deleteById(productId);
    }


}
