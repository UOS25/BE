package uos.uos25.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Purchase {
    @Id
    private Integer purchaseId;

    private LocalDateTime purchaseDate;
    @Column(length = 20)
    private String purchaseStatus;
    private Integer givenEa;
    private Integer orderEa;
    @Column(length = 18)
    private String purchaseCheck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
