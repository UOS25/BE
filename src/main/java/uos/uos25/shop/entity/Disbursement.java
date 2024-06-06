package uos.uos25.shop.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
public class Disbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long disburseId;

    private LocalDateTime disburseDate;
    private Long disburseAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @Builder
    public Disbursement(Long disburseId, LocalDateTime disburseDate, Long disburseAmount, Shop shop) {
        this.disburseId = disburseId;
        this.disburseDate = disburseDate;
        this.disburseAmount = disburseAmount;
        this.shop = shop;
    }
}
