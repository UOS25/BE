package uos.uos25.disposal.entity;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.NoArgsConstructor;
import uos.uos25.common.BaseEntity;
import uos.uos25.product.entity.Product;
import uos.uos25.shop.entity.Shop;

@Entity
@NoArgsConstructor
public class Disposal extends BaseEntity {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long disposalId;

    @Column(nullable = false)
    private Integer ea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barcode", nullable = false)
    private Product product;

    @Builder
    public Disposal(Shop shop, Product product, Integer ea) {
        this.shop = shop;
        this.product = product;
        this.ea = ea;
    }
}
