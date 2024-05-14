package uos.uos25.orders.entity;

import jakarta.persistence.*;
import uos.uos25.product.entity.Product;
import uos.uos25.shop.entity.Shop;

import java.time.LocalDateTime;

@Entity
public class Orders {
    @Id
    private Integer ordersId;

    private LocalDateTime ordersDate;
    @Column(length = 20)
    private String orderStatus;
    private Integer givenEa;
    private Integer orderEa;
    @Column(length = 18)
    private String orderCheck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
