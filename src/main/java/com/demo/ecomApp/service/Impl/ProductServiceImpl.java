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
import java.util.stream.Collectors;


@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void insertProducts(List<ProductDto> productlist){
        List<ProductsEntity> productsEntityList = productlist.stream()
                .filter(p-> !productRepository.findByProductId(p.getProductId()).isPresent())
                .map(p -> DtoMapper.convertToEntity(p))
                .collect(Collectors.toList());

        productRepository.saveAll(productsEntityList);
    }

    @Override
    public List<ProductsEntity> getProductsByShopper(String shopperId, String category, String brand, int limit, Pageable pageRequest){
        List<ProductsEntity> products = productRepository.getProductsByShopper(shopperId, category, brand, pageRequest);
        return products;
    }

    @Override
    public void deleteProductByProductId(String productId){
        if(productRepository.findByProductId(productId).isPresent()) {
            productRepository.deleteById(productId);
            log.info("Product deleted successfully");
        } else {
            log.info("Unable to delete product, product not found");
            throw new EcomException("POL0001", "Product Id not found", productId);
        }

    }


}
