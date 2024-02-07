package com.demo.ecomApp.service.Impl;

import com.demo.ecomApp.dto.ProductDto;
import com.demo.ecomApp.entity.ProductsEntity;
import com.demo.ecomApp.entity.mapper.DtoMapper;
import com.demo.ecomApp.repository.ProductRepository;
import com.demo.ecomApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void insertProducts(List<ProductDto> productlist){
        for (ProductDto products : productlist) {
            ProductsEntity productsEntity = DtoMapper.convertToEntity(products);
            productRepository.save(productsEntity);
        }
    }

    @Override
    public List<ProductsEntity> getProductsByShopper(String shopperId, String category, String brand, int limit, Pageable pageRequest){
        List<ProductsEntity> products = productRepository.getProductsByShopper(shopperId, category, brand, pageRequest);
        return products;
    }


}
