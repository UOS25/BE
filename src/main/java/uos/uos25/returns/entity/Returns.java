package uos.uos25.returns.entity;

import jakarta.persistence.*;
import uos.uos25.shop.entity.Shop;
import uos.uos25.product.entity.Product;

import java.time.LocalDateTime;

@Entity
public class Returns {
    @Id
    private Integer returnsId;

    private LocalDateTime returnsDate;
    private Integer ea;
    @Column(length = 18)
    private String returnsStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
