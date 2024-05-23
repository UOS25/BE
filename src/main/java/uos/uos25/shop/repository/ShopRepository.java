package uos.uos25.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uos.uos25.shop.entity.Shop;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Shop findByShopName(String shopName);
}
