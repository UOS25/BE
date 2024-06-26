package uos.uos25.shop.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.exception.ShopNotFoundException;
import uos.uos25.shop.repository.ShopRepository;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;

    public Shop findShopById(Long shopId) {
        return shopRepository.findById(shopId).orElseThrow(() -> new ShopNotFoundException());
    }
}
