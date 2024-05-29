package uos.uos25.sale.entity;

import jakarta.persistence.*;
import uos.uos25.common.BaseEntity;
import uos.uos25.shop.entity.Shop;

import java.time.LocalDateTime;

@Entity
public class Sales extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long salesId;

    @Column(nullable = false)
    private LocalDateTime salesDate;

    @Column(nullable = false)
    private String salesType;

    @Column(nullable = false)
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;
}
