package uos.uos25.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Disposal {
    @Id
    private Integer disposalId;
    private LocalDateTime disposalDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
