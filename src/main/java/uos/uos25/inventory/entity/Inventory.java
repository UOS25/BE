package uos.uos25.inventory.entity;

import jakarta.persistence.*;
import uos.uos25.common.BaseEntity;
import uos.uos25.product.entity.Product;
import uos.uos25.shop.entity.Shop;

import java.time.LocalDateTime;

@Entity
public class Inventory extends BaseEntity {
    @Id
    private Integer inventoryId;

    private Integer ea;
    private LocalDateTime warehousingDate;
    private Integer display;
    private LocalDateTime expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
