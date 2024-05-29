package uos.uos25.returns.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import uos.uos25.common.BaseEntity;
import uos.uos25.shop.entity.Shop;
import uos.uos25.product.entity.Product;

import java.time.LocalDateTime;

@Entity
public class Returns extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long returnsId;

    @Column(nullable = false)
    private Integer ea;

    @Column(nullable = false)
    @ColumnDefault("0")
    private String returnsStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
