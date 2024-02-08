package com.demo.ecomApp.service.Impl;

import com.demo.ecomApp.dto.ProductShelfItemDto;
import com.demo.ecomApp.dto.ShelfDto;
import com.demo.ecomApp.dto.ShopperDto;
import com.demo.ecomApp.entity.ShelfEntity;
import com.demo.ecomApp.entity.mapper.DtoMapper;
import com.demo.ecomApp.repository.ShelfRepository;
import com.demo.ecomApp.service.ShelfService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ShelfServiceImpl implements ShelfService {

    @Autowired
    ShelfRepository shelfRepository;

    @Override
    public void insertShelfData(List<ShelfDto> shelfDtoList){
        for(ShelfDto shelfDTOlist: shelfDtoList){
            ShelfEntity shelfEntity = new ShelfEntity();
            shelfEntity.setShopperId(shelfDTOlist.getShopperId());
            for(ProductShelfItemDto items: shelfDTOlist.getShelf()){
                shelfEntity.setProductId(items.getProductId());
                shelfEntity.setRelevancyScore(items.getRelevancyScore());
                try {
                    shelfRepository.save(shelfEntity);

                } catch (DataIntegrityViolationException e){
                    log.info("Foreign key constraint violation: Coudn't insert product ID : {}", shelfEntity.getProductId() + e.getMessage());
                }
            }
        }
    }

    @Override
    public List<ShopperDto> getShopperByProducts(String productId, int limit, Pageable pageRequest) {
        List<ShelfEntity> shoppers = shelfRepository.getShelvesByProducts(productId, pageRequest);
        if(shoppers!= null) {
            return DtoMapper.mapShelfEntitiesToShopperDtos(shoppers);
        }
        return null;
    }
}
