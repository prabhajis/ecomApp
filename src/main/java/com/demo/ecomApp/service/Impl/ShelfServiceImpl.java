package com.demo.ecomApp.service.Impl;

import com.demo.ecomApp.dto.ProductShelfItemDto;
import com.demo.ecomApp.dto.ShelfDto;
import com.demo.ecomApp.dto.ShopperDto;
import com.demo.ecomApp.entity.ShelfEntity;
import com.demo.ecomApp.entity.mapper.DtoMapper;
import com.demo.ecomApp.repository.ShelfRepository;
import com.demo.ecomApp.service.ShelfService;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
                shelfRepository.save(shelfEntity);
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
