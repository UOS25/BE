package uos.uos25.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Inventory {
    @Id
    private Integer inventoryId;

    private Integer ea;
    private LocalDateTime warehousingDate;
    private Integer display;
    private LocalDateTime expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
