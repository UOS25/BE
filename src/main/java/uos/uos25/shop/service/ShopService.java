package uos.uos25.shop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.repository.ShopRepository;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;

    @Transactional
    public Shop saveShop(Shop shop) {
        return shopRepository.save(shop);
    }

}
