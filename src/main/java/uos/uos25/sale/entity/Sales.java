package uos.uos25.sale.entity;

import jakarta.persistence.*;
import uos.uos25.common.BaseEntity;
import uos.uos25.shop.entity.Shop;

import java.time.LocalDateTime;

@Entity
public class Sales extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer salesId;

    private LocalDateTime salesDate;
    @Column(length = 18)
    private String salesType;
    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
