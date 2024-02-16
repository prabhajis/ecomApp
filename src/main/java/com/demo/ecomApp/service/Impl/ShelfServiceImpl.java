package com.demo.ecomApp.service.Impl;

import com.demo.ecomApp.dto.ProductShelfItemDto;
import com.demo.ecomApp.dto.ShelfDto;
import com.demo.ecomApp.dto.ShopperDto;
import com.demo.ecomApp.entity.ProductsEntity;
import com.demo.ecomApp.entity.ShelfEntity;
import com.demo.ecomApp.entity.mapper.DtoMapper;
import com.demo.ecomApp.exception.EcomException;
import com.demo.ecomApp.repository.ProductRepository;
import com.demo.ecomApp.repository.ShelfRepository;
import com.demo.ecomApp.service.ShelfService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ShelfServiceImpl implements ShelfService {

    @Autowired
    ShelfRepository shelfRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void insertShelfData(List<ShelfDto> shelfDtoList) {
        List<ShelfEntity> shelfEntities = null;

        List<String> productIdList = productRepository.getProductIds();
        if(productIdList.isEmpty()){
            throw new EcomException("POL0001", "Unable to insert Shelves","Products not found");
        }
        List<String> paylaodProductIds = shelfDtoList.stream()
                .flatMap(myObject -> myObject.getShelf().stream())
                .map(ProductShelfItemDto::getProductId)
                .collect(Collectors.toList());

        List<String> productIdsToRemove = paylaodProductIds.stream().filter(p -> !productIdList.contains(p)).collect(Collectors.toList());
        List<ShelfDto> filteredList = removeShelvesByProductId(shelfDtoList, productIdsToRemove);

        List<ShelfEntity> shopperShelfList = filteredList.stream()
                .flatMap(shopperEntry -> shopperEntry.getShelf().stream()
                        .map(shelfEntry -> new ShelfEntity(shopperEntry.getShopperId(), shelfEntry.getProductId(), shelfEntry.getRelevancyScore())))
                .collect(Collectors.toList());

        shelfRepository.saveAll(shopperShelfList);
    }

    @Override
    public List<ShopperDto> getShopperByProducts(String productId, int limit, Pageable pageRequest) {
        List<ShelfEntity> shoppers = shelfRepository.getShelvesByProducts(productId, pageRequest);
        if(shoppers!= null) {
            DtoMapper.mapShelfEntitiesToShopperDtos(shoppers);
        }
        return null;
    }

    private List<ShelfDto> removeShelvesByProductId(List<ShelfDto> shelfDtoList, List<String> productIdToRemove) {
            return shelfDtoList.stream()
                    .map(shelfDto -> {
                        List<ProductShelfItemDto> updatedShelf = shelfDto.getShelf().stream()
                                .filter(item -> !productIdToRemove.contains(item.getProductId()))
                                .collect(Collectors.toList());
                        shelfDto.setShelf(updatedShelf);
                        return shelfDto;
                    })
                    .collect(Collectors.toList());
        }

    @Override
    public void deleteShelvesByProductIdAndShopperId(String productId, String shopperId) {
        if (shelfRepository.findByShopperIdAndProductId(shopperId, productId).isPresent()){
            shelfRepository.deleteShelvesByShopperIdAndProductId(productId, shopperId);
            log.info("Shopper deleted. Product ID:{} Shopper Id:{}", productId, shopperId);
        } else{
            log.info("Shoppers not found");
            throw new EcomException("POL0001", "Shopper not found", shopperId);
        }
    }
}
