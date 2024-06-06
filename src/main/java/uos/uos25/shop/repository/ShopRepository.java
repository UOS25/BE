package uos.uos25.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uos.uos25.shop.entity.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Shop findByShopName(String shopName);
}
