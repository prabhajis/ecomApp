package com.demo.ecomApp.service.Impl;

import com.demo.ecomApp.dto.ProductDto;
import com.demo.ecomApp.entity.ProductsEntity;
import com.demo.ecomApp.entity.mapper.DtoMapper;
import com.demo.ecomApp.exception.EcomException;
import com.demo.ecomApp.repository.ProductRepository;
import com.demo.ecomApp.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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
    @Cacheable(value = "product", key = "#shopperId", sync = true)
    public List<ProductsEntity> getProductsByShopper(String shopperId, String category, String brand, int limit, Pageable pageRequest){
        List<ProductsEntity> products = productRepository.getProductsByShopper(shopperId, category, brand, pageRequest);
        if(products.isEmpty()){
            throw new EcomException("POL0001", "Unable retrieve Products", "Prodcuts not found");
        }
        return products;
    }

    @Override
    @CacheEvict(value = "get-product", key = "#productId")
    public void deleteProductByProductId(String productId){
        Optional<ProductsEntity> productsEntity = productRepository.findByProductId(productId);
        if(productsEntity.isPresent()) {
            productRepository.delete(productsEntity.get());
            log.info("Product deleted successfully");
        } else {
            log.info("Unable to delete product, product not found");
            throw new EcomException("POL0001", "Product Id not found", productId);
        }
    }

    @Override
    @Cacheable(value = "get-product", key = "#productId", sync = true)
    public ProductsEntity getProductsByProductId(String productId) {
        Optional<ProductsEntity> productsEntity = productRepository.findByProductId(productId);
        if(!productsEntity.isPresent()){
            throw new EcomException("POL0001", "Product not found", productId);
        }
        return productsEntity.get();
    }


}
