package uos.uos25.order.entity;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.uos25.common.BaseEntity;
import uos.uos25.headquarter.entity.Product;
import uos.uos25.shop.entity.Shop;

@Entity
@Getter
@NoArgsConstructor
public class Returns extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long returnsId;

    @Column(nullable = false)
    private Integer ea;

    @Column(length = 50, nullable = false)
    private String returnsStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barcode", nullable = false)
    private Product product;

    @Builder
    public Returns(Integer ea, String returnsStatus, Shop shop, Product product) {
        this.ea = ea;
        this.returnsStatus = returnsStatus;
        this.shop = shop;
        this.product = product;
    }

    public Integer getPrice() {
        return product.getOrderPrice() * ea;
    }
}
