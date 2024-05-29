package uos.uos25.inventory.entity;

import jakarta.persistence.*;
import uos.uos25.common.BaseEntity;
import uos.uos25.product.entity.Product;
import uos.uos25.shop.entity.Shop;

import java.time.LocalDateTime;

@Entity
public class Inventory extends BaseEntity {

    @Id @GeneratedValue
    @Column(nullable = false)
    private Long inventoryId;

    @Column(nullable = false)
    private Integer ea;

    @Column(nullable = false)
    private LocalDateTime warehousingDate;

    @Column(nullable = false)
    private Integer display;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
