package uos.uos25.shop.entity;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.uos25.common.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
public class Disbursement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long disburseId;

    @Column(nullable = true)
    private Integer disburseAmount;

    @Column(nullable = false)
    private String disburseType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @Builder
    public Disbursement(Integer disburseAmount, String disburseType, Shop shop) {
        this.disburseAmount = disburseAmount;
        this.disburseType = disburseType;
        this.shop = shop;
    }
}
