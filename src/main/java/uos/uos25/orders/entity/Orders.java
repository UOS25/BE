package uos.uos25.orders.entity;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.uos25.common.BaseEntity;
import uos.uos25.product.entity.Product;
import uos.uos25.shop.entity.Shop;

@Entity
@Getter
@NoArgsConstructor
public class Orders extends BaseEntity {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersId;

    @Column(length = 50, nullable = false)
    private String ordersStatus;

    @Column(nullable = true)
    private Integer givenEa;

    @Column(nullable = false)
    private Integer ordersEa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barcode", nullable = false)
    private Product product;

    @Builder
    public Orders(
            String ordersStatus, Integer givenEa, Integer ordersEa, Shop shop, Product product) {
        this.ordersStatus = ordersStatus;
        this.givenEa = givenEa;
        this.ordersEa = ordersEa;
        this.shop = shop;
        this.product = product;
    }

    public void setOrdersEa(Integer ea) {
        this.ordersEa = ea;
    }

    public void setOrdersStatus(String ordersStatus) {
        this.ordersStatus = ordersStatus;
    }

    public void setGivenEa(Integer givenEa) {
        this.givenEa = givenEa;
    }

    public Integer getPrice() {
        return ordersEa * product.getOrderPrice();
    }

    public boolean canBeCanceled() {
        return ordersStatus.equals(OrdersStatus.STORED.getStatus());
    }
}
