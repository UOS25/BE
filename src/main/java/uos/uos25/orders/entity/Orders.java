package uos.uos25.orders.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.uos25.common.BaseEntity;
import uos.uos25.product.entity.Product;
import uos.uos25.shop.entity.Shop;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Orders extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersId;

    @Column(length = 20)
    private String ordersStatus;
    private Integer givenEa;
    private Integer ordersEa;
    @Column(length = 18)
    private String ordersCheck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public Orders(String ordersStatus, Integer givenEa, Integer ordersEa, String ordersCheck, Shop shop, Product product){
        this.ordersStatus = ordersStatus;
        this.givenEa = givenEa;
        this.ordersEa = ordersEa;
        this.ordersCheck = ordersCheck;
        this.shop = shop;
        this.product = product;
    }

    public void setOrdersEa(Integer ea){
        this.ordersEa = ea;
    }

    public void setOrdersStatus(String ordersStatus) {
        this.ordersStatus = ordersStatus;
    }
}
