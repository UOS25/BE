package uos.uos25.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.exception.ShopNotFoundException;
import uos.uos25.shop.repository.ShopRepository;

@Service
@RequiredArgsConstructor
public class ShopService {
    private ShopRepository shopRepository;

    public Shop findShopById(Long shopId){
        return shopRepository.findById(shopId).orElseThrow(() -> new ShopNotFoundException());
    }
}
