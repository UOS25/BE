package uos.uos25.disposal.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import uos.uos25.product.entity.Product;
import uos.uos25.shop.entity.Shop;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Disposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long disposalId;
    private Integer ea;
    private LocalDateTime disposalDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public Disposal(Shop shop, Product product, Integer ea){
        this.shop = shop;
        this.product = product;
        this.ea = ea;
    }
}
