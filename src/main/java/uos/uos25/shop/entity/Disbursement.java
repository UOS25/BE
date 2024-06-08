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
    private Long disburseId;

    private Integer disburseAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @Builder
    public Disbursement(Long disburseId, Integer disburseAmount, Shop shop) {
        this.disburseId = disburseId;
        this.disburseAmount = disburseAmount;
        this.shop = shop;
    }
}
