package uos.uos25.order.entity;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.uos25.common.BaseEntity;
import uos.uos25.headquarter.entity.Product;
import uos.uos25.shop.entity.Shop;

@Entity
@NoArgsConstructor
@Getter
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

    public Integer getPrice() {
        return ea * product.getOrderPrice();
    }
}
